package com.erp.distribution.sfa.transaksi.sales_order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erp.distribution.R;
import com.erp.distribution.sfa.common_utils.AlertDialogWarning;
import com.erp.distribution.sfa.master.material.MaterialViewModel;
import com.erp.distribution.sfa.model.FtSalesh;
import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;
import com.erp.distribution.sfa.security_model.FUser;
import com.erp.distribution.sfa.service_rest.FtSaleshServiceRest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SalesOrderActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    ApiAuthenticationClient apiAuthenticationClient;

    FUser userActive = new FUser();

    SalesOrderViewModel salesOrderViewModel;
    MaterialViewModel materialViewModel;

    private SalesOrderAdapter adapter = new SalesOrderAdapter();
    RecyclerView recyclerView;

    Map<Long, FtSalesh> mapSource = new HashMap();
    String searchText = "";


    @BindView(R.id.fab_add_new)
    FloatingActionButton fab_AddNew;

    private RelativeLayout rootLayout;

    interface  OnViewListener{
        void aksiButtonFormSave();
        void aksiTableItemClick(FtSalesh note);
    }
    OnViewListener onViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_template1);
        rootLayout = findViewById(R.id.rootLayout);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        salesOrderViewModel = new ViewModelProvider(this).get(SalesOrderViewModel.class);

        recyclerView = findViewById(R.id.recycler_view_customer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Tambah Devider dibawah setiap Item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);


        inizialize();
    }

    void inizialize(){
        apiAuthenticationClient = ApiAuthenticationClient.getInstance();

        initListener();
    }

    public void initListener(){


//        adapter.setOnItemClickListener(new FtSaleshAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(FtSalesh note) {
//                onViewListener.aksiTableItemClick(note);
//            }
//        });

        for (FtSalesh ftSalesh: salesOrderViewModel.getAllFtSalesh()) {
            mapSource.put(ftSalesh.getRefno(), ftSalesh);
        }
        adapter.submitList(new ArrayList<>(mapSource.values()));

//        noteViewModel.getAllFtSaleshLive().observe(this, new Observer<List<FtSalesh>>() {
//            @Override
//            public void onChanged(@Nullable List<FtSalesh> notes) {
//
//                listSource = new ArrayList<>(notes);
//                adapter.submitList(notes.stream().filter(x->x.getCustname().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList()));
//
//            }
//        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                FtSalesh deletedFtSalesh = salesOrderViewModel.delete(adapter.getFtSaleshAt(viewHolder.getAdapterPosition()));
                mapSource.remove(deletedFtSalesh.getRefno()) ;
                adapter.submitList(new ArrayList<>(mapSource.values()));

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar
                        .make(rootLayout, "Terhapus dari daftar!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salesOrderViewModel.insert(deletedFtSalesh);
                        mapSource.put(deletedFtSalesh.getRefno(), deletedFtSalesh);
                        adapter.submitList(new ArrayList<>(mapSource.values()));

                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

                Toast.makeText(SalesOrderActivity.this, "FtSalesh deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_menu_template1, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText = newText;
//                Log.d("Ini Filter", newText);
                if (newText !=null) {

                   adapter.submitList(mapSource.values().stream().filter(x->x.getOrderno().toLowerCase().contains(newText.toLowerCase())).collect(Collectors.toList()));
                   return true;
                }
                return false;
            }
        });


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.customer_scyncronize:
                syncronizeWithRestServer();
                return true;
            case R.id.customer_deleteall:
                deleteAllFromDb();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fab_add_new)
    void addNewForm(){
        Intent intent = new Intent(SalesOrderActivity.this, SalesOrderFormActivity.class);
        intent.putExtra(SalesOrderFormActivity.EXTRA_ID, salesOrderViewModel.itemHeader.getRefno());
        intent.putExtra(SalesOrderFormActivity.EXTRA_OBJECT, salesOrderViewModel.itemHeader);
        startActivityForResult(intent, EDIT_NOTE_REQUEST);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
//            FtSalesh domain = (FtSalesh) data.getSerializableExtra(AddEditFtSaleshActivity.DOMAIN);
//            noteViewModel.insert(domain);
//            Toast.makeText(this, "FtSalesh saved", Toast.LENGTH_SHORT).show();
//        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra(AddEditFtSaleshActivity.EXTRA_ID, -1);
//            if (id == -1) {
//                Toast.makeText(this, "FtSalesh can't be updated", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            FtSalesh domain = (FtSalesh) data.getSerializableExtra(AddEditFtSaleshActivity.DOMAIN);
//            domain.setId(id);
//            noteViewModel.update(domain);
//            Toast.makeText(this, "FtSalesh updated", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "FtSalesh not saved", Toast.LENGTH_SHORT).show();
//        }
    }


    void syncronizeWithRestServer(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");


        FtSaleshServiceRest serviceRest = new FtSaleshServiceRest(this);

        List<FtSalesh> customers = new ArrayList<>();
        List<FtSalesh> list = new ArrayList<>();
        for (FtSalesh domain: serviceRest.getAllFtSaleshByDivision(userActive.getFdivisionBean())) {
            salesOrderViewModel.insert(domain);

//            list.add(domain);
//            Log.d("Insert ++++ ", domain.getCustname());
//
//            FtSalesh customer = new FtSalesh();
//            customer.setUser(domain.getCustname() );
//            customer.setSubject(domain.getCustno());
//            customer.setDate(sdf.format(domain.getCreated()));
//            customer.setPreview(domain.getFdivisionBean().toString());
//
//            customers.add(domain);

        }
//        customers.sort(Comparator.comparing(FtSalesh::getCustname));
//        customerAdapter.submitFreshRvList(customers);
//        customerAdapter.notifyDataSetChanged();
//
//        setDisplayView_FromDb();
    }


    public void deleteAllFromDb(){
        AlertDialogWarning alert = new AlertDialogWarning(this, "Yakin Menghapus Semua Data?");
        alert.getButtonOke().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                salesOrderViewModel.deleteAllFtSalesh();
            }
        });
        alert.getButtonCancel().setOnClickListener((View view) ->{
            alert.dismiss();
        });

        alert.showDialog();

    }


}