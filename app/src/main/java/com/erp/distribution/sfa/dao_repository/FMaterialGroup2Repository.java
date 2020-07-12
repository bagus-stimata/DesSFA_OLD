package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FMaterialGroup2Dao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FMaterialGroup2;
import com.erp.distribution.sfa.model.FMaterialGroup2;

import java.util.List;

public class FMaterialGroup2Repository {

    /**
     * @param fMaterialGroup2
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FMaterialGroup2Dao fMaterialGroup2Dao;
    private LiveData<List<FMaterialGroup2>> listFMaterialGroup2Live;
    private List<FMaterialGroup2> listFMaterialGroup2;
    public FMaterialGroup2Repository(Application application) {
        database = AppDatabase.getInstance(application);
        fMaterialGroup2Dao = database.fMaterialGroup2Dao();
        listFMaterialGroup2Live = fMaterialGroup2Dao.getAllFMaterialGroup2Live();
    }

    public void insert(FMaterialGroup2 fMaterialGroup2) {
        database.fMaterialGroup2Dao().insert(fMaterialGroup2);
    }
    public void update(FMaterialGroup2 fMaterialGroup2) {
        database.fMaterialGroup2Dao().update(fMaterialGroup2);
    }
    public void delete(FMaterialGroup2 fMaterialGroup2) {
        database.fMaterialGroup2Dao().delete(fMaterialGroup2);
    }

    public void deleteAllFMaterialGroup2() {
        database.fMaterialGroup2Dao().deleteAllFMaterialGroup2();
    }
    
    
    public LiveData<List<FMaterialGroup2>> getAllFMaterialGroup2Live() {
        return listFMaterialGroup2Live;
    }
    public List<FMaterialGroup2> getAllFMaterialGroup2() {
        return database.fMaterialGroup2Dao().getAllFMaterialGroup2();
    }
    public List<FMaterialGroup2> getAllFMaterialGroup2ById(Integer id) {
        return database.fMaterialGroup2Dao().getAllById(id);
    }
    public List<FMaterialGroup2> getAllFMaterialGroup2ByParentId(Integer id) {
        return database.fMaterialGroup2Dao().getAllByParentId(id);
    }





}
