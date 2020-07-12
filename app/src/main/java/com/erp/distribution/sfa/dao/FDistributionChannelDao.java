package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FDistributionChannel;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FDistributionChannelDao {
    /**
     * @param fDistributionChannel
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FDistributionChannel fDistributionChannel);
    @Update
    void update(FDistributionChannel fDistributionChannel);
    @Delete
    void delete(FDistributionChannel fDistributionChannel);


    @Query("DELETE FROM fDistributionChannel")
    void deleteAllFDistributionChannel();

    @Query("SELECT * FROM fDistributionChannel ")
    LiveData<List<FDistributionChannel>> getAllFDistributionChannelLive();

    @Query("SELECT * FROM fDistributionChannel ")
    List<FDistributionChannel> getAllFDistributionChannel();


    @Query("SELECT * FROM fDistributionChannel WHERE id = :id ")
    List<FDistributionChannel> getAllById(int id);

    @Query("SELECT * FROM fDistributionChannel WHERE fdivisionBean = :id ")
    List<FDistributionChannel> getAllByDivision(int id);

}