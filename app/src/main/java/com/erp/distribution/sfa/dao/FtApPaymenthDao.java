package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtApPaymenth;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtApPaymenthDao {
    /**
     * @param ftApPaymenth
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtApPaymenth ftApPaymenth);
    @Update
    void update(FtApPaymenth ftApPaymenth);
    @Delete
    void delete(FtApPaymenth ftApPaymenth);


    @Query("DELETE FROM ftApPaymenth")
    void deleteAllFtApPaymenth();

    @Query("SELECT * FROM ftApPaymenth ")
    LiveData<List<FtApPaymenth>> getAllFtApPaymenthLive();

    @Query("SELECT * FROM ftApPaymenth ")
    List<FtApPaymenth> getAllFtApPaymenth();


    @Query("SELECT * FROM ftApPaymenth WHERE refno = :refno ")
    List<FtApPaymenth> getAllById(Long refno);

    @Query("SELECT * FROM ftApPaymenth WHERE fdivisionBean = :id ")
    List<FtApPaymenth> getAllByDivision(Integer id);

}