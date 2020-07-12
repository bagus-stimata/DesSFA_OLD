package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtApPaymentd;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtApPaymentdDao {
    /**
     * @param ftApPaymentd
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtApPaymentd ftApPaymentd);
    @Update
    void update(FtApPaymentd ftApPaymentd);
    @Delete
    void delete(FtApPaymentd ftApPaymentd);


    @Query("DELETE FROM ftApPaymentd")
    void deleteAllFtApPaymentd();

    @Query("SELECT * FROM ftApPaymentd ")
    LiveData<List<FtApPaymentd>> getAllFtApPaymentdLive();

    @Query("SELECT * FROM ftApPaymentd ")
    List<FtApPaymentd> getAllFtApPaymentd();


    @Query("SELECT * FROM ftApPaymentd WHERE id = :id ")
    List<FtApPaymentd> getAllById(Long id);

    @Query("SELECT * FROM ftApPaymentd WHERE ftApPaymenthBean = :id ")
    List<FtApPaymentd> getAllByParentId(Long id);

}