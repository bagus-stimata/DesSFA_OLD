package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtPriceAlth;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtPriceAlthDao {
    /**
     * @param ftPriceAlth
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtPriceAlth ftPriceAlth);
    @Update
    void update(FtPriceAlth ftPriceAlth);
    @Delete
    void delete(FtPriceAlth ftPriceAlth);


    @Query("DELETE FROM ftPriceAlth")
    void deleteAllFtPriceAlth();

    @Query("SELECT * FROM ftPriceAlth ")
    LiveData<List<FtPriceAlth>> getAllFtPriceAlthLive();

    @Query("SELECT * FROM ftPriceAlth ")
    List<FtPriceAlth> getAllFtPriceAlth();


    @Query("SELECT * FROM ftPriceAlth WHERE id = :id ")
    List<FtPriceAlth> getAllById(Long id);

    @Query("SELECT * FROM ftPriceAlth WHERE fdivisionBean = :id ")
    List<FtPriceAlth> getAllByDivision(Integer id);

}