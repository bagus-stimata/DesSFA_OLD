package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FExpedisiDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FExpedisi;
import com.erp.distribution.sfa.model.FExpedisi;

import java.util.List;

public class FExpedisiRepository {

    /**
     * @param fExpedisi
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FExpedisiDao fExpedisiDao;
    private LiveData<List<FExpedisi>> listFExpedisiLive;
    private List<FExpedisi> listFExpedisi;
    public FExpedisiRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fExpedisiDao = database.fExpedisiDao();
        listFExpedisiLive = fExpedisiDao.getAllFExpedisiLive();
    }

    public void insert(FExpedisi fExpedisi) {
        database.fExpedisiDao().insert(fExpedisi);
    }
    public void update(FExpedisi fExpedisi) {
        database.fExpedisiDao().update(fExpedisi);
    }
    public void delete(FExpedisi fExpedisi) {
        database.fExpedisiDao().delete(fExpedisi);
    }

    public void deleteAllFExpedisi() {
        database.fExpedisiDao().deleteAllFExpedisi();
    }
   
   
    public LiveData<List<FExpedisi>> getAllFExpedisiLive() {
        return listFExpedisiLive;
    }
    public List<FExpedisi> getAllFExpedisi() {
        return database.fExpedisiDao().getAllFExpedisi();
    }
    public List<FExpedisi> getAllFExpedisiById(Integer id) {
        return database.fExpedisiDao().getAllById(id);
    }
    public List<FExpedisi> getAllFExpedisiByDivision(Integer id) {
        return database.fExpedisiDao().getAllByDivision(id);
    }





}
