package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtPurchasehDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtPurchaseh;
import com.erp.distribution.sfa.model.FtPurchaseh;

import java.util.List;

public class FtPurchasehRepository {

    /**
     * @param ftPurchaseh
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtPurchasehDao ftPurchasehDao;
    private LiveData<List<FtPurchaseh>> listFtPurchasehLive;
    private List<FtPurchaseh> listFtPurchaseh;
    public FtPurchasehRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftPurchasehDao = database.ftPurchasehDao();
        listFtPurchasehLive = ftPurchasehDao.getAllFtPurchasehLive();
    }

    public void insert(FtPurchaseh ftPurchaseh) {
        database.ftPurchasehDao().insert(ftPurchaseh);
    }
    public void update(FtPurchaseh ftPurchaseh) {
        database.ftPurchasehDao().update(ftPurchaseh);
    }
    public void delete(FtPurchaseh ftPurchaseh) {
        database.ftPurchasehDao().delete(ftPurchaseh);
    }

    public void deleteAllFtPurchaseh() {
        database.ftPurchasehDao().deleteAllFtPurchaseh();
    }

    public LiveData<List<FtPurchaseh>> getAllFtPurchasehLive() {
        return listFtPurchasehLive;
    }
    public List<FtPurchaseh> getAllFtPurchaseh() {
        return database.ftPurchasehDao().getAllFtPurchaseh();
    }
    public List<FtPurchaseh> getAllFtPurchasehById(Long id) {
        return database.ftPurchasehDao().getAllById(id);
    }
    public List<FtPurchaseh> getAllFtPurchasehByDivision(Integer id) {
        return database.ftPurchasehDao().getAllByDivision(id);
    }



}
