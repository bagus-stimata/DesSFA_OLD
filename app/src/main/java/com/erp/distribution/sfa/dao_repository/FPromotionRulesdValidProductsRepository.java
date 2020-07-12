package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FPromotionRulesdValidProductsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FPromotionRulesdValidProducts;
import com.erp.distribution.sfa.model.FPromotionRulesdValidProducts;

import java.util.List;

public class FPromotionRulesdValidProductsRepository {

    /**
     * @param fPromotionRulesdValidProducts
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FPromotionRulesdValidProductsDao fPromotionRulesdValidProductsDao;
    private LiveData<List<FPromotionRulesdValidProducts>> listFPromotionRulesdValidProductsLive;
    private List<FPromotionRulesdValidProducts> listFPromotionRulesdValidProducts;
    public FPromotionRulesdValidProductsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fPromotionRulesdValidProductsDao = database.fPromotionRulesdValidProductsDao();
        listFPromotionRulesdValidProductsLive = fPromotionRulesdValidProductsDao.getAllFPromotionRulesdValidProductsLive();
    }

    public void insert(FPromotionRulesdValidProducts fPromotionRulesdValidProducts) {
        database.fPromotionRulesdValidProductsDao().insert(fPromotionRulesdValidProducts);
    }
    public void update(FPromotionRulesdValidProducts fPromotionRulesdValidProducts) {
        database.fPromotionRulesdValidProductsDao().update(fPromotionRulesdValidProducts);
    }
    public void delete(FPromotionRulesdValidProducts fPromotionRulesdValidProducts) {
        database.fPromotionRulesdValidProductsDao().delete(fPromotionRulesdValidProducts);
    }

    public void deleteAllFPromotionRulesdValidProducts() {
        database.fPromotionRulesdValidProductsDao().deleteAllFPromotionRulesdValidProducts();
    }
  
  
    public LiveData<List<FPromotionRulesdValidProducts>> getAllFPromotionRulesdValidProductsLive() {
        return listFPromotionRulesdValidProductsLive;
    }
    public List<FPromotionRulesdValidProducts> getAllFPromotionRulesdValidProducts() {
        return database.fPromotionRulesdValidProductsDao().getAllFPromotionRulesdValidProducts();
    }
    public List<FPromotionRulesdValidProducts> getAllFPromotionRulesdValidProductsById(Integer id) {
        return database.fPromotionRulesdValidProductsDao().getAllById(id);
    }
    public List<FPromotionRulesdValidProducts> getAllFPromotionRulesdValidProductsByParentId(Integer id) {
        return database.fPromotionRulesdValidProductsDao().getAllByParentId(id);
    }




}
