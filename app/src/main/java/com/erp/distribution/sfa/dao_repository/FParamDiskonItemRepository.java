package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FParamDiskonItemDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FParamDiskonItem;
import com.erp.distribution.sfa.model.FParamDiskonItem;

import java.util.List;

public class FParamDiskonItemRepository {

    /**
     * @param fParamDiskonItem
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FParamDiskonItemDao fParamDiskonItemDao;
    private LiveData<List<FParamDiskonItem>> listFParamDiskonItemLive;
    private List<FParamDiskonItem> listFParamDiskonItem;
    public FParamDiskonItemRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fParamDiskonItemDao = database.fParamDiskonItemDao();
        listFParamDiskonItemLive = fParamDiskonItemDao.getAllFParamDiskonItemLive();
    }

    public void insert(FParamDiskonItem fParamDiskonItem) {
        database.fParamDiskonItemDao().insert(fParamDiskonItem);
    }
    public void update(FParamDiskonItem fParamDiskonItem) {
        database.fParamDiskonItemDao().update(fParamDiskonItem);
    }
    public void delete(FParamDiskonItem fParamDiskonItem) {
        database.fParamDiskonItemDao().delete(fParamDiskonItem);
    }

    public void deleteAllFParamDiskonItem() {
        database.fParamDiskonItemDao().deleteAllFParamDiskonItem();
    }
   
    public LiveData<List<FParamDiskonItem>> getAllFParamDiskonItemLive() {
        return listFParamDiskonItemLive;
    }
    public List<FParamDiskonItem> getAllFParamDiskonItem() {
        return database.fParamDiskonItemDao().getAllFParamDiskonItem();
    }
    public List<FParamDiskonItem> getAllFParamDiskonItemById(Integer id) {
        return database.fParamDiskonItemDao().getAllById(id);
    }
    public List<FParamDiskonItem> getAllFParamDiskonItemByDivision(Integer id) {
        return database.fParamDiskonItemDao().getAllByDivision(id);
    }




}
