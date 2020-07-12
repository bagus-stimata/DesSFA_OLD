package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtSalesdItemsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtSalesdItems;
import com.erp.distribution.sfa.model.FtSalesdItems;

import java.util.List;

public class FtSalesdItemsRepository {

    /**
     * @param ftSalesdItems
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtSalesdItemsDao ftSalesdItemsDao;
    private LiveData<List<FtSalesdItems>> listFtSalesdItemsLive;
    private List<FtSalesdItems> listFtSalesdItems;
    public FtSalesdItemsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftSalesdItemsDao = database.ftSalesdItemsDao();
        listFtSalesdItemsLive = ftSalesdItemsDao.getAllFtSalesdItemsLive();
    }

    public void insert(FtSalesdItems ftSalesdItems) {
        database.ftSalesdItemsDao().insert(ftSalesdItems);
    }
    public void update(FtSalesdItems ftSalesdItems) {
        database.ftSalesdItemsDao().update(ftSalesdItems);
    }
    public void delete(FtSalesdItems ftSalesdItems) {
        database.ftSalesdItemsDao().delete(ftSalesdItems);
    }

    public void deleteAllFtSalesdItems() {
        database.ftSalesdItemsDao().deleteAllFtSalesdItems();
    }

    public LiveData<List<FtSalesdItems>> getAllFtSalesdItemsLive() {
        return listFtSalesdItemsLive;
    }
    public List<FtSalesdItems> getAllFtSalesdItems() {
        return database.ftSalesdItemsDao().getAllFtSalesdItems();
    }
    public List<FtSalesdItems> getAllFtSalesdItemsById(Long id) {
        return database.ftSalesdItemsDao().getAllById(id);
    }
    public List<FtSalesdItems> getAllFtSalesdItemsByParentId(Long id) {
        return database.ftSalesdItemsDao().getAllByParentId(id);
    }




}
