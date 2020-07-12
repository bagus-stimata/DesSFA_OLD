package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtPurchasedItemsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtPurchasedItems;
import com.erp.distribution.sfa.model.FtPurchasedItems;

import java.util.List;

public class FtPurchasedItemsRepository {

    /**
     * @param ftPurchasedItems
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtPurchasedItemsDao ftPurchasedItemsDao;
    private LiveData<List<FtPurchasedItems>> listFtPurchasedItemsLive;
    private List<FtPurchasedItems> listFtPurchasedItems;
    public FtPurchasedItemsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftPurchasedItemsDao = database.ftPurchasedItemsDao();
        listFtPurchasedItemsLive = ftPurchasedItemsDao.getAllFtPurchasedItemsLive();
    }

    public void insert(FtPurchasedItems ftPurchasedItems) {
        database.ftPurchasedItemsDao().insert(ftPurchasedItems);
    }
    public void update(FtPurchasedItems ftPurchasedItems) {
        database.ftPurchasedItemsDao().update(ftPurchasedItems);
    }
    public void delete(FtPurchasedItems ftPurchasedItems) {
        database.ftPurchasedItemsDao().delete(ftPurchasedItems);
    }

    public void deleteAllFtPurchasedItems() {
        database.ftPurchasedItemsDao().deleteAllFtPurchasedItems();
    }

    public LiveData<List<FtPurchasedItems>> getAllFtPurchasedItemsLive() {
        return listFtPurchasedItemsLive;
    }
    public List<FtPurchasedItems> getAllFtPurchasedItems() {
        return database.ftPurchasedItemsDao().getAllFtPurchasedItems();
    }
    public List<FtPurchasedItems> getAllFtPurchasedItemsById(Long id) {
        return database.ftPurchasedItemsDao().getAllById(id);
    }
    public List<FtPurchasedItems> getAllFtPurchasedItemsByParentId(Long id) {
        return database.ftPurchasedItemsDao().getAllByParentId(id);
    }




}
