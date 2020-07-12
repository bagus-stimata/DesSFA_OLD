package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtApPaymentdDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtApPaymentd;
import com.erp.distribution.sfa.model.FtApPaymentd;

import java.util.List;

public class FtApPaymentdRepository {

    /**
     * @param ftApPaymentd
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtApPaymentdDao ftApPaymentdDao;
    private LiveData<List<FtApPaymentd>> listFtApPaymentdLive;
    private List<FtApPaymentd> listFtApPaymentd;
    public FtApPaymentdRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftApPaymentdDao = database.ftApPaymentdDao();
        listFtApPaymentdLive = ftApPaymentdDao.getAllFtApPaymentdLive();
    }

    public void insert(FtApPaymentd ftApPaymentd) {
        database.ftApPaymentdDao().insert(ftApPaymentd);
    }
    public void update(FtApPaymentd ftApPaymentd) {
        database.ftApPaymentdDao().update(ftApPaymentd);
    }
    public void delete(FtApPaymentd ftApPaymentd) {
        database.ftApPaymentdDao().delete(ftApPaymentd);
    }

    public void deleteAllFtApPaymentd() {
        database.ftApPaymentdDao().deleteAllFtApPaymentd();
    }
   
    public LiveData<List<FtApPaymentd>> getAllFtApPaymentdLive() {
        return listFtApPaymentdLive;
    }
    public List<FtApPaymentd> getAllFtApPaymentd() {
        return database.ftApPaymentdDao().getAllFtApPaymentd();
    }
    public List<FtApPaymentd> getAllFtApPaymentdById(Long id) {
        return database.ftApPaymentdDao().getAllById(id);
    }
    public List<FtApPaymentd> getAllFtApPaymentdByDivision(Long id) {
        return database.ftApPaymentdDao().getAllByParentId(id);
    }





}
