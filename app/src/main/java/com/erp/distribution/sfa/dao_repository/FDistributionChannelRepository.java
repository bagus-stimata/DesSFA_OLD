package com.erp.distribution.sfa.dao_repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.erp.distribution.sfa.dao.FDistributionChannelDao;
import com.erp.distribution.sfa.database.AppDatabase;
import com.erp.distribution.sfa.model.FDistributionChannel;
import com.erp.distribution.sfa.model.FDistributionChannel;

import java.util.List;

public class FDistributionChannelRepository {

    /**
     * @param fDistributionChannel
     * Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynTask
     */
    AppDatabase database;

    private FDistributionChannelDao fDistributionChannelDao;
    private LiveData<List<FDistributionChannel>> listFDistributionChannelLive;
    private List<FDistributionChannel> listFDistributionChannel;
    public FDistributionChannelRepository(Application application) {
        database = AppDatabase.getInstance(application);
        fDistributionChannelDao = database.fDistributionChannelDao();
        listFDistributionChannelLive = fDistributionChannelDao.getAllFDistributionChannelLive();
    }

    public void insert(FDistributionChannel fDistributionChannel) {
        database.fDistributionChannelDao().insert(fDistributionChannel);
    }
    public void update(FDistributionChannel fDistributionChannel) {
        database.fDistributionChannelDao().update(fDistributionChannel);
    }
    public void delete(FDistributionChannel fDistributionChannel) {
        database.fDistributionChannelDao().delete(fDistributionChannel);
    }

    public void deleteAllFDistributionChannel() {
        database.fDistributionChannelDao().deleteAllFDistributionChannel();
    }

    public LiveData<List<FDistributionChannel>> getAllFDistributionChannelLive() {
        return listFDistributionChannelLive;
    }
    public List<FDistributionChannel> getAllFDistributionChannel() {
        return database.fDistributionChannelDao().getAllFDistributionChannel();
    }
    public List<FDistributionChannel> getAllFDistributionChannelById(Integer id) {
        return database.fDistributionChannelDao().getAllById(id);
    }
    public List<FDistributionChannel> getAllFDistributionChannelByDivision(Integer id) {
        return database.fDistributionChannelDao().getAllByDivision(id);
    }





}
