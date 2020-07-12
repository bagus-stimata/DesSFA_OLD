package com.erp.distribution;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.erp.distribution.sfa.dao_repository.FAreaRepository;
import com.erp.distribution.sfa.dao_repository.FCustomerGroupRepository;
import com.erp.distribution.sfa.dao_repository.FCustomerRepository;
import com.erp.distribution.sfa.dao_repository.FMaterialGroup3Repository;
import com.erp.distribution.sfa.dao_repository.FMaterialRepository;
import com.erp.distribution.sfa.dao_repository.FSubAreaRepository;
import com.erp.distribution.sfa.model.FCustomer;
import com.erp.distribution.sfa.model.FMaterial;
import com.erp.distribution.sfa.model.FMaterialGroup3;
import com.erp.distribution.sfa.security_model.FUser;
import com.erp.distribution.sfa.security_repository.FUserRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private FUserRepository repository;

    private FCustomerRepository fCustomerRepository;
    private FCustomerGroupRepository fCustomerGroupRepository;
    private FAreaRepository fAreaRepository;
    private FSubAreaRepository fSubAreaRepository;

    private FMaterialRepository fMaterialRepository;
    private FMaterialGroup3Repository fMaterialGroup3Repository;

    private LiveData<List<FUser>> listFUserLive;
    private List<FUser> listFUser = new ArrayList<>();

    protected MutableLiveData<FUser> itemHeader;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new FUserRepository(application);
        listFUserLive = repository.getAllFUserLive();

    }

    public FUser insert(FUser fUser) {
        repository.insert(fUser);
        return fUser;
    }

    public FUser update(FUser fUser) {
        repository.update(fUser);
        return fUser;
    }
    public FUser delete(FUser fUser) {
        repository.delete(fUser);
        return fUser;
    }
    public void deleteAllFUser() {
        repository.deleteAllFUser();
    }
    public LiveData<List<FUser>> getAllFUserLive() {
        return listFUserLive;
    }
    public List<FUser> getAllFUser() {
        return repository.getAllFUser();
    }

    public LiveData<FUser> getItemHeader() {
        return itemHeader;
    }
}
