package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.SysvarDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.Sysvar;
import com.erp.distribution.sfa.model.Sysvar;

import java.util.List;

public class SysvarRepository {

    /**
     * @param sysvar
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private SysvarDao sysvarDao;
    private LiveData<List<Sysvar>> listSysvarLive;
    private List<Sysvar> listSysvar;
    public SysvarRepository(Application application) {
        database = AppDatabase.getInstance(application);
        sysvarDao = database.sysvarDao();
        listSysvarLive = sysvarDao.getAllSysvarLive();
    }

    public void insert(Sysvar sysvar) {
        database.sysvarDao().insert(sysvar);
    }
    public void update(Sysvar sysvar) {
        database.sysvarDao().update(sysvar);
    }
    public void delete(Sysvar sysvar) {
        database.sysvarDao().delete(sysvar);
    }

    public void deleteAllSysvar() {
        database.sysvarDao().deleteAllSysvar();
    }

    public LiveData<List<Sysvar>> getAllSysvarLive() {
        return listSysvarLive;
    }
    public List<Sysvar> getAllSysvar() {
        return database.sysvarDao().getAllSysvar();
    }
    public List<Sysvar> getAllSysvarById(Integer id) {
        return database.sysvarDao().getAllById(id);
    }
    public List<Sysvar> getAllSysvarByDivision(Integer id) {
        return database.sysvarDao().getAllByDivision(id);
    }




}
