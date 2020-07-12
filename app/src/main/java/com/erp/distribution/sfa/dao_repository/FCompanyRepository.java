package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FCompanyDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FCompany;
import com.erp.distribution.sfa.model.FCompany;

import java.util.List;

public class FCompanyRepository {

    /**
     * @param fCompany
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FCompanyDao fCompanyDao;
    private LiveData<List<FCompany>> listFCompanyLive;
    private List<FCompany> listFCompany;
    public FCompanyRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fCompanyDao = database.fCompanyDao();
        listFCompanyLive = fCompanyDao.getAllFCompanyLive();
    }

    public void insert(FCompany fCompany) {
        database.fCompanyDao().insert(fCompany);
    }
    public void update(FCompany fCompany) {
        database.fCompanyDao().update(fCompany);
    }
    public void delete(FCompany fCompany) {
        database.fCompanyDao().delete(fCompany);
    }

    public void deleteAllFCompany() {
        database.fCompanyDao().deleteAllFCompany();
    }


    public LiveData<List<FCompany>> getAllFCompanyLive() {
        return listFCompanyLive;
    }
    public List<FCompany> getAllFCompany() {
        return database.fCompanyDao().getAllFCompany();
    }
    public List<FCompany> getAllFCompanyById(Integer id) {
        return database.fCompanyDao().getAllById(id);
    }


    

}
