package com.erp.distribution.sfa.transaksi.sales_order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.erp.distribution.sfa.dao_repository.FtSaleshRepository;
import com.erp.distribution.sfa.model.FCustomer;
import com.erp.distribution.sfa.model.FMaterial;
import com.erp.distribution.sfa.model.FSalesman;
import com.erp.distribution.sfa.model.FWarehouse;
import com.erp.distribution.sfa.model.FtSalesdItems;
import com.erp.distribution.sfa.model.FtSalesh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesOrderViewModel extends AndroidViewModel {
    protected FtSaleshRepository repository;
    protected LiveData<List<FtSalesh>> listFtSaleshLive;
    protected Map<Long, FtSalesh> mapFtSalesh = new HashMap<>();
    protected Map<Long, FtSalesdItems> mapFtSalesdItems = new HashMap<>();

    protected FtSalesh itemHeader = new FtSalesh();
    protected MutableLiveData<FtSalesh> itemHeaderLive;

    public SalesOrderViewModel(@NonNull Application application) {
        super(application);
        repository = new FtSaleshRepository(application);
        listFtSaleshLive = repository.getAllFtSaleshLive();
    }
    public FtSalesh insert(FtSalesh fMaterial) {
        repository.insert(fMaterial);
        return fMaterial;
    }
    public FtSalesh update(FtSalesh fMaterial) {
        repository.update(fMaterial);
        return fMaterial;
    }
    public FtSalesh delete(FtSalesh fMaterial) {
        repository.delete(fMaterial);
        return fMaterial;
    }
    public void deleteAllFtSalesh() {
        repository.deleteAllFtSalesh();
    }
    public LiveData<List<FtSalesh>> getAllFtSaleshLive() {
        return listFtSaleshLive;
    }
    public List<FtSalesh> getAllFtSalesh() {
        return repository.getAllFtSalesh();
    }



}
