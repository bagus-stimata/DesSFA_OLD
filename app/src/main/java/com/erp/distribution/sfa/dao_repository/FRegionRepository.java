package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FRegionDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FRegion;
import com.erp.distribution.sfa.model.FRegion;

import java.util.List;

public class FRegionRepository {

    /**
     * @param fRegion
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FRegionDao fRegionDao;
    private LiveData<List<FRegion>> listFRegionLive;
    private List<FRegion> listFRegion;
    public FRegionRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fRegionDao = database.fRegionDao();
        listFRegionLive = fRegionDao.getAllFRegionLive();
    }

    public void insert(FRegion fRegion) {
        database.fRegionDao().insert(fRegion);
    }
    public void update(FRegion fRegion) {
        database.fRegionDao().update(fRegion);
    }
    public void delete(FRegion fRegion) {
        database.fRegionDao().delete(fRegion);
    }

    public void deleteAllFRegion() {
        database.fRegionDao().deleteAllFRegion();
    }

    public LiveData<List<FRegion>> getAllFRegionLive() {
        return listFRegionLive;
    }
    public List<FRegion> getAllFRegion() {
        return database.fRegionDao().getAllFRegion();
    }
    public List<FRegion> getAllFRegionById(Integer id) {
        return database.fRegionDao().getAllById(id);
    }
    public List<FRegion> getAllFRegionByDivision(Integer id) {
        return database.fRegionDao().getAllByDivision(id);
    }





}
