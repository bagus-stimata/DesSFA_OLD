package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtArPaymentd;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtArPaymentdDao {
    /**
     * @param ftArPaymentd
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtArPaymentd ftArPaymentd);
    @Update
    void update(FtArPaymentd ftArPaymentd);
    @Delete
    void delete(FtArPaymentd ftArPaymentd);


    @Query("DELETE FROM ftArPaymentd")
    void deleteAllFtArPaymentd();

    @Query("SELECT * FROM ftArPaymentd ")
    LiveData<List<FtArPaymentd>> getAllFtArPaymentdLive();

    @Query("SELECT * FROM ftArPaymentd ")
    List<FtArPaymentd> getAllFtArPaymentd();


    @Query("SELECT * FROM ftArPaymentd WHERE id = :id ")
    List<FtArPaymentd> getAllById(Long id);

    @Query("SELECT * FROM ftArPaymentd WHERE ftArPaymenthBean = :id ")
    List<FtArPaymentd> getAllByParentId(Long id);

}