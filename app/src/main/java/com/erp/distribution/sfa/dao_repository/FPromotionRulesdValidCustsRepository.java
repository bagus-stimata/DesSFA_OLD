package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FPromotionRulesdValidCustsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FPromotionRulesdValidCusts;
import com.erp.distribution.sfa.model.FPromotionRulesdValidCusts;

import java.util.List;

public class FPromotionRulesdValidCustsRepository {

    /**
     * @param fPromotionRulesdValidCusts
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FPromotionRulesdValidCustsDao fPromotionRulesdValidCustsDao;
    private LiveData<List<FPromotionRulesdValidCusts>> listFPromotionRulesdValidCustsLive;
    private List<FPromotionRulesdValidCusts> listFPromotionRulesdValidCusts;
    public FPromotionRulesdValidCustsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fPromotionRulesdValidCustsDao = database.fPromotionRulesdValidCustsDao();
        listFPromotionRulesdValidCustsLive = fPromotionRulesdValidCustsDao.getAllFPromotionRulesdValidCustsLive();
    }

    public void insert(FPromotionRulesdValidCusts fPromotionRulesdValidCusts) {
        database.fPromotionRulesdValidCustsDao().insert(fPromotionRulesdValidCusts);
    }
    public void update(FPromotionRulesdValidCusts fPromotionRulesdValidCusts) {
        database.fPromotionRulesdValidCustsDao().update(fPromotionRulesdValidCusts);
    }
    public void delete(FPromotionRulesdValidCusts fPromotionRulesdValidCusts) {
        database.fPromotionRulesdValidCustsDao().delete(fPromotionRulesdValidCusts);
    }

    public void deleteAllFPromotionRulesdValidCusts() {
        database.fPromotionRulesdValidCustsDao().deleteAllFPromotionRulesdValidCusts();
    }
    
    
    public LiveData<List<FPromotionRulesdValidCusts>> getAllFPromotionRulesdValidCustsLive() {
        return listFPromotionRulesdValidCustsLive;
    }
    public List<FPromotionRulesdValidCusts> getAllFPromotionRulesdValidCusts() {
        return database.fPromotionRulesdValidCustsDao().getAllFPromotionRulesdValidCusts();
    }
    public List<FPromotionRulesdValidCusts> getAllFPromotionRulesdValidCustsById(Integer id) {
        return database.fPromotionRulesdValidCustsDao().getAllById(id);
    }
    public List<FPromotionRulesdValidCusts> getAllFPromotionRulesdValidCustsByParentId(Integer id) {
        return database.fPromotionRulesdValidCustsDao().getAllByParentId(id);
    }





}
