package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtArPaymenthDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtArPaymenth;
import com.erp.distribution.sfa.model.FtArPaymenth;

import java.util.List;

public class FtArPaymenthRepository {

    /**
     * @param ftArPaymenth
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtArPaymenthDao ftArPaymenthDao;
    private LiveData<List<FtArPaymenth>> listFtArPaymenthLive;
    private List<FtArPaymenth> listFtArPaymenth;
    public FtArPaymenthRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftArPaymenthDao = database.ftArPaymenthDao();
        listFtArPaymenthLive = ftArPaymenthDao.getAllFtArPaymenthLive();
    }

    public void insert(FtArPaymenth ftArPaymenth) {
        database.ftArPaymenthDao().insert(ftArPaymenth);
    }
    public void update(FtArPaymenth ftArPaymenth) {
        database.ftArPaymenthDao().update(ftArPaymenth);
    }
    public void delete(FtArPaymenth ftArPaymenth) {
        database.ftArPaymenthDao().delete(ftArPaymenth);
    }

    public void deleteAllFtArPaymenth() {
        database.ftArPaymenthDao().deleteAllFtArPaymenth();
    }


    public LiveData<List<FtArPaymenth>> getAllFtArPaymenthLive() {
        return listFtArPaymenthLive;
    }
    public List<FtArPaymenth> getAllFtArPaymenth() {
        return database.ftArPaymenthDao().getAllFtArPaymenth();
    }
    public List<FtArPaymenth> getAllFtArPaymenthById(Long id) {
        return database.ftArPaymenthDao().getAllById(id);
    }
    public List<FtArPaymenth> getAllFtArPaymenthByDivision(Integer id) {
        return database.ftArPaymenthDao().getAllByDivision(id);
    }



}
