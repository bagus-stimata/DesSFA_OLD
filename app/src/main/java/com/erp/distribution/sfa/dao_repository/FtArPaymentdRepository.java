package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtArPaymentdDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtArPaymentd;
import com.erp.distribution.sfa.model.FtArPaymentd;

import java.util.List;

public class FtArPaymentdRepository {

    /**
     * @param ftArPaymentd
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtArPaymentdDao ftArPaymentdDao;
    private LiveData<List<FtArPaymentd>> listFtArPaymentdLive;
    private List<FtArPaymentd> listFtArPaymentd;
    public FtArPaymentdRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftArPaymentdDao = database.ftArPaymentdDao();
        listFtArPaymentdLive = ftArPaymentdDao.getAllFtArPaymentdLive();
    }

    public void insert(FtArPaymentd ftArPaymentd) {
        database.ftArPaymentdDao().insert(ftArPaymentd);
    }
    public void update(FtArPaymentd ftArPaymentd) {
        database.ftArPaymentdDao().update(ftArPaymentd);
    }
    public void delete(FtArPaymentd ftArPaymentd) {
        database.ftArPaymentdDao().delete(ftArPaymentd);
    }

    public void deleteAllFtArPaymentd() {
        database.ftArPaymentdDao().deleteAllFtArPaymentd();
    }


    public LiveData<List<FtArPaymentd>> getAllFtArPaymentdLive() {
        return listFtArPaymentdLive;
    }
    public List<FtArPaymentd> getAllFtArPaymentd() {
        return database.ftArPaymentdDao().getAllFtArPaymentd();
    }
    public List<FtArPaymentd> getAllFtArPaymentdById(Long id) {
        return database.ftArPaymentdDao().getAllById(id);
    }
    public List<FtArPaymentd> getAllFtArPaymentdByParentId(Long id) {
        return database.ftArPaymentdDao().getAllByParentId(id);
    }




}
