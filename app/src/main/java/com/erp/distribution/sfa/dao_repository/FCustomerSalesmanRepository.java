package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FCompanyDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FCustomerSalesman;
import com.erp.distribution.sfa.model.FCompany;

import java.util.List;

public class FCustomerSalesmanRepository {

    /**
     * @param fCompany
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FCompanyDao fCompanyDao;
    private LiveData<List<FCompany>> listFCompanyLive;
    private List<FCompany> listFCompany;
    public FCustomerSalesmanRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fCompanyDao = database.fCompanyDao();
        listFCompanyLive = fCompanyDao.getAllFCompanyLive();
    }

    public void insert(FCustomerSalesman fCustomerSalesman) {
        database.fCustomerSalesmanDao().insert(fCustomerSalesman);
    }
    public void update(FCustomerSalesman fCustomerSalesman) {
        database.fCustomerSalesmanDao().update(fCustomerSalesman);
    }
    public void delete(FCustomerSalesman fCustomerSalesman) {
        database.fCustomerSalesmanDao().delete(fCustomerSalesman);
    }

    public void deleteAllFCustomerSalesman() {
        database.fCustomerSalesmanDao().deleteAllFCustomerSalesman();
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
