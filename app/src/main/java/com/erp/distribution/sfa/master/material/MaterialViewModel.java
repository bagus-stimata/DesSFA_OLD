package com.erp.distribution.sfa.master.material;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.erp.distribution.sfa.dao_repository.FMaterialRepository;
import com.erp.distribution.sfa.model.FMaterial;

import java.util.ArrayList;
import java.util.List;

public class MaterialViewModel extends AndroidViewModel {
    private FMaterialRepository repository;
    private LiveData<List<FMaterial>> listFMaterialLive;
    private List<FMaterial> listFMaterial = new ArrayList<>();

    protected MutableLiveData<FMaterial> itemHeader;

    public MaterialViewModel(@NonNull Application application) {
        super(application);
        repository = new FMaterialRepository(application);
        listFMaterialLive = repository.getAllFMaterialLive();

    }
    public FMaterial insert(FMaterial fMaterial) {
        repository.insert(fMaterial);
        return fMaterial;
    }
    public FMaterial update(FMaterial fMaterial) {
        repository.update(fMaterial);
        return fMaterial;
    }
    public FMaterial delete(FMaterial fMaterial) {
        repository.delete(fMaterial);
        return fMaterial;
    }
    public void deleteAllFMaterial() {
        repository.deleteAllFMaterial();
    }
    public LiveData<List<FMaterial>> getAllFMaterialLive() {
        return listFMaterialLive;
    }
    public List<FMaterial> getAllFMaterial() {
        return repository.getAllFMaterial();
    }

    public LiveData<FMaterial> getItemHeader() {
        return itemHeader;
    }
}
