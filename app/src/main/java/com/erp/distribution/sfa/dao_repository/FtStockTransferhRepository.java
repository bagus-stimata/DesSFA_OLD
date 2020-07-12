package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FtStockTransferhDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FtStockTransferh;
import com.erp.distribution.sfa.model.FtStockTransferh;

import java.util.List;

public class FtStockTransferhRepository {

    /**
     * @param ftStockTransferh
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FtStockTransferhDao ftStockTransferhDao;
    private LiveData<List<FtStockTransferh>> listFtStockTransferhLive;
    private List<FtStockTransferh> listFtStockTransferh;
    public FtStockTransferhRepository(Application application) {
        database = AppDatabase.getInstance(application);
        ftStockTransferhDao = database.ftStockTransferhDao();
        listFtStockTransferhLive = ftStockTransferhDao.getAllFtStockTransferhLive();
    }

    public void insert(FtStockTransferh ftStockTransferh) {
        database.ftStockTransferhDao().insert(ftStockTransferh);
    }
    public void update(FtStockTransferh ftStockTransferh) {
        database.ftStockTransferhDao().update(ftStockTransferh);
    }
    public void delete(FtStockTransferh ftStockTransferh) {
        database.ftStockTransferhDao().delete(ftStockTransferh);
    }

    public void deleteAllFtStockTransferh() {
        database.ftStockTransferhDao().deleteAllFtStockTransferh();
    }


    public LiveData<List<FtStockTransferh>> getAllFtStockTransferhLive() {
        return listFtStockTransferhLive;
    }
    public List<FtStockTransferh> getAllFtStockTransferh() {
        return database.ftStockTransferhDao().getAllFtStockTransferh();
    }
    public List<FtStockTransferh> getAllFtStockTransferhById(Long id) {
        return database.ftStockTransferhDao().getAllById(id);
    }
    public List<FtStockTransferh> getAllFtStockTransferhByDivision(Integer id) {
        return database.ftStockTransferhDao().getAllByDivision(id);
    }




}
