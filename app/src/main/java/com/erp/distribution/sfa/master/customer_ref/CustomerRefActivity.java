package com.erp.distribution.sfa.master.customer_ref;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.erp.distribution.R;
import com.erp.distribution.sfa.model.FCustomer;
import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerRefActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.erp.distribution.master.customer.CustomerActivity.EXTRA_ID";
    public static final String EXTRA_OBJECT = "com.erp.distribution.master.customer.CustomerActivity.EXTRA_OBJECT";

    ApiAuthenticationClient apiAuthenticationClient;

    CustomerRefViewModel customerViewModel;

    private FCustomerRefAdapter customerAdapter;
    private ActionMode actionMode;

    private RelativeLayout rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_template1);

        customerViewModel = new ViewModelProvider(this).get(CustomerRefViewModel.class);

        rootLayout = findViewById(R.id.rootLayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        customerAdapter = new FCustomerAdapter(new ArrayList<>(FCustomers.fakeFCustomers()));
//        customerAdapter = new FCustomerAdapter(new ArrayList<>());

        final RecyclerView rv = findViewById(R.id.recycler_view_customer);
        rv.setAdapter(customerAdapter);

        //Tambah Devider dibawah setiap Item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider));
        rv.addItemDecoration(dividerItemDecoration);

//        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addFCustomer();
//                rv.scrollToPosition(0);
//            }
//        });

        ItemTouchHelper rvItemTouchHelper = new ItemTouchHelper(
                // below statement: used at move and sort
                // new ItemTouchHandler(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                //ItemTouchHelper.LEFT)
                new ItemTouchHandler(0,
                        ItemTouchHelper.LEFT)
        );

        rvItemTouchHelper.attachToRecyclerView(rv);

//        customerAdapter.setListener(new FCustomerAdapter.FCustomerAdapterListener() {
//            @Override
//            public void onItemClick(int position) {
//                enableActionMode(position);
//            }
//
//            @Override
//            public void onItemLongClick(int position) {
//                enableActionMode(position);
//            }
//        });

        inizialize();

    }

    void inizialize(){

        customerViewModel.getAllFCustomerLive().observe(this, new Observer<List<FCustomer>>() {
            @Override
            public void onChanged(@Nullable List<FCustomer> listDomain) {
//                customerAdapter.submitList(listDomain);
            }
        });

        apiAuthenticationClient = ApiAuthenticationClient.getInstance();
//        apiAuthenticationClient.setUsername("bagus");
//        apiAuthenticationClient.setPassword("hacker");
//        apiAuthenticationClient.setBaseUrl("http://192.168.1.100:8989/rest");
//        apiAuthenticationClient.setBaseUrl("http://ssp-surabaya.ddns.net:8989/rest/");

        setDisplayView_FromDb();
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
                Log.d("Ini Filter", newText);
                if (newText !=null ) {
//                    customerAdapter.getFilter().filter(newText);

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
            case R.id.customer_scyncronize:
                scyncronizeWithRestServer();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void enableActionMode(int position) {
//        if (actionMode == null)
//            actionMode = startSupportActionMode(new ActionMode.Callback() {
//                @Override
//                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
//                    return true;
//                }
//
//                @Override
//                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                    return false;
//                }
//
//                @Override
//                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                    if (item.getItemId() == R.id.action_delete) {
//                        customerAdapter.deleteFCustomers();
//                        mode.finish();
//                        return true;
//                    }
//                    return false;
//                }
//
//                @Override
//                public void onDestroyActionMode(ActionMode mode) {
//                    customerAdapter.selectedItems.clear();
//                    List<FCustomer> customers = customerAdapter.getListFiltered();
//                    for (FCustomer customer : customers) {
//                        if (customer.isSelected())
//                            customer.setSelected(false);
//                    }
//                    customerAdapter.notifyDataSetChanged();
//                    actionMode = null;
//                }
//            });
//
//        customerAdapter.toggleSelection(position);
//        final int size = customerAdapter.selectedItems.size();
//        if (size == 0) {
//            actionMode.finish();
//        } else {
//            actionMode.setTitle(size + "");
//            actionMode.invalidate();
//        }

    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback {

        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();

//            Collections.swap(customerAdapter.getListFiltered(), from, to);
//            customerAdapter.notifyItemMoved(from, to);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

//            FCustomer deleteObject = customerViewModel.delete(customerAdapter.getCustomerAt(viewHolder.getAdapterPosition()));

//            FCustomer deleteObject = customerAdapter.getCustomerAt(viewHolder.getAdapterPosition());
//            customerAdapter.getListFiltered().remove(viewHolder.getAdapterPosition());
//            customerAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//
//            // showing snack bar with Undo option
//            Snackbar snackbar = Snackbar
//                    .make(rootLayout, " Data Terhapus!", Snackbar.LENGTH_LONG);
//            snackbar.setAction("UNDO", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    deleteObject.setId(0);
////                    customerViewModel.insert(deleteObject);
//                    customerAdapter.getListFiltered().add(viewHolder.getAdapterPosition(), deleteObject);
////                    customerAdapter.getListFiltered().add( deleteObject);
//                    customerAdapter.notifyItemInserted(viewHolder.getAdapterPosition());
//
//                    // undo is selected, restore the deleted item
////                        mAdapter.restoreItem(deletedItem, deletedIndex);
//                }
//            });
//            snackbar.setActionTextColor(Color.YELLOW);
//            snackbar.show();

        }
    }


    void scyncronizeWithRestServer(){

        apiAuthenticationClient = ApiAuthenticationClient.getInstance();
        apiAuthenticationClient.setUsername("bagus");
        apiAuthenticationClient.setPassword("hacker");
        apiAuthenticationClient.setBaseUrl("http://192.168.1.100:8989/rest");

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");

        Log.d("Syncron", ">>>>>> Start Syncron dengann Server");

//        FCustomerServiceRest serviceRest = new FCustomerServiceRest(this);
//
//        List<FCustomer> customers = new ArrayList<>();
//        List<FCustomer> list = new ArrayList<>();
//        for (FCustomer domain: serviceRest.getAllFCustomerByDivision(7347268)) {
//            list.add(domain);
//            customerViewModel.insert(domain);
//            Log.d("Insert ++++ ", domain.getCustname());
//
////            FCustomer customer = new FCustomer();
////            customer.setUser(domain.getCustname() );
////            customer.setSubject(domain.getCustno());
////            customer.setDate(sdf.format(domain.getCreated()));
////            customer.setPreview(domain.getFdivisionBean().toString());
//
//            customers.add(domain);
//
//        }
//        customers.sort(Comparator.comparing(FCustomer::getCustname));
//        customerAdapter.submitFreshRvList(customers);
//        customerAdapter.notifyDataSetChanged();
//
//        setDisplayView_FromDb();
    }

    void setDisplayView_FromDb(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");

//        List<FCustomer> customers = new ArrayList<>();
//        for (FCustomer domain: customerViewModel.getAllFCustomer()) {
//            customers.add(domain);
//        }
//
//        customers.sort(Comparator.comparing(FCustomer::getCustname));
//        customerAdapter.submitFreshRvList(customers);
//        customerAdapter.notifyDataSetChanged();

    }

    public void deleteAllFromDb(){
        customerViewModel.deleteAllFCustomer();
    }

}