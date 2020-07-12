package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FSalesmanDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FSalesman;
import com.erp.distribution.sfa.model.FSalesman;

import java.util.List;

public class FSalesmanRepository {

    /**
     * @param fSalesman
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FSalesmanDao fSalesmanDao;
    private LiveData<List<FSalesman>> listFSalesmanLive;
    private List<FSalesman> listFSalesman;
    public FSalesmanRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fSalesmanDao = database.fSalesmanDao();
        listFSalesmanLive = fSalesmanDao.getAllFSalesmanLive();
    }

    public void insert(FSalesman fSalesman) {
        database.fSalesmanDao().insert(fSalesman);
    }
    public void update(FSalesman fSalesman) {
        database.fSalesmanDao().update(fSalesman);
    }
    public void delete(FSalesman fSalesman) {
        database.fSalesmanDao().delete(fSalesman);
    }

    public void deleteAllFSalesman() {
        database.fSalesmanDao().deleteAllFSalesman();
    }
  
    public LiveData<List<FSalesman>> getAllFSalesmanLive() {
        return listFSalesmanLive;
    }
    public List<FSalesman> getAllFSalesman() {
        return database.fSalesmanDao().getAllFSalesman();
    }
    public List<FSalesman> getAllFSalesmanById(Integer id) {
        return database.fSalesmanDao().getAllById(id);
    }
    public List<FSalesman> getAllFSalesmanByDivision(Integer id) {
        return database.fSalesmanDao().getAllByDivision(id);
    }




}
