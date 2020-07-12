package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FDivisionDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FDivision;
import com.erp.distribution.sfa.model.FDivision;

import java.util.List;

public class FDivisionRepository {

    /**
     * @param fDivision
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FDivisionDao fDivisionDao;
    private LiveData<List<FDivision>> listFDivisionLive;
    private List<FDivision> listFDivision;
    public FDivisionRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fDivisionDao = database.fDivisionDao();
        listFDivisionLive = fDivisionDao.getAllFDivisionLive();
    }

    public void insert(FDivision fDivision) {
        database.fDivisionDao().insert(fDivision);
    }
    public void update(FDivision fDivision) {
        database.fDivisionDao().update(fDivision);
    }
    public void delete(FDivision fDivision) {
        database.fDivisionDao().delete(fDivision);
    }

    public void deleteAllFDivision() {
        database.fDivisionDao().deleteAllFDivision();
    }
    
    
    public LiveData<List<FDivision>> getAllFDivisionLive() {
        return listFDivisionLive;
    }
    public List<FDivision> getAllFDivision() {
        return database.fDivisionDao().getAllFDivision();
    }
    public List<FDivision> getAllFDivisionById(Integer id) {
        return database.fDivisionDao().getAllById(id);
    }
    public List<FDivision> getAllFDivisionByParentId(Integer id) {
        return database.fDivisionDao().getAllByParentId(id);
    }






}
