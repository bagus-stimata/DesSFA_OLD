package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FAreaDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FArea;

import java.util.List;

public class FAreaRepository {

    /**
     * @param fArea
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FAreaDao fAreaDao;
    private LiveData<List<FArea>> listFAreaLive;
    private List<FArea> listFArea;
    public FAreaRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fAreaDao = database.fAreaDao();
        listFAreaLive = fAreaDao.getAllFAreaLive();
    }

    public void insert(FArea fArea) {
        database.fAreaDao().insert(fArea);
    }
    public void update(FArea fArea) {
        database.fAreaDao().insert(fArea);
    }
    public void delete(FArea fArea) {
        database.fAreaDao().update(fArea);
    }

    public void deleteAllFArea() {
        database.fAreaDao().deleteAllFArea();
    }

    public LiveData<List<FArea>> getAllFAreaLive() {
        return listFAreaLive;
    }
    public List<FArea> getAllFArea() {
        return database.fAreaDao().getAllFArea();
    }
    public List<FArea> getAllFAreaById(Integer id) {
        return database.fAreaDao().getAllById(id);
    }
    public List<FArea> getAllFAreaByDivision(Integer id) {
        return database.fAreaDao().getAllByDivision(id);
    }







}
