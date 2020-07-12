package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FParamDiskonItemVendorDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FParamDiskonItemVendor;
import com.erp.distribution.sfa.model.FParamDiskonItemVendor;

import java.util.List;

public class FParamDiskonItemVendorRepository {

    /**
     * @param fParamDiskonItemVendor
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FParamDiskonItemVendorDao fParamDiskonItemVendorDao;
    private LiveData<List<FParamDiskonItemVendor>> listFParamDiskonItemVendorLive;
    private List<FParamDiskonItemVendor> listFParamDiskonItemVendor;
    public FParamDiskonItemVendorRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fParamDiskonItemVendorDao = database.fParamDiskonItemVendorDao();
        listFParamDiskonItemVendorLive = fParamDiskonItemVendorDao.getAllFParamDiskonItemVendorLive();
    }

    public void insert(FParamDiskonItemVendor fParamDiskonItemVendor) {
        database.fParamDiskonItemVendorDao().insert(fParamDiskonItemVendor);
    }
    public void update(FParamDiskonItemVendor fParamDiskonItemVendor) {
        database.fParamDiskonItemVendorDao().update(fParamDiskonItemVendor);
    }
    public void delete(FParamDiskonItemVendor fParamDiskonItemVendor) {
        database.fParamDiskonItemVendorDao().delete(fParamDiskonItemVendor);
    }

    public void deleteAllFParamDiskonItemVendor() {
        database.fParamDiskonItemVendorDao().deleteAllFParamDiskonItemVendor();
    }
   
    public LiveData<List<FParamDiskonItemVendor>> getAllFParamDiskonItemVendorLive() {
        return listFParamDiskonItemVendorLive;
    }
    public List<FParamDiskonItemVendor> getAllFParamDiskonItemVendor() {
        return database.fParamDiskonItemVendorDao().getAllFParamDiskonItemVendor();
    }
    public List<FParamDiskonItemVendor> getAllFParamDiskonItemVendorById(Integer id) {
        return database.fParamDiskonItemVendorDao().getAllById(id);
    }
    public List<FParamDiskonItemVendor> getAllFParamDiskonItemVendorByDivision(Integer id) {
        return database.fParamDiskonItemVendorDao().getAllByDivision(id);
    }




}
