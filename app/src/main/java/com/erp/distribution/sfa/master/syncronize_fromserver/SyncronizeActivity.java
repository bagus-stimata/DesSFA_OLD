package com.erp.distribution.sfa.master.syncronize_fromserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.erp.distribution.R;
import com.erp.distribution.sfa.master.material.MaterialViewModel;
import com.erp.distribution.sfa.master.Customer.CustomerViewModel;
import com.erp.distribution.sfa.model.FCustomer;
import com.erp.distribution.sfa.model.FMaterial;
import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;
import com.erp.distribution.sfa.security_model.FUser;
import com.erp.distribution.sfa.service_rest.FCustomerServiceRest;
import com.erp.distribution.sfa.service_rest.FMaterialServiceRest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SyncronizeActivity extends AppCompatActivity {

    public static final String EXTRA_OBJECT = "com.erp.distribution.sfa.master.SyncronizeActivity.EXTRA_OBJECT";

    ApiAuthenticationClient apiAuthenticationClient;

    MaterialViewModel fMaterialViewModel;
    CustomerViewModel fCustomerViewModel;

    FUser userActive = new FUser();

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.progress_text)
    TextView progressText;
    @BindView(R.id.detil_info)
    TextView detilInfo;


    @BindView(R.id.progress_btn_selesai)
    Button btnSelesai;


    int counter =0;
    int persentase = 0;

    FMaterialServiceRest fMaterialServiceRest;
    FCustomerServiceRest fCustomerServiceRest;
    List<FMaterial> listFMaterial = new ArrayList<>();
    List<FCustomer> listFCustomer = new ArrayList<>();
    int allDataSize = 0;

    Thread thread1;
    Thread thread2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syncronize);

        ButterKnife.bind(this);

        apiAuthenticationClient = ApiAuthenticationClient.getInstance();
//        apiAuthenticationClient.setUsername(userActive.getUsername());
//        apiAuthenticationClient.setPassword(userActive.getPlainPassword());

        fMaterialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);
        fCustomerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_OBJECT)) {
            userActive = (FUser) intent.getSerializableExtra(EXTRA_OBJECT);
        }

        fMaterialServiceRest = new FMaterialServiceRest(this);
        fCustomerServiceRest = new FCustomerServiceRest(this);

        detilInfo.setText("Sedang menarik data dari server...");

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                fMaterialViewModel.deleteAllFMaterial();
                listFMaterial = fMaterialServiceRest.getAllFMaterialByDivision(userActive.getFdivisionBean());
                startInsertToDb();
            }
        });
        thread1.start();

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                fCustomerViewModel.deleteAllFCustomer();
                listFCustomer = fCustomerServiceRest.getAllFCustomerByDivision(userActive.getFdivisionBean());
                startInsertToDb();
            }
        });
        thread2.start();


        btnSelesai.setOnClickListener((View v) -> {
            finish(); //finish intent
        });

    }

    public void startInsertToDb(){
        if (listFMaterial.size() >0 && listFCustomer.size()>0) {
            allDataSize = listFCustomer.size()+listFMaterial.size();

            progressBar.setMax(allDataSize);
            progressBar.setIndeterminate(false);

            for (FMaterial domain: listFMaterial) {
                InsertFMaterialAsyncTask insertTask = new InsertFMaterialAsyncTask(apiAuthenticationClient, domain);
                insertTask.execute();
            }
            for (FCustomer domain: listFCustomer) {
                InsertFCustomerAsyncTask insertTask = new InsertFCustomerAsyncTask(apiAuthenticationClient, domain);
                insertTask.execute();
            }
        }
    }
    public void progressStatus(){
        progressBar.setProgress(counter);
//            progressText.setText( ((progressBar.getProgress() /progressBar.getMax())*100) + "");
        progressText.setText( progressBar.getProgress()  + "");
        detilInfo.setText("Save Data: " +  counter + " of " + allDataSize );
        if (progressBar.getProgress() == progressBar.getMax())  btnSelesai.setVisibility(View.VISIBLE);

    }

    public class InsertFMaterialAsyncTask extends AsyncTask<Void, Void, Integer> {

        private ApiAuthenticationClient apiAuthenticationClient;

        FMaterial domain =  new FMaterial();
        private InsertFMaterialAsyncTask(ApiAuthenticationClient apiAuthenticationClient, FMaterial domain) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.domain = domain;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            fMaterialViewModel.insert(domain);
            counter++;
            return  0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressStatus();
        }
    }

    public class InsertFCustomerAsyncTask extends AsyncTask<Void, Void, Integer> {

        private ApiAuthenticationClient apiAuthenticationClient;

        FCustomer domain =  new FCustomer();
        private InsertFCustomerAsyncTask(ApiAuthenticationClient apiAuthenticationClient, FCustomer domain) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.domain = domain;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            fCustomerViewModel.insert(domain);
            counter++;
            return  0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressStatus();
        }

    }





}