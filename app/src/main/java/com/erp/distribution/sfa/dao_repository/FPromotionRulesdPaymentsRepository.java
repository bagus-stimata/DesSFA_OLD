package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FPromotionRulesdPaymentsDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FPromotionRulesdPayments;
import com.erp.distribution.sfa.model.FPromotionRulesdPayments;

import java.util.List;

public class FPromotionRulesdPaymentsRepository {

    /**
     * @param fPromotionRulesdPayments
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FPromotionRulesdPaymentsDao fPromotionRulesdPaymentsDao;
    private LiveData<List<FPromotionRulesdPayments>> listFPromotionRulesdPaymentsLive;
    private List<FPromotionRulesdPayments> listFPromotionRulesdPayments;
    public FPromotionRulesdPaymentsRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fPromotionRulesdPaymentsDao = database.fPromotionRulesdPaymentsDao();
        listFPromotionRulesdPaymentsLive = fPromotionRulesdPaymentsDao.getAllFPromotionRulesdPaymentsLive();
    }

    public void insert(FPromotionRulesdPayments fPromotionRulesdPayments) {
        database.fPromotionRulesdPaymentsDao().insert(fPromotionRulesdPayments);
    }
    public void update(FPromotionRulesdPayments fPromotionRulesdPayments) {
        database.fPromotionRulesdPaymentsDao().update(fPromotionRulesdPayments);
    }
    public void delete(FPromotionRulesdPayments fPromotionRulesdPayments) {
        database.fPromotionRulesdPaymentsDao().delete(fPromotionRulesdPayments);
    }

    public void deleteAllFPromotionRulesdPayments() {
        database.fPromotionRulesdPaymentsDao().deleteAllFPromotionRulesdPayments();
    }
    
    
    public LiveData<List<FPromotionRulesdPayments>> getAllFPromotionRulesdPaymentsLive() {
        return listFPromotionRulesdPaymentsLive;
    }
    public List<FPromotionRulesdPayments> getAllFPromotionRulesdPayments() {
        return database.fPromotionRulesdPaymentsDao().getAllFPromotionRulesdPayments();
    }
    public List<FPromotionRulesdPayments> getAllFPromotionRulesdPaymentsById(Integer id) {
        return database.fPromotionRulesdPaymentsDao().getAllById(id);
    }
    public List<FPromotionRulesdPayments> getAllFPromotionRulesdPaymentsByParentId(Integer id) {
        return database.fPromotionRulesdPaymentsDao().getAllByParentId(id);
    }



}
