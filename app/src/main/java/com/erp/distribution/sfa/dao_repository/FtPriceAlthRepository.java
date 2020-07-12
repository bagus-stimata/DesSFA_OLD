package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtPriceAlthDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtPriceAlth;
import com.erp.distribution.sfa.model.FtPriceAlth;

import java.util.List;

public class FtPriceAlthRepository {

    /**
     * @param ftPriceAlth
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtPriceAlthDao ftPriceAlthDao;
    private LiveData<List<FtPriceAlth>> listFtPriceAlthLive;
    private List<FtPriceAlth> listFtPriceAlth;
    public FtPriceAlthRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftPriceAlthDao = database.ftPriceAlthDao();
        listFtPriceAlthLive = ftPriceAlthDao.getAllFtPriceAlthLive();
    }

    public void insert(FtPriceAlth ftPriceAlth) {
        database.ftPriceAlthDao().insert(ftPriceAlth);
    }
    public void update(FtPriceAlth ftPriceAlth) {
        database.ftPriceAlthDao().update(ftPriceAlth);
    }
    public void delete(FtPriceAlth ftPriceAlth) {
        database.ftPriceAlthDao().delete(ftPriceAlth);
    }

    public void deleteAllFtPriceAlth() {
        database.ftPriceAlthDao().deleteAllFtPriceAlth();
    }


    public LiveData<List<FtPriceAlth>> getAllFtPriceAlthLive() {
        return listFtPriceAlthLive;
    }
    public List<FtPriceAlth> getAllFtPriceAlth() {
        return database.ftPriceAlthDao().getAllFtPriceAlth();
    }
    public List<FtPriceAlth> getAllFtPriceAlthById(Long id) {
        return database.ftPriceAlthDao().getAllById(id);
    }
    public List<FtPriceAlth> getAllFtPriceAlthByDivision(Integer id) {
        return database.ftPriceAlthDao().getAllByDivision(id);
    }




}
