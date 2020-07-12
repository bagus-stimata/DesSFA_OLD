package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FUangMukaDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FUangMuka;
import com.erp.distribution.sfa.model.FUangMuka;

import java.util.List;

public class FUangMukaRepository {

    /**
     * @param fUangMuka
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FUangMukaDao fUangMukaDao;
    private LiveData<List<FUangMuka>> listFUangMukaLive;
    private List<FUangMuka> listFUangMuka;
    public FUangMukaRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fUangMukaDao = database.fUangMukaDao();
        listFUangMukaLive = fUangMukaDao.getAllFUangMukaLive();
    }

    public void insert(FUangMuka fUangMuka) {
        database.fUangMukaDao().insert(fUangMuka);
    }
    public void update(FUangMuka fUangMuka) {
        database.fUangMukaDao().update(fUangMuka);
    }
    public void delete(FUangMuka fUangMuka) {
        database.fUangMukaDao().delete(fUangMuka);
    }

    public void deleteAllFUangMuka() {
        database.fUangMukaDao().deleteAllFUangMuka();
    }

    public LiveData<List<FUangMuka>> getAllFUangMukaLive() {
        return listFUangMukaLive;
    }
    public List<FUangMuka> getAllFUangMuka() {
        return database.fUangMukaDao().getAllFUangMuka();
    }
    public List<FUangMuka> getAllFUangMukaById(Integer id) {
        return database.fUangMukaDao().getAllById(id);
    }
    public List<FUangMuka> getAllFUangMukaByDivision(Integer id) {
        return database.fUangMukaDao().getAllByDivision(id);
    }



}
