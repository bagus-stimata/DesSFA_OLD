package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FSubAreaDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FArea;
import com.erp.distribution.sfa.model.FSubArea;

import java.util.List;

public class FSubAreaRepository {

    /**
     * @param fSubArea
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FSubAreaDao fSubAreaDao;
    private LiveData<List<FSubArea>> listFSubAreaLive;
    private List<FSubArea> listFSubArea;
    public FSubAreaRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fSubAreaDao = database.fSubAreaDao();
        listFSubAreaLive = fSubAreaDao.getAllFSubAreaLive();
    }

    public void insert(FSubArea fSubArea) {
        database.fSubAreaDao().insert(fSubArea);
    }
    public void update(FSubArea fSubArea) {
        database.fSubAreaDao().update(fSubArea);
    }
    public void delete(FSubArea fSubArea) {
        database.fSubAreaDao().delete(fSubArea);
    }

    public void deleteAllFSubArea() {
        database.fSubAreaDao().deleteAllFSubArea();
    }


    public LiveData<List<FSubArea>> getAllFSubAreaLive() {
        return listFSubAreaLive;
    }
    public List<FSubArea> getAllFSubArea() {
        return database.fSubAreaDao().getAllFSubArea();
    }
    public List<FSubArea> getAllFSubAreaById(Integer id) {
        return database.fSubAreaDao().getAllById(id);
    }
    public List<FSubArea> getAllFSubAreaByParentId(Integer id) {
        return database.fSubAreaDao().getAllByParentId(id);
    }




}
