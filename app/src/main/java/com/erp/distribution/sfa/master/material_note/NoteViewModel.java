package com.erp.distribution.sfa.master.material_note;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.erp.distribution.sfa.dao_repository.FCustomerRepository;
import com.erp.distribution.sfa.model.FCustomer;

import java.util.ArrayList;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private FCustomerRepository repository;
    private LiveData<List<FCustomer>> listFCustomerLive;
    private List<FCustomer> listFCustomer = new ArrayList<>();

    protected MutableLiveData<FCustomer> itemHeader;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new FCustomerRepository(application);
        listFCustomerLive = repository.getAllFCustomerLive();

    }
    public FCustomer insert(FCustomer fCustomer) {
        repository.insert(fCustomer);
        return fCustomer;
    }
    public FCustomer update(FCustomer fCustomer) {
        repository.update(fCustomer);
        return fCustomer;
    }
    public FCustomer delete(FCustomer fCustomer) {
        repository.delete(fCustomer);
        return fCustomer;
    }
    public void deleteAllFCustomer() {
        repository.deleteAllFCustomer();
    }
    public LiveData<List<FCustomer>> getAllFCustomerLive() {
        return listFCustomerLive;
    }
    public List<FCustomer> getAllFCustomer() {
        return repository.getAllFCustomer();
    }

    public LiveData<FCustomer> getItemHeader() {
        return itemHeader;
    }
}
