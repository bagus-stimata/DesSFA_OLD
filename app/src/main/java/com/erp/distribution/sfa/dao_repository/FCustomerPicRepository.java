package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FCustomerPicDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FCustomerPic;
import com.erp.distribution.sfa.model.FCustomerPic;

import java.util.List;

public class FCustomerPicRepository {

    /**
     * @param fCustomerPic
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FCustomerPicDao fCustomerPicDao;
    private LiveData<List<FCustomerPic>> listFCustomerPicLive;
    private List<FCustomerPic> listFCustomerPic;
    public FCustomerPicRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fCustomerPicDao = database.fCustomerPicDao();
        listFCustomerPicLive = fCustomerPicDao.getAllFCustomerPicLive();
    }

    public void insert(FCustomerPic fCustomerPic) {
        database.fCustomerPicDao().insert(fCustomerPic);
    }
    public void update(FCustomerPic fCustomerPic) {
        database.fCustomerPicDao().update(fCustomerPic);
    }
    public void delete(FCustomerPic fCustomerPic) {
        database.fCustomerPicDao().delete(fCustomerPic);
    }

    public void deleteAllFCustomerPic() {
        database.fCustomerPicDao().deleteAllFCustomerPic();
    }
    
    
    public LiveData<List<FCustomerPic>> getAllFCustomerPicLive() {
        return listFCustomerPicLive;
    }
    public List<FCustomerPic> getAllFCustomerPic() {
        return database.fCustomerPicDao().getAllFCustomerPic();
    }
    public List<FCustomerPic> getAllFCustomerPicById(Integer id) {
        return database.fCustomerPicDao().getAllById(id);
    }
    public List<FCustomerPic> getAllFCustomerPicByDivision(Integer id) {
        return database.fCustomerPicDao().getAllByParentId(id);
    }




}
