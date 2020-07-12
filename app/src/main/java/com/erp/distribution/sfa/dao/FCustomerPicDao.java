package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FCustomerPic;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FCustomerPicDao {
    /**
     * @param fCustomerPic
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FCustomerPic fCustomerPic);
    @Update
    void update(FCustomerPic fCustomerPic);
    @Delete
    void delete(FCustomerPic fCustomerPic);


    @Query("DELETE FROM fCustomerPic")
    void deleteAllFCustomerPic();

    @Query("SELECT * FROM fCustomerPic ")
    LiveData<List<FCustomerPic>> getAllFCustomerPicLive();

    @Query("SELECT * FROM fCustomerPic ")
    List<FCustomerPic> getAllFCustomerPic();


    @Query("SELECT * FROM fCustomerPic WHERE id = :id ")
    List<FCustomerPic> getAllById(int id);

    @Query("SELECT * FROM fCustomerPic WHERE fcustomerBean = :id ")
    List<FCustomerPic> getAllByParentId(int id);

}