package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FMaterialPicDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FMaterialPic;
import com.erp.distribution.sfa.model.FMaterialPic;

import java.util.List;

public class FMaterialPicRepository {

    /**
     * @param fMaterialPic
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FMaterialPicDao fMaterialPicDao;
    private LiveData<List<FMaterialPic>> listFMaterialPicLive;
    private List<FMaterialPic> listFMaterialPic;
    public FMaterialPicRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fMaterialPicDao = database.fMaterialPicDao();
        listFMaterialPicLive = fMaterialPicDao.getAllFMaterialPicLive();
    }

    public void insert(FMaterialPic fMaterialPic) {
        database.fMaterialPicDao().insert(fMaterialPic);
    }
    public void update(FMaterialPic fMaterialPic) {
        database.fMaterialPicDao().update(fMaterialPic);
    }
    public void delete(FMaterialPic fMaterialPic) {
        database.fMaterialPicDao().delete(fMaterialPic);
    }

    public void deleteAllFMaterialPic() {
        database.fMaterialPicDao().deleteAllFMaterialPic();
    }
   
    public LiveData<List<FMaterialPic>> getAllFMaterialPicLive() {
        return listFMaterialPicLive;
    }
    public List<FMaterialPic> getAllFMaterialPic() {
        return database.fMaterialPicDao().getAllFMaterialPic();
    }
    public List<FMaterialPic> getAllFMaterialPicById(Integer id) {
        return database.fMaterialPicDao().getAllById(id);
    }
    public List<FMaterialPic> getAllFMaterialPicByParentId(Integer id) {
        return database.fMaterialPicDao().getAllByParentId(id);
    }



}
