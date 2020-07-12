package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FMaterialDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FMaterial;
import com.erp.distribution.sfa.model.FMaterial;

import java.util.List;

public class FMaterialRepository {

    /**
     * @param fMaterial
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FMaterialDao fMaterialDao;
    private LiveData<List<FMaterial>> listFMaterialLive;
    private List<FMaterial> listFMaterial;
    public FMaterialRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fMaterialDao = database.fMaterialDao();
        listFMaterialLive = fMaterialDao.getAllFMaterialLive();
    }

    public void insert(FMaterial fMaterial) {
        database.fMaterialDao().insert(fMaterial);
    }
    public void update(FMaterial fMaterial) {
        database.fMaterialDao().update(fMaterial);
    }
    public void delete(FMaterial fMaterial) {
        database.fMaterialDao().delete(fMaterial);
    }

    public void deleteAllFMaterial() {
        database.fMaterialDao().deleteAllFMaterial();
    }
   
   
    public LiveData<List<FMaterial>> getAllFMaterialLive() {
        return listFMaterialLive;
    }
    public List<FMaterial> getAllFMaterial() {
        return database.fMaterialDao().getAllFMaterial();
    }
    public List<FMaterial> getAllFMaterialById(Integer id) {
        return database.fMaterialDao().getAllById(id);
    }
    public List<FMaterial> getAllFMaterialByDivision(Integer id) {
        return database.fMaterialDao().getAllByDivision(id);
    }





}
