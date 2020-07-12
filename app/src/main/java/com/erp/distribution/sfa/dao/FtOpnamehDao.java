package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtOpnameh;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtOpnamehDao {
    /**
     * @param ftOpnameh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtOpnameh ftOpnameh);
    @Update
    void update(FtOpnameh ftOpnameh);
    @Delete
    void delete(FtOpnameh ftOpnameh);


    @Query("DELETE FROM ftOpnameh")
    void deleteAllFtOpnameh();

    @Query("SELECT * FROM ftOpnameh ")
    LiveData<List<FtOpnameh>> getAllFtOpnamehLive();

    @Query("SELECT * FROM ftOpnameh ")
    List<FtOpnameh> getAllFtOpnameh();


    @Query("SELECT * FROM ftOpnameh WHERE refno = :refno ")
    List<FtOpnameh> getAllById(Long refno);

    @Query("SELECT * FROM ftOpnameh WHERE fdivisionBean = :id ")
    List<FtOpnameh> getAllByDivision(Integer id);

}