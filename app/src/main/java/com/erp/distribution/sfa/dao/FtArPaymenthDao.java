package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtArPaymenth;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtArPaymenthDao {
    /**
     * @param ftArPaymenth
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtArPaymenth ftArPaymenth);
    @Update
    void update(FtArPaymenth ftArPaymenth);
    @Delete
    void delete(FtArPaymenth ftArPaymenth);


    @Query("DELETE FROM ftArPaymenth")
    void deleteAllFtArPaymenth();

    @Query("SELECT * FROM ftArPaymenth ")
    LiveData<List<FtArPaymenth>> getAllFtArPaymenthLive();

    @Query("SELECT * FROM ftArPaymenth ")
    List<FtArPaymenth> getAllFtArPaymenth();


    @Query("SELECT * FROM ftArPaymenth WHERE refno = :refno ")
    List<FtArPaymenth> getAllById(Long refno);

    @Query("SELECT * FROM ftArPaymenth WHERE fdivisionBean = :id ")
    List<FtArPaymenth> getAllByDivision(Integer id);

}