package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FCustomerGroupDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FCustomerGroup;
import com.erp.distribution.sfa.model.FCustomerGroup;

import java.util.List;

public class FCustomerGroupRepository {

    /**
     * @param fCustomerGroup
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FCustomerGroupDao fCustomerGroupDao;
    private LiveData<List<FCustomerGroup>> listFCustomerGroupLive;
    private List<FCustomerGroup> listFCustomerGroup;
    public FCustomerGroupRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fCustomerGroupDao = database.fCustomerGroupDao();
        listFCustomerGroupLive = fCustomerGroupDao.getAllFCustomerGroupLive();
    }

    public void insert(FCustomerGroup fCustomerGroup) {
        database.fCustomerGroupDao().insert(fCustomerGroup);
    }
    public void update(FCustomerGroup fCustomerGroup) {
        database.fCustomerGroupDao().update(fCustomerGroup);
    }
    public void delete(FCustomerGroup fCustomerGroup) {
        database.fCustomerGroupDao().delete(fCustomerGroup);
    }

    public void deleteAllFCustomerGroup() {
        database.fCustomerGroupDao().deleteAllFCustomerGroup();
    }
    
    
    public LiveData<List<FCustomerGroup>> getAllFCustomerGroupLive() {
        return listFCustomerGroupLive;
    }
    public List<FCustomerGroup> getAllFCustomerGroup() {
        return database.fCustomerGroupDao().getAllFCustomerGroup();
    }
    public List<FCustomerGroup> getAllFCustomerGroupById(Integer id) {
        return database.fCustomerGroupDao().getAllById(id);
    }
    public List<FCustomerGroup> getAllFCustomerGroupByDivision(Integer id) {
        return database.fCustomerGroupDao().getAllByDivision(id);
    }







}
