package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FMaterialGroup1Dao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FMaterialGroup1;
import com.erp.distribution.sfa.model.FMaterialGroup1;

import java.util.List;

public class FMaterialGroup1Repository {

    /**
     * @param fMaterialGroup1
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FMaterialGroup1Dao fMaterialGroup1Dao;
    private LiveData<List<FMaterialGroup1>> listFMaterialGroup1Live;
    private List<FMaterialGroup1> listFMaterialGroup1;
    public FMaterialGroup1Repository(Application application) {
        database = AppDatabase.getInstance(application);
        fMaterialGroup1Dao = database.fMaterialGroup1Dao();
        listFMaterialGroup1Live = fMaterialGroup1Dao.getAllFMaterialGroup1Live();
    }

    public void insert(FMaterialGroup1 fMaterialGroup1) {
        database.fMaterialGroup1Dao().insert(fMaterialGroup1);
    }
    public void update(FMaterialGroup1 fMaterialGroup1) {
        database.fMaterialGroup1Dao().update(fMaterialGroup1);
    }
    public void delete(FMaterialGroup1 fMaterialGroup1) {
        database.fMaterialGroup1Dao().delete(fMaterialGroup1);
    }

    public void deleteAllFMaterialGroup1() {
        database.fMaterialGroup1Dao().deleteAllFMaterialGroup1();
    }


    public LiveData<List<FMaterialGroup1>> getAllFMaterialGroup1Live() {
        return listFMaterialGroup1Live;
    }
    public List<FMaterialGroup1> getAllFMaterialGroup1() {
        return database.fMaterialGroup1Dao().getAllFMaterialGroup1();
    }
    public List<FMaterialGroup1> getAllFMaterialGroup1ById(Integer id) {
        return database.fMaterialGroup1Dao().getAllById(id);
    }
    public List<FMaterialGroup1> getAllFMaterialGroup1ByDivision(Integer id) {
        return database.fMaterialGroup1Dao().getAllByDivision(id);
    }





}
