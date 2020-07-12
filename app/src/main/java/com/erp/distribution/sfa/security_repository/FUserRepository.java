package com.erp.distribution.sfa.security_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.security_model.FUser;

import java.util.List;

public class FUserRepository {

    /**
     * @param fUser
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FUserDao fUserDao;
    private LiveData<List<FUser>> listFUserLive;
    private List<FUser> listFUser;
    public FUserRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fUserDao = database.fUserDao();
        listFUserLive = fUserDao.getAllFUserLive();
    }

    public void insert(FUser fUser) {
        database.fUserDao().insert(fUser);
    }
    public void update(FUser fUser) {
        database.fUserDao().insert(fUser);
    }
    public void delete(FUser fUser) {
        database.fUserDao().update(fUser);
    }
    
    public void deleteAllFUser() {
        database.fUserDao().deleteAllFUser();
    }


    public LiveData<List<FUser>> getAllFUserLive() {
        return listFUserLive;
    }

    public List<FUser> getAllFUser() {
        return database.fUserDao().getAllFUser();
    }
    public List<FUser> getAllFUserById(Integer id) {
        return database.fUserDao().getAllById(id);
    }
    public List<FUser> getAllFUserByDivision(Integer id) {
        return database.fUserDao().getAllByDivision(id);
    }








}
