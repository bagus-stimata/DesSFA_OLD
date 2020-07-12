package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtPricehDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtPricedItems;
import com.erp.distribution.sfa.model.FtPriceh;

import java.util.List;

public class FtPricedItemsRepository {

    /**
     * @param ftPriceh
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtPricehDao ftPricehDao;
    private LiveData<List<FtPriceh>> listFtPricehLive;
    private List<FtPriceh> listFtPriceh;
    public FtPricedItemsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftPricehDao = database.ftPricehDao();
        listFtPricehLive = ftPricehDao.getAllFtPricehLive();
    }

    public void insert(FtPricedItems ftPricedItems) {
        database.ftPricedItemsDao().insert(ftPricedItems);
    }
    public void update(FtPricedItems ftPricedItems) {
        database.ftPricedItemsDao().update(ftPricedItems);
    }
    public void delete(FtPricedItems ftPricedItems) {
        database.ftPricedItemsDao().delete(ftPricedItems);
    }

    public void deleteAllFtPricedItems() {
        database.ftPricedItemsDao().deleteAllFtPricedItems();
    }


    public LiveData<List<FtPriceh>> getAllFtPricehLive() {
        return listFtPricehLive;
    }
    public List<FtPriceh> getAllFtPriceh() {
        return database.ftPricehDao().getAllFtPriceh();
    }
    public List<FtPriceh> getAllFtPricehById(Long id) {
        return database.ftPricehDao().getAllById(id);
    }
    public List<FtPriceh> getAllFtPricehByDivision(Integer id) {
        return database.ftPricehDao().getAllByDivision(id);
    }



}
