package com.erp.distribution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erp.distribution.sfa.common_utils.AlertDialogConfirm;
import com.erp.distribution.sfa.master.material.MaterialActivity;
import com.erp.distribution.sfa.master.material.MaterialViewModel;
import com.erp.distribution.sfa.master.Customer.CustomerActivity;
import com.erp.distribution.sfa.master.Customer.CustomerViewModel;
import com.erp.distribution.sfa.master.syncronize_fromserver.SyncronizeActivity;
import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;
import com.erp.distribution.sfa.security_model.FUser;
import com.erp.distribution.sfa.security_repository.FUserServiceRest;
import com.erp.distribution.sfa.transaksi.sales_order.SalesOrderActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final int RE_LOGIN = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    public static final int TO_SUBMENU_REQUEST = 3;

    ApiAuthenticationClient apiAuthenticationClient;

    MainViewModel mainViewModel;

    MaterialViewModel fMaterialViewModel;
    CustomerViewModel fCustomerViewModel;

    FUser userActive = new FUser();

    ProgressBar progressBar;

    @BindView(R.id.greeting_img)
    ImageView greetImg;
    @BindView(R.id.greeting_text1)
    TextView greetText1;
    @BindView(R.id.greeting_text2)
    TextView greetText2;


    @BindView(R.id.home_mTopSyncronize)
    RelativeLayout mTopSyncronize;
    @BindView(R.id.home_mTopTopup)
    RelativeLayout mTopTopup;
    @BindView(R.id.home_mTopPromotion)
    RelativeLayout mTopPromotion;

    @BindView(R.id.home_mJourneyPlan)
    CardView mJourneyPlan;
    @BindView(R.id.home_mSalesOrder)
    CardView mSalesOrder;
    @BindView(R.id.home_mSummary)
    CardView mSummary;
    @BindView(R.id.home_mProduct)
    CardView mProduct;
    @BindView(R.id.home_mCustomer)
    CardView mCustomer;
    @BindView(R.id.home_mHelp)
    CardView mHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        ButterKnife.bind(this);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        fMaterialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);
        fCustomerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        /**
         * SECURITY BASIC CONFIG
         */
        apiAuthenticationClient = ApiAuthenticationClient.getInstance();
//        apiAuthenticationClient.setBaseUrl("http://192.168.1.100:8989/rest"); //di taruh API

        initialize();
    }

    /**
     * Konsep:
     * A. Ketika Login -> Wajib Menggunakan Otentikasi dari Server
     * B. Ketika Berhasil Login maka aplikasi akan tetap bisa digunakan walau tanpa koneksi internet sampai Logout
     *(setidaknya pernah login, sudah bisa menggunakan aplikasi)
     *
     * 1. Ketika masuk aplikasi: Maka periksa apakah username dan password ada dalam database (biar salah atau benar)
     *    A. Jika ada maka aplikasi bisa dipakai (walau username dan database salah)
     *    B. Jika belum ada maka akan dipaksa untuk Login
     *
     * 2. Login -> Ambil UserName dan Password dari Database -> Jika Ada maka simpan kedalam database
     * 3. User pada database akan dihapus jika: (a) Logout (b) Berhasil Login (diganti dengan data user baru)
     */
    public void initialize(){
        initializeListener();
        if (mainViewModel.getAllFUser().size() ==0) {
            showLoginView(); //Setidaknya pernah masuk
        }else {
            /**
             * Init user otentikasi
             * Dari Dao
             */
            userActive = mainViewModel.getAllFUser().get(0);
            apiAuthenticationClient.setUserActive(userActive);
            apiAuthenticationClient.setUsername(userActive.getUsername());
//            apiAuthenticationClient.setPassword(userActive.getPassword());
            apiAuthenticationClient.setPassword(userActive.getPlainPassword());

            apiAuthenticationClient.setBaseUrl("http://ssp-surabaya.ddns.net:8989/rest/");

            showDashBoard();
        }


    }


    public  void showDashBoard(){
        //Asumsi pada tabel user cuma satu

        greeting();

    }

    void showLoginView(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        intent.putExtra(LoginActivity.EXTRA_ID, note.getId());
//        intent.putExtra(LoginActivity.EXTRA_OBJECT, note);
        startActivityForResult(intent, RE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RE_LOGIN && resultCode == RESULT_OK) {
            FUser resultObject = (FUser) data.getSerializableExtra(LoginActivity.EXTRA_OBJECT);
            apiAuthenticationClient = ApiAuthenticationClient.getInstance();
            apiAuthenticationClient.setUsername(resultObject.getUsername());
            apiAuthenticationClient.setPassword(resultObject.getPassword());
            apiAuthenticationClient.setBaseUrl("http://192.168.1.100:8989/rest/");
//            apiAuthenticationClient.setUsername("bagus");
//            apiAuthenticationClient.setPassword("hacker");

            FUserServiceRest fUserServiceRest = new FUserServiceRest(this);
            FUser domain = fUserServiceRest.getFUserByUsername(resultObject.getUsername());
            if (domain.getUsername().isEmpty() || domain.getPassword().isEmpty()) {
                Toast.makeText(this, "Invalid Username atau Password", Toast.LENGTH_LONG).show();

                showLoginView();
            }else {
                mainViewModel.deleteAllFUser();
                domain.setPlainPassword(resultObject.getPassword()); //Password yang dipakai untuk logi ke server: Adanya di android saja
                mainViewModel.insert(domain);

                showDashBoard();

                Toast.makeText(this, "User Saved", Toast.LENGTH_LONG).show();
            }


        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
//            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }



    @SuppressLint("SetTextI18n")
    private void greeting() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        String namaUser = userActive.getFullName();
        if (userActive.getFullName().isEmpty()) userActive.getUsername();


        if (timeOfDay >= 0 && timeOfDay < 12){
            greetText1.setText("Selamat Pagi");
            greetImg.setImageResource(R.drawable.img_default_half_morning);
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText1.setText("Selamat Siang");
            greetImg.setImageResource(R.drawable.img_default_half_afternoon);
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText1.setText("Selamat Sore");
            greetImg.setImageResource(R.drawable.img_default_half_without_sun);
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText1.setText("Selamat Malam");
            greetText1.setTextColor(Color.WHITE);
            greetImg.setImageResource(R.drawable.img_default_half_night);
        }
        greetText2.setText(namaUser);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboard_refresh:
                return true;
            case  R.id.dashboard_keluar:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void logOut(){
        AlertDialogConfirm alert = new AlertDialogConfirm(this, "Keluar dari Aplikasi?");
        alert.getButtonOke().setOnClickListener( (View view) -> {
            mainViewModel.deleteAllFUser();
            userActive = new FUser();
            showLoginView();
        });
        alert.getButtonCancel().setOnClickListener( (View view) -> {
            alert.dismiss();
        });
        alert.showDialog();
    }
    void initializeListener(){
//        mTopSyncronize.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//        mTopTopup.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//        mTopPromotion.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//
//
//
//        mJourneyPlan.setOnClickListener((View v) -> {
//            Toast.makeText(this, "Hello Journey Plan", Toast.LENGTH_LONG).show();
//        });
//        mSalesOrder.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//
//        });
//        mSummary.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//
//        });
//        mProduct.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//
//        });
//        mCustomer.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//        mHelp.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });


    }
    public void aksiMenuDashboard(View v) {

        if (v.getId() == R.id.home_mTopSyncronize) {
        }else if (v.getId() == R.id.home_mTopTopup) {
        }else if (v.getId() == R.id.home_mTopPromotion) {
        }else if (v.getId() == R.id.home_mJourneyPlan) {
        }else if (v.getId() == R.id.home_mSalesOrder) {

        }else if (v.getId() == R.id.home_mSummary) {
        }else if (v.getId() == R.id.home_mProduct) {

        }else if (v.getId() == R.id.home_mCustomer) {

        }else if (v.getId() == R.id.home_mHelp) {


        }
    }

    @OnClick(R.id.home_mTopSyncronize)
    void menuSyncronize(){
        AlertDialogConfirm alert = new AlertDialogConfirm(this, "Lanjutkan Syncronisasi dengan Server (membutuhkan waktu yang agak lama)");
        alert.getButtonOke().setOnClickListener( (View view) -> {
            Intent intent = new Intent(MainActivity.this, SyncronizeActivity.class);
            intent.putExtra(SyncronizeActivity.EXTRA_OBJECT, userActive);
            startActivity(intent);
            alert.dismiss();
        });
        alert.getButtonCancel().setOnClickListener( (View view) -> {
            alert.dismiss();
        });
        alert.showDialog();
    }
    @OnClick(R.id.home_mSalesOrder)
    void menuSalesOrder(){
        Intent intent = new Intent(MainActivity.this, SalesOrderActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.home_mProduct)
    void menuProduct(){
        Intent intent = new Intent(MainActivity.this, MaterialActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.home_mCustomer)
    void menuCustomer() {
        Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
        startActivity(intent);
    }

}