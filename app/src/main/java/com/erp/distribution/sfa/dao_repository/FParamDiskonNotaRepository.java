package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FParamDiskonNotaDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FParamDiskonNota;
import com.erp.distribution.sfa.model.FParamDiskonNota;

import java.util.List;

public class FParamDiskonNotaRepository {

    /**
     * @param fParamDiskonNota
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FParamDiskonNotaDao fParamDiskonNotaDao;
    private LiveData<List<FParamDiskonNota>> listFParamDiskonNotaLive;
    private List<FParamDiskonNota> listFParamDiskonNota;
    public FParamDiskonNotaRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fParamDiskonNotaDao = database.fParamDiskonNotaDao();
        listFParamDiskonNotaLive = fParamDiskonNotaDao.getAllFParamDiskonNotaLive();
    }

    public void insert(FParamDiskonNota fParamDiskonNota) {
        database.fParamDiskonNotaDao().insert(fParamDiskonNota);
    }
    public void update(FParamDiskonNota fParamDiskonNota) {
        database.fParamDiskonNotaDao().update(fParamDiskonNota);
    }
    public void delete(FParamDiskonNota fParamDiskonNota) {
        database.fParamDiskonNotaDao().delete(fParamDiskonNota);
    }

    public void deleteAllFParamDiskonNota() {
        database.fParamDiskonNotaDao().deleteAllFParamDiskonNota();
    }
   
   
    public LiveData<List<FParamDiskonNota>> getAllFParamDiskonNotaLive() {
        return listFParamDiskonNotaLive;
    }
    public List<FParamDiskonNota> getAllFParamDiskonNota() {
        return database.fParamDiskonNotaDao().getAllFParamDiskonNota();
    }
    public List<FParamDiskonNota> getAllFParamDiskonNotaById(Integer id) {
        return database.fParamDiskonNotaDao().getAllById(id);
    }
    public List<FParamDiskonNota> getAllFParamDiskonNotaByDivision(Integer id) {
        return database.fParamDiskonNotaDao().getAllByDivision(id);
    }



}
