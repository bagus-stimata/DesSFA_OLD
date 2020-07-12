package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FTaxDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FTax;
import com.erp.distribution.sfa.model.FTax;

import java.util.List;

public class FTaxRepository {

    /**
     * @param fTax
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FTaxDao fTaxDao;
    private LiveData<List<FTax>> listFTaxLive;
    private List<FTax> listFTax;
    public FTaxRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fTaxDao = database.fTaxDao();
        listFTaxLive = fTaxDao.getAllFTaxLive();
    }

    public void insert(FTax fTax) {
        database.fTaxDao().insert(fTax);
    }
    public void update(FTax fTax) {
        database.fTaxDao().update(fTax);
    }
    public void delete(FTax fTax) {
        database.fTaxDao().delete(fTax);
    }

    public void deleteAllFTax() {
        database.fTaxDao().deleteAllFTax();
    }
    
    
    public LiveData<List<FTax>> getAllFTaxLive() {
        return listFTaxLive;
    }
    public List<FTax> getAllFTax() {
        return database.fTaxDao().getAllFTax();
    }
    public List<FTax> getAllFTaxById(Integer id) {
        return database.fTaxDao().getAllById(id);
    }
    public List<FTax> getAllFTaxByDivision(Integer id) {
        return database.fTaxDao().getAllByDivision(id);
    }




}
