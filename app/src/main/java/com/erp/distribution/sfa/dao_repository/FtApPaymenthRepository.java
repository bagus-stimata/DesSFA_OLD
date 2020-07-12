package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtApPaymenthDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtApPaymenth;
import com.erp.distribution.sfa.model.FtApPaymenth;

import java.util.List;

public class FtApPaymenthRepository {

    /**
     * @param ftApPaymenth
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtApPaymenthDao ftApPaymenthDao;
    private LiveData<List<FtApPaymenth>> listFtApPaymenthLive;
    private List<FtApPaymenth> listFtApPaymenth;
    public FtApPaymenthRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftApPaymenthDao = database.ftApPaymenthDao();
        listFtApPaymenthLive = ftApPaymenthDao.getAllFtApPaymenthLive();
    }

    public void insert(FtApPaymenth ftApPaymenth) {
        database.ftApPaymenthDao().insert(ftApPaymenth);
    }
    public void update(FtApPaymenth ftApPaymenth) {
        database.ftApPaymenthDao().update(ftApPaymenth);
    }
    public void delete(FtApPaymenth ftApPaymenth) {
        database.ftApPaymenthDao().delete(ftApPaymenth);
    }

    public void deleteAllFtApPaymenth() {
        database.ftApPaymenthDao().deleteAllFtApPaymenth();
    }
   
    public LiveData<List<FtApPaymenth>> getAllFtApPaymenthLive() {
        return listFtApPaymenthLive;
    }
    public List<FtApPaymenth> getAllFtApPaymenth() {
        return database.ftApPaymenthDao().getAllFtApPaymenth();
    }
    public List<FtApPaymenth> getAllFtApPaymenthById(Long refno) {
        return database.ftApPaymenthDao().getAllById(refno);
    }
    public List<FtApPaymenth> getAllFtApPaymenthByDivision(Integer id) {
        return database.ftApPaymenthDao().getAllByDivision(id);
    }



}
