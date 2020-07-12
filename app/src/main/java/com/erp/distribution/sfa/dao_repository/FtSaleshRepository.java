package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtSaleshDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtSalesh;
import com.erp.distribution.sfa.model.FtSalesh;

import java.util.List;

public class FtSaleshRepository {

    /**
     * @param ftSalesh
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtSaleshDao ftSaleshDao;
    private LiveData<List<FtSalesh>> listFtSaleshLive;
    private List<FtSalesh> listFtSalesh;
    public FtSaleshRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftSaleshDao = database.ftSaleshDao();
        listFtSaleshLive = ftSaleshDao.getAllFtSaleshLive();
    }

    public void insert(FtSalesh ftSalesh) {
        database.ftSaleshDao().insert(ftSalesh);
    }
    public void update(FtSalesh ftSalesh) {
        database.ftSaleshDao().update(ftSalesh);
    }
    public void delete(FtSalesh ftSalesh) {
        database.ftSaleshDao().delete(ftSalesh);
    }

    public void deleteAllFtSalesh() {
        database.ftSaleshDao().deleteAllFtSalesh();
    }


    public LiveData<List<FtSalesh>> getAllFtSaleshLive() {
        return listFtSaleshLive;
    }
    public List<FtSalesh> getAllFtSalesh() {
        return database.ftSaleshDao().getAllFtSalesh();
    }
    public List<FtSalesh> getAllFtSaleshById(Long id) {
        return database.ftSaleshDao().getAllById(id);
    }
    public List<FtSalesh> getAllFtSaleshByDivision(Integer id) {
        return database.ftSaleshDao().getAllByDivision(id);
    }



}
