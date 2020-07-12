package com.erp.distribution.sfa.master.customer2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erp.distribution.R;
import com.erp.distribution.sfa.model.FCustomer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.ButterKnife;

public class Customer2Activity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    NoteViewModel noteViewModel;

    private NoteAdapter adapter = new NoteAdapter();
    RecyclerView recyclerView;

    List<FCustomer> listSource = new ArrayList<>();
    String searchText = "";

    private ConstraintLayout constrainOrCoordinatorRootLayout;

    interface  OnViewListener{
        void aksiButtonFormSave();
        void aksiTableItemClick(FCustomer note);
    }
    OnViewListener onViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer2);

        ButterKnife.bind(this);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Display icon in the toolbar
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        constrainOrCoordinatorRootLayout = findViewById(R.id.coordinatorLayout);



//        noteViewModel = noteController.noteViewModel;
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //Tambah Devider dibawah setiap Item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);


        initListener();
    }

    public void initListener(){

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FCustomer note) {
                onViewListener.aksiTableItemClick(note);
            }
        });

        noteViewModel.getAllFCustomerLive().observe(this, new Observer<List<FCustomer>>() {
            @Override
            public void onChanged(@Nullable List<FCustomer> notes) {
                listSource = new ArrayList<>(notes);
                adapter.submitList(notes.stream().filter(x->x.getCustname().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList()));

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                FCustomer deletedNote = noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar
                        .make(constrainOrCoordinatorRootLayout, " removed from cart!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        noteViewModel.insert(deletedNote);
                        // undo is selected, restore the deleted item
//                        mAdapter.restoreItem(deletedItem, deletedIndex);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

                Toast.makeText(Customer2Activity.this, "Note deleted", Toast.LENGTH_SHORT).show();
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
                Log.d("Ini Filter", newText);

                if (newText !=null) {

                   adapter.submitList(listSource.stream().filter(x->x.getCustname().toLowerCase().contains(newText.toLowerCase())).collect(Collectors.toList()));
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
//            case R.id.save_note:
//                return true;
//            case R.id.miProfile:
//                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



}