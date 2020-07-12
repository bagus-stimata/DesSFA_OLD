package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtPurchaseh;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtPurchasehDao {
    /**
     * @param ftPurchaseh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtPurchaseh ftPurchaseh);
    @Update
    void update(FtPurchaseh ftPurchaseh);
    @Delete
    void delete(FtPurchaseh ftPurchaseh);


    @Query("DELETE FROM ftPurchaseh")
    void deleteAllFtPurchaseh();

    @Query("SELECT * FROM ftPurchaseh ")
    LiveData<List<FtPurchaseh>> getAllFtPurchasehLive();

    @Query("SELECT * FROM ftPurchaseh ")
    List<FtPurchaseh> getAllFtPurchaseh();


    @Query("SELECT * FROM ftPurchaseh WHERE refno = :refno ")
    List<FtPurchaseh> getAllById(Long refno);

    @Query("SELECT * FROM ftPurchaseh WHERE fdivisionBean = :id ")
    List<FtPurchaseh> getAllByDivision(int id);

}