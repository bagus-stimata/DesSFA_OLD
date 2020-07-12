package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FCustomer;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FCustomerDao {
    /**
     * @param fCustomer
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FCustomer fCustomer);
    @Update
    void update(FCustomer fCustomer);
    @Delete
    void delete(FCustomer fCustomer);

    @Query("DELETE FROM fCustomer")
    void deleteAllFCustomer();

    @Query("SELECT * FROM fCustomer ")
    LiveData<List<FCustomer>> getAllFCustomerLive();

    @Query("SELECT * FROM fCustomer ")
    List<FCustomer> getAllFCustomer();

    @Query("SELECT * FROM fCustomer WHERE id = :id ")
    List<FCustomer> getAllById(int id);

    @Query("SELECT * FROM fCustomer WHERE fdivisionBean = :id ")
    List<FCustomer> getAllByDivision(int id);

}