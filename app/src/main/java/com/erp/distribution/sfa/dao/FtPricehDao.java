package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtPriceh;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtPricehDao {
    /**
     * @param ftPriceh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtPriceh ftPriceh);
    @Update
    void update(FtPriceh ftPriceh);
    @Delete
    void delete(FtPriceh ftPriceh);


    @Query("DELETE FROM ftPriceh")
    void deleteAllFtPriceh();

    @Query("SELECT * FROM ftPriceh ")
    LiveData<List<FtPriceh>> getAllFtPricehLive();

    @Query("SELECT * FROM ftPriceh ")
    List<FtPriceh> getAllFtPriceh();


    @Query("SELECT * FROM ftPriceh WHERE refno = :refno ")
    List<FtPriceh> getAllById(Long refno);

    @Query("SELECT * FROM ftPriceh WHERE fdivisionBean = :id ")
    List<FtPriceh> getAllByDivision(int id);

}