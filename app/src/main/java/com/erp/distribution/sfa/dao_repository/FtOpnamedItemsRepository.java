package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtOpnamedItemsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtOpnamedItems;
import com.erp.distribution.sfa.model.FtOpnamedItems;

import java.util.List;

public class FtOpnamedItemsRepository {

    /**
     * @param ftOpnamedItems
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtOpnamedItemsDao ftOpnamedItemsDao;
    private LiveData<List<FtOpnamedItems>> listFtOpnamedItemsLive;
    private List<FtOpnamedItems> listFtOpnamedItems;
    public FtOpnamedItemsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftOpnamedItemsDao = database.ftOpnamedItemsDao();
        listFtOpnamedItemsLive = ftOpnamedItemsDao.getAllFtOpnamedItemsLive();
    }

    public void insert(FtOpnamedItems ftOpnamedItems) {
        database.ftOpnamedItemsDao().insert(ftOpnamedItems);
    }
    public void update(FtOpnamedItems ftOpnamedItems) {
        database.ftOpnamedItemsDao().update(ftOpnamedItems);
    }
    public void delete(FtOpnamedItems ftOpnamedItems) {
        database.ftOpnamedItemsDao().delete(ftOpnamedItems);
    }

    public void deleteAllFtOpnamedItems() {
        database.ftOpnamedItemsDao().deleteAllFtOpnamedItems();
    }
   
    public LiveData<List<FtOpnamedItems>> getAllFtOpnamedItemsLive() {
        return listFtOpnamedItemsLive;
    }
    public List<FtOpnamedItems> getAllFtOpnamedItems() {
        return database.ftOpnamedItemsDao().getAllFtOpnamedItems();
    }
    public List<FtOpnamedItems> getAllFtOpnamedItemsById(Long id) {
        return database.ftOpnamedItemsDao().getAllById(id);
    }
    public List<FtOpnamedItems> getAllFtOpnamedItemsByParentId(Long id) {
        return database.ftOpnamedItemsDao().getAllByParentId(id);
    }



}
