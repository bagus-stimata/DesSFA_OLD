package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FVendorDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FVendor;
import com.erp.distribution.sfa.model.FVendor;

import java.util.List;

public class FVendorRepository {

    /**
     * @param fVendor
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FVendorDao fVendorDao;
    private LiveData<List<FVendor>> listFVendorLive;
    private List<FVendor> listFVendor;
    public FVendorRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fVendorDao = database.fVendorDao();
        listFVendorLive = fVendorDao.getAllFVendorLive();
    }

    public void insert(FVendor fVendor) {
        database.fVendorDao().insert(fVendor);
    }
    public void update(FVendor fVendor) {
        database.fVendorDao().update(fVendor);
    }
    public void delete(FVendor fVendor) {
        database.fVendorDao().delete(fVendor);
    }

    public void deleteAllFVendor() {
        database.fVendorDao().deleteAllFVendor();
    }


    public LiveData<List<FVendor>> getAllFVendorLive() {
        return listFVendorLive;
    }
    public List<FVendor> getAllFVendor() {
        return database.fVendorDao().getAllFVendor();
    }
    public List<FVendor> getAllFVendorById(Integer id) {
        return database.fVendorDao().getAllById(id);
    }
    public List<FVendor> getAllFVendorByDivision(Integer id) {
        return database.fVendorDao().getAllByDivision(id);
    }



}
