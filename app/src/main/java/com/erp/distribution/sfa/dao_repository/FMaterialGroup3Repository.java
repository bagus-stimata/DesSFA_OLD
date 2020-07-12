package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FMaterialGroup3Dao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FMaterialGroup3;
import com.erp.distribution.sfa.model.FMaterialGroup3;

import java.util.List;

public class FMaterialGroup3Repository {

    /**
     * @param fMaterialGroup3
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FMaterialGroup3Dao fMaterialGroup3Dao;
    private LiveData<List<FMaterialGroup3>> listFMaterialGroup3Live;
    private List<FMaterialGroup3> listFMaterialGroup3;
    public FMaterialGroup3Repository(Application application) {
        database = AppDatabase.getInstance(application);
        fMaterialGroup3Dao = database.fMaterialGroup3Dao();
        listFMaterialGroup3Live = fMaterialGroup3Dao.getAllFMaterialGroup3Live();
    }

    public void insert(FMaterialGroup3 fMaterialGroup3) {
        database.fMaterialGroup3Dao().insert(fMaterialGroup3);
    }
    public void update(FMaterialGroup3 fMaterialGroup3) {
        database.fMaterialGroup3Dao().update(fMaterialGroup3);
    }
    public void delete(FMaterialGroup3 fMaterialGroup3) {
        database.fMaterialGroup3Dao().delete(fMaterialGroup3);
    }

    public void deleteAllFMaterialGroup3() {
        database.fMaterialGroup3Dao().deleteAllFMaterialGroup3();
    }
   
   
    public LiveData<List<FMaterialGroup3>> getAllFMaterialGroup3Live() {
        return listFMaterialGroup3Live;
    }
    public List<FMaterialGroup3> getAllFMaterialGroup3() {
        return database.fMaterialGroup3Dao().getAllFMaterialGroup3();
    }
    public List<FMaterialGroup3> getAllFMaterialGroup3ById(Integer id) {
        return database.fMaterialGroup3Dao().getAllById(id);
    }
    public List<FMaterialGroup3> getAllFMaterialGroup3ByParentId(Integer id) {
        return database.fMaterialGroup3Dao().getAllByParentId(id);
    }




}
