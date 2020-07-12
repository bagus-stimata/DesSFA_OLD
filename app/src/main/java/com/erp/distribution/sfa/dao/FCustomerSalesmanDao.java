package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FCustomerSalesman;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FCustomerSalesmanDao {
    /**
     * @param fCustomerSalesman
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FCustomerSalesman fCustomerSalesman);
    @Update
    void update(FCustomerSalesman fCustomerSalesman);
    @Delete
    void delete(FCustomerSalesman fCustomerSalesman);


    @Query("DELETE FROM fCustomerSalesman")
    void deleteAllFCustomerSalesman();

    @Query("SELECT * FROM fCustomerSalesman ")
    LiveData<List<FCustomerSalesman>> getAllFCustomerSalesmanLive();

    @Query("SELECT * FROM fCustomerSalesman ")
    List<FCustomerSalesman> getAllFCustomerSalesman();


    @Query("SELECT * FROM fCustomerSalesman WHERE id = :id ")
    List<FCustomerSalesman> getAllById(int id);

    @Query("SELECT * FROM fCustomerSalesman WHERE fcustomerBean = :id ")
    List<FCustomerSalesman> getAllByDivision(int id);

}