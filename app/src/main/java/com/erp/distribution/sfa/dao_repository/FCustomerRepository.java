package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FCustomerDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FCustomer;
import com.erp.distribution.sfa.model.FCustomer;
import com.erp.distribution.sfa.model.FGiro;

import java.util.List;

public class FCustomerRepository {

    /**
     * @param fCustomer
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FCustomerDao fCustomerDao;
    private LiveData<List<FCustomer>> listFCustomerLive;
    private List<FCustomer> listFCustomer;
    public FCustomerRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fCustomerDao = database.fCustomerDao();
        listFCustomerLive = fCustomerDao.getAllFCustomerLive();
    }

    public void insert(FCustomer fCustomer) {
        database.fCustomerDao().insert(fCustomer);
    }
    public void update(FCustomer fCustomer) {
        database.fCustomerDao().update(fCustomer);
    }
    public void delete(FCustomer fCustomer) {
        database.fCustomerDao().delete(fCustomer);
    }

    public void deleteAllFCustomer() {
        database.fCustomerDao().deleteAllFCustomer();
    }
    
    public LiveData<List<FCustomer>> getAllFCustomerLive() {
        return listFCustomerLive;
    }
    public List<FCustomer> getAllFCustomer() {
        return database.fCustomerDao().getAllFCustomer();
    }
    public List<FCustomer> getAllFCustomerById(Integer id) {
        return database.fCustomerDao().getAllById(id);
    }
    public List<FCustomer> getAllFCustomerByDivision(Integer id) {
        return database.fCustomerDao().getAllByDivision(id);
    }







}
