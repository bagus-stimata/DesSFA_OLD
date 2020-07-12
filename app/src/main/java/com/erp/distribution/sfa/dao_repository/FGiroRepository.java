package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FGiroDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FGiro;
import com.erp.distribution.sfa.model.FGiro;

import java.util.List;

public class FGiroRepository {

    /**
     * @param fGiro
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FGiroDao fGiroDao;
    private LiveData<List<FGiro>> listFGiroLive;
    private List<FGiro> listFGiro;
    public FGiroRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fGiroDao = database.fGiroDao();
        listFGiroLive = fGiroDao.getAllFGiroLive();
    }

    public void insert(FGiro fGiro) {
        database.fGiroDao().insert(fGiro);
    }
    public void update(FGiro fGiro) {
        database.fGiroDao().update(fGiro);
    }
    public void delete(FGiro fGiro) {
        database.fGiroDao().delete(fGiro);
    }

    public void deleteAllFGiro() {
        database.fGiroDao().deleteAllFGiro();
    }
   
   
    public LiveData<List<FGiro>> getAllFGiroLive() {
        return listFGiroLive;
    }
    public List<FGiro> getAllFGiro() {
        return database.fGiroDao().getAllFGiro();
    }
    public List<FGiro> getAllFGiroById(Integer id) {
        return database.fGiroDao().getAllById(id);
    }
    public List<FGiro> getAllFGiroByDivision(Integer id) {
        return database.fGiroDao().getAllByDivision(id);
    }





}
