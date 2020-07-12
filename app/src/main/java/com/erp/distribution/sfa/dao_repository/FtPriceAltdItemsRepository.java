package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtPriceAltdItemsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtPriceAltdItems;
import com.erp.distribution.sfa.model.FtPriceAltdItems;

import java.util.List;

public class FtPriceAltdItemsRepository {

    /**
     * @param ftPriceAltdItems
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtPriceAltdItemsDao ftPriceAltdItemsDao;
    private LiveData<List<FtPriceAltdItems>> listFtPriceAltdItemsLive;
    private List<FtPriceAltdItems> listFtPriceAltdItems;
    public FtPriceAltdItemsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftPriceAltdItemsDao = database.ftPriceAltdItemsDao();
        listFtPriceAltdItemsLive = ftPriceAltdItemsDao.getAllFtPriceAltdItemsLive();
    }

    public void insert(FtPriceAltdItems ftPriceAltdItems) {
        database.ftPriceAltdItemsDao().insert(ftPriceAltdItems);
    }
    public void update(FtPriceAltdItems ftPriceAltdItems) {
        database.ftPriceAltdItemsDao().update(ftPriceAltdItems);
    }
    public void delete(FtPriceAltdItems ftPriceAltdItems) {
        database.ftPriceAltdItemsDao().delete(ftPriceAltdItems);
    }

    public void deleteAllFtPriceAltdItems() {
        database.ftPriceAltdItemsDao().deleteAllFtPriceAltdItems();
    }


    public LiveData<List<FtPriceAltdItems>> getAllFtPriceAltdItemsLive() {
        return listFtPriceAltdItemsLive;
    }
    public List<FtPriceAltdItems> getAllFtPriceAltdItems() {
        return database.ftPriceAltdItemsDao().getAllFtPriceAltdItems();
    }
    public List<FtPriceAltdItems> getAllFtPriceAltdItemsById(Long id) {
        return database.ftPriceAltdItemsDao().getAllById(id);
    }
    public List<FtPriceAltdItems> getAllFtPriceAltdItemsByDivision(Long id) {
        return database.ftPriceAltdItemsDao().getAllByDivision(id);
    }




}
