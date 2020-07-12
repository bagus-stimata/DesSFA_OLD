package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FPromotionRuleshDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FPromotionRulesh;
import com.erp.distribution.sfa.model.FPromotionRulesh;

import java.util.List;

public class FPromotionRuleshRepository {

    /**
     * @param fPromotionRulesh
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FPromotionRuleshDao fPromotionRuleshDao;
    private LiveData<List<FPromotionRulesh>> listFPromotionRuleshLive;
    private List<FPromotionRulesh> listFPromotionRulesh;
    public FPromotionRuleshRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fPromotionRuleshDao = database.fPromotionRuleshDao();
        listFPromotionRuleshLive = fPromotionRuleshDao.getAllFPromotionRuleshLive();
    }

    public void insert(FPromotionRulesh fPromotionRulesh) {
        database.fPromotionRuleshDao().insert(fPromotionRulesh);
    }
    public void update(FPromotionRulesh fPromotionRulesh) {
        database.fPromotionRuleshDao().update(fPromotionRulesh);
    }
    public void delete(FPromotionRulesh fPromotionRulesh) {
        database.fPromotionRuleshDao().delete(fPromotionRulesh);
    }

    public void deleteAllFPromotionRulesh() {
        database.fPromotionRuleshDao().deleteAllFPromotionRulesh();
    }
   
   
    public LiveData<List<FPromotionRulesh>> getAllFPromotionRuleshLive() {
        return listFPromotionRuleshLive;
    }
    public List<FPromotionRulesh> getAllFPromotionRulesh() {
        return database.fPromotionRuleshDao().getAllFPromotionRulesh();
    }
    public List<FPromotionRulesh> getAllFPromotionRuleshById(Integer id) {
        return database.fPromotionRuleshDao().getAllById(id);
    }
    public List<FPromotionRulesh> getAllFPromotionRuleshByDivision(Integer id) {
        return database.fPromotionRuleshDao().getAllByDivision(id);
    }





}
