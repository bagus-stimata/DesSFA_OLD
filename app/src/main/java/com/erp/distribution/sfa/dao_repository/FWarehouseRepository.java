package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FWarehouseDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FWarehouse;
import com.erp.distribution.sfa.model.FWarehouse;

import java.util.List;

public class FWarehouseRepository {

    /**
     * @param fWarehouse
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FWarehouseDao fWarehouseDao;
    private LiveData<List<FWarehouse>> listFWarehouseLive;
    private List<FWarehouse> listFWarehouse;
    public FWarehouseRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fWarehouseDao = database.fWarehouseDao();
        listFWarehouseLive = fWarehouseDao.getAllFWarehouseLive();
    }

    public void insert(FWarehouse fWarehouse) {
        database.fWarehouseDao().insert(fWarehouse);
    }
    public void update(FWarehouse fWarehouse) {
        database.fWarehouseDao().update(fWarehouse);
    }
    public void delete(FWarehouse fWarehouse) {
        database.fWarehouseDao().delete(fWarehouse);
    }

    public void deleteAllFWarehouse() {
        database.fWarehouseDao().deleteAllFWarehouse();
    }

    public LiveData<List<FWarehouse>> getAllFWarehouseLive() {
        return listFWarehouseLive;
    }
    public List<FWarehouse> getAllFWarehouse() {
        return database.fWarehouseDao().getAllFWarehouse();
    }
    public List<FWarehouse> getAllFWarehouseById(Integer id) {
        return database.fWarehouseDao().getAllById(id);
    }
    public List<FWarehouse> getAllFWarehouseByDivision(Integer id) {
        return database.fWarehouseDao().getAllByDivision(id);
    }




}
