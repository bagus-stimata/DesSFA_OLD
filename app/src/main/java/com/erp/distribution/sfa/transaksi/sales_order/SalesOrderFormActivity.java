package com.erp.distribution.sfa.transaksi.sales_order;

import android.app.SearchableInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.erp.distribution.R;
import com.erp.distribution.sfa.common_utils.SearchableDialogFMaterial;
import com.erp.distribution.sfa.master.material.MaterialViewModel;
import com.erp.distribution.sfa.model.FMaterial;
import com.erp.distribution.sfa.model.FtSalesh;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SalesOrderFormActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.erp.distribution.sfa.transaksi.sales_order.SalesOrderFormActivity.EXTRA_ID";
    public static final String EXTRA_OBJECT = "com.erp.distribution.sfa.transaksi.sales_order.SalesOrderFormActivity.EXTRA_OBJECT";

    SalesOrderViewModel salesOrderViewModel;
    MaterialViewModel materialViewModel;

    @BindView(R.id.text_order_no)
    EditText editTextOrderNo;
    @BindView(R.id.text_invoice_no)
    EditText editTextInvoiceNo;

    private MaterialSpinnerAdapter materialAdapter;

    FtSalesh domain = new FtSalesh();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_form);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        salesOrderViewModel = new ViewModelProvider(this).get(SalesOrderViewModel.class);
        materialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("EDIT SO");
            FtSalesh note = (FtSalesh) intent.getSerializableExtra(EXTRA_OBJECT);
//            editTextTitle.setText(note.getInvoiceno());

        } else {
//            setTitle("Add FtSalesh");
        }

        initialize();

    }

    @BindView(R.id.spinner_customer)
    Spinner spinnerCustomer;

    void initialize(){
        ArrayList<String> numberList = new ArrayList<>();
        numberList.add("Select Number");
        numberList.add("Satu");
        numberList.add("Dua");
        numberList.add("Tiga");
        numberList.add("Empat");


        ArrayList<FMaterial> listFMaterial =new ArrayList<>( materialViewModel.getAllFMaterial());
        materialAdapter = new MaterialSpinnerAdapter(this, listFMaterial);

//        spinnerCustomer.setAdapter(new ArrayAdapter<>(SalesOrderFormActivity.this, android.R.layout.simple_spinner_dropdown_item, numberList));
        spinnerCustomer.setAdapter(materialAdapter);


        initListener();

    }
    void initListener(){
        spinnerCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position ==0) {

                }else {
                    String sNumber = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu_template1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // close this activity and return to preview activity (if there is any)

            case R.id.menu_save:
                saveFtSalesh();
            case R.id.fab_save:
                saveFtSalesh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }

    private void saveFtSalesh() {

        writeBinder();
        if (domain.getOrderno().isEmpty() || domain.getInvoiceno().isEmpty()) {
            Toast.makeText(this, "Data masih belum lengkap", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_OBJECT, domain);
        long id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();

    }

    private void writeBinder(){
        domain.setOrderno(editTextOrderNo.getText().toString());
        domain.setInvoiceno(editTextInvoiceNo.getText().toString());
    }

//    @OnItem(R.id.spinner_customer)
//    void selectSearchabelSpinner(){
//        Toast.makeText(this, "Oke tak pilih ya", Toast.LENGTH_SHORT).show();
//
//    }

    @OnClick(R.id.button)
    void buttonKlik(){
        SearchableDialogFMaterial searcableDialogFMaterial =new SearchableDialogFMaterial(this);
        searcableDialogFMaterial.showDialog();

    }

}