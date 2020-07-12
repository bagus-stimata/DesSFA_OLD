package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtOpnamehDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtOpnameh;
import com.erp.distribution.sfa.model.FtOpnameh;

import java.util.List;

public class FtOpnamehRepository {

    /**
     * @param ftOpnameh
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtOpnamehDao ftOpnamehDao;
    private LiveData<List<FtOpnameh>> listFtOpnamehLive;
    private List<FtOpnameh> listFtOpnameh;
    public FtOpnamehRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftOpnamehDao = database.ftOpnamehDao();
        listFtOpnamehLive = ftOpnamehDao.getAllFtOpnamehLive();
    }

    public void insert(FtOpnameh ftOpnameh) {
        database.ftOpnamehDao().insert(ftOpnameh);
    }
    public void update(FtOpnameh ftOpnameh) {
        database.ftOpnamehDao().update(ftOpnameh);
    }
    public void delete(FtOpnameh ftOpnameh) {
        database.ftOpnamehDao().delete(ftOpnameh);
    }

    public void deleteAllFtOpnameh() {
        database.ftOpnamehDao().deleteAllFtOpnameh();
    }


    public LiveData<List<FtOpnameh>> getAllFtOpnamehLive() {
        return listFtOpnamehLive;
    }
    public List<FtOpnameh> getAllFtOpnameh() {
        return database.ftOpnamehDao().getAllFtOpnameh();
    }
    public List<FtOpnameh> getAllFtOpnamehById(Long id) {
        return database.ftOpnamehDao().getAllById(id);
    }
    public List<FtOpnameh> getAllFtOpnamehByDivision(Integer id) {
        return database.ftOpnamehDao().getAllByDivision(id);
    }




}
