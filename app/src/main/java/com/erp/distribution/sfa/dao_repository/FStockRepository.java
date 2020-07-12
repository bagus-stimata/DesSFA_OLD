package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FStockDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FStock;
import com.erp.distribution.sfa.model.FStock;

import java.util.List;

public class FStockRepository {

    /**
     * @param fStock
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FStockDao fStockDao;
    private LiveData<List<FStock>> listFStockLive;
    private List<FStock> listFStock;
    public FStockRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fStockDao = database.fStockDao();
        listFStockLive = fStockDao.getAllFStockLive();
    }

    public void insert(FStock fStock) {
        database.fStockDao().insert(fStock);
    }
    public void update(FStock fStock) {
        database.fStockDao().update(fStock);
    }
    public void delete(FStock fStock) {
        database.fStockDao().delete(fStock);
    }

    public void deleteAllFStock() {
        database.fStockDao().deleteAllFStock();
    }
   
   
    public LiveData<List<FStock>> getAllFStockLive() {
        return listFStockLive;
    }
    public List<FStock> getAllFStock() {
        return database.fStockDao().getAllFStock();
    }
    public List<FStock> getAllFStockById(Long id) {
        return database.fStockDao().getAllById(id);
    }
    public List<FStock> getAllFStockByParentId(Integer fwarehouseBean, Integer fmaterialBean) {
        return database.fStockDao().getAllByParentId(fwarehouseBean, fmaterialBean);
    }





}
