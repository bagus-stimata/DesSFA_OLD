package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtStockTransferdItemsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtStockTransferdItems;
import com.erp.distribution.sfa.model.FtStockTransferdItems;

import java.util.List;

public class FtStockTransferItemsRepository {

    /**
     * @param ftStockTransferdItems
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtStockTransferdItemsDao ftStockTransferdItemsDao;
    private LiveData<List<FtStockTransferdItems>> listFtStockTransferdItemsLive;
    private List<FtStockTransferdItems> listFtStockTransferdItems;
    public FtStockTransferItemsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftStockTransferdItemsDao = database.ftStockTransferdItemsDao();
        listFtStockTransferdItemsLive = ftStockTransferdItemsDao.getAllFtStockTransferdItemsLive();
    }

    public void insert(FtStockTransferdItems ftStockTransferdItems) {
        database.ftStockTransferdItemsDao().insert(ftStockTransferdItems);
    }
    public void update(FtStockTransferdItems ftStockTransferdItems) {
        database.ftStockTransferdItemsDao().update(ftStockTransferdItems);
    }
    public void delete(FtStockTransferdItems ftStockTransferdItems) {
        database.ftStockTransferdItemsDao().delete(ftStockTransferdItems);
    }

    public void deleteAllFtStockTransferdItems() {
        database.ftStockTransferdItemsDao().deleteAllFtStockTransferdItems();
    }

    public LiveData<List<FtStockTransferdItems>> getAllFtStockTransferdItemsLive() {
        return listFtStockTransferdItemsLive;
    }
    public List<FtStockTransferdItems> getAllFtStockTransferdItems() {
        return database.ftStockTransferdItemsDao().getAllFtStockTransferdItems();
    }
    public List<FtStockTransferdItems> getAllFtStockTransferdItemsById(Long id) {
        return database.ftStockTransferdItemsDao().getAllById(id);
    }
    public List<FtStockTransferdItems> getAllFtStockTransferdItemsByDivision(Long id) {
        return database.ftStockTransferdItemsDao().getAllByDivision(id);
    }





}
