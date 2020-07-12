package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FMaterialSalesBrandDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FMaterialSalesBrand;
import com.erp.distribution.sfa.model.FMaterialSalesBrand;

import java.util.List;

public class FMaterialSalesBrandRepository {

    /**
     * @param fMaterialSalesBrand
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FMaterialSalesBrandDao fMaterialSalesBrandDao;
    private LiveData<List<FMaterialSalesBrand>> listFMaterialSalesBrandLive;
    private List<FMaterialSalesBrand> listFMaterialSalesBrand;
    public FMaterialSalesBrandRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fMaterialSalesBrandDao = database.fMaterialSalesBrandDao();
        listFMaterialSalesBrandLive = fMaterialSalesBrandDao.getAllFMaterialSalesBrandLive();
    }

    public void insert(FMaterialSalesBrand fMaterialSalesBrand) {
        database.fMaterialSalesBrandDao().insert(fMaterialSalesBrand);
    }
    public void update(FMaterialSalesBrand fMaterialSalesBrand) {
        database.fMaterialSalesBrandDao().update(fMaterialSalesBrand);
    }
    public void delete(FMaterialSalesBrand fMaterialSalesBrand) {
        database.fMaterialSalesBrandDao().delete(fMaterialSalesBrand);
    }

    public void deleteAllFMaterialSalesBrand() {
        database.fMaterialSalesBrandDao().deleteAllFMaterialSalesBrand();
    }
   
   
    public LiveData<List<FMaterialSalesBrand>> getAllFMaterialSalesBrandLive() {
        return listFMaterialSalesBrandLive;
    }
    public List<FMaterialSalesBrand> getAllFMaterialSalesBrand() {
        return database.fMaterialSalesBrandDao().getAllFMaterialSalesBrand();
    }
    public List<FMaterialSalesBrand> getAllFMaterialSalesBrandById(Integer id) {
        return database.fMaterialSalesBrandDao().getAllById(id);
    }
    public List<FMaterialSalesBrand> getAllFMaterialSalesBrandByDivision(Integer id) {
        return database.fMaterialSalesBrandDao().getAllByDivision(id);
    }




}
