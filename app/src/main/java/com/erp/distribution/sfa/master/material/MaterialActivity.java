package com.erp.distribution.sfa.master.material;

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
import com.erp.distribution.sfa.model.FMaterial;
import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;
import com.erp.distribution.sfa.security_model.FUser;
import com.erp.distribution.sfa.service_rest.FMaterialServiceRest;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import butterknife.ButterKnife;

public class MaterialActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    ApiAuthenticationClient apiAuthenticationClient;

    FUser userActive = new FUser();

    MaterialViewModel fMaterialViewModel;

    private MaterialAdapter adapter = new MaterialAdapter();
    RecyclerView recyclerView;

    Map<Integer, FMaterial> mapSource = new HashMap();
    String searchText = "";

    private RelativeLayout rootLayout;

    interface  OnViewListener{
        void aksiButtonFormSave();
        void aksiTableItemClick(FMaterial note);
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


        fMaterialViewModel = new ViewModelProvider(this).get(MaterialViewModel.class);

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
//        apiAuthenticationClient.setUsername("bagus");
//        apiAuthenticationClient.setPassword("hacker");
//        apiAuthenticationClient.setBaseUrl("http://192.168.1.100:8989/rest");

//        userActive = apiAuthenticationClient.getUserActive();
//        apiAuthenticationClient.setUsername(userActive.getUsername());
//        apiAuthenticationClient.setPassword(userActive.getPlainPassword());

        initListener();
    }

    public void initListener(){
//        FloatingActionButton buttonAddFMaterial = findViewById(R.id.button_add_note);
//        buttonAddFMaterial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddEditFMaterialActivity.class);
//                startActivityForResult(intent, ADD_NOTE_REQUEST);
//            }
//        });

//        adapter.setOnItemClickListener(new FMaterialAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(FMaterial note) {
//                onViewListener.aksiTableItemClick(note);
//            }
//        });

        for (FMaterial fMaterial: fMaterialViewModel.getAllFMaterial()) {
            mapSource.put(fMaterial.getId(), fMaterial);
        }
        adapter.submitList(new ArrayList<>(mapSource.values()));

//        noteViewModel.getAllFMaterialLive().observe(this, new Observer<List<FMaterial>>() {
//            @Override
//            public void onChanged(@Nullable List<FMaterial> notes) {
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

                FMaterial deletedFMaterial = fMaterialViewModel.delete(adapter.getFMaterialAt(viewHolder.getAdapterPosition()));
                mapSource.remove(deletedFMaterial.getId()) ;
                adapter.submitList(new ArrayList<>(mapSource.values()));

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar
                        .make(rootLayout, "Terhapus dari daftar!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fMaterialViewModel.insert(deletedFMaterial);
                        mapSource.put(deletedFMaterial.getId(), deletedFMaterial);
                        adapter.submitList(new ArrayList<>(mapSource.values()));

                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

                Toast.makeText(MaterialActivity.this, "FMaterial deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_menu, menu);

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

                   adapter.submitList(mapSource.values().stream().filter(x->x.getPname().toLowerCase().contains(newText.toLowerCase())).collect(Collectors.toList()));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
//            FMaterial domain = (FMaterial) data.getSerializableExtra(AddEditFMaterialActivity.DOMAIN);
//            noteViewModel.insert(domain);
//            Toast.makeText(this, "FMaterial saved", Toast.LENGTH_SHORT).show();
//        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra(AddEditFMaterialActivity.EXTRA_ID, -1);
//            if (id == -1) {
//                Toast.makeText(this, "FMaterial can't be updated", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            FMaterial domain = (FMaterial) data.getSerializableExtra(AddEditFMaterialActivity.DOMAIN);
//            domain.setId(id);
//            noteViewModel.update(domain);
//            Toast.makeText(this, "FMaterial updated", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "FMaterial not saved", Toast.LENGTH_SHORT).show();
//        }
    }

    public void formAddEditShow(FMaterial note){
//        Intent intent = new Intent(MainActivity.this, AddEditFMaterialActivity.class);
//        intent.putExtra(AddEditFMaterialActivity.EXTRA_ID, note.getId());
//        intent.putExtra(AddEditFMaterialActivity.DOMAIN, note);
//        startActivityForResult(intent, EDIT_NOTE_REQUEST);

    }


    void syncronizeWithRestServer(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");

        Log.d("Syncron", ">>>>>> Start Syncron dengann Server");

        FMaterialServiceRest serviceRest = new FMaterialServiceRest(this);

        List<FMaterial> customers = new ArrayList<>();
        List<FMaterial> list = new ArrayList<>();
        for (FMaterial domain: serviceRest.getAllFMaterialByDivision(userActive.getFdivisionBean())) {
            fMaterialViewModel.insert(domain);

//            list.add(domain);
//            Log.d("Insert ++++ ", domain.getCustname());
//
//            FMaterial customer = new FMaterial();
//            customer.setUser(domain.getCustname() );
//            customer.setSubject(domain.getCustno());
//            customer.setDate(sdf.format(domain.getCreated()));
//            customer.setPreview(domain.getFdivisionBean().toString());
//
//            customers.add(domain);

        }
//        customers.sort(Comparator.comparing(FMaterial::getCustname));
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
                fMaterialViewModel.deleteAllFMaterial();
            }
        });
        alert.getButtonCancel().setOnClickListener((View view) ->{
            alert.dismiss();
        });

        alert.showDialog();

    }


}