package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FCustomerGroup;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FCustomerGroupDao {
    /**
     * @param fCustomerGroup
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FCustomerGroup fCustomerGroup);
    @Update
    void update(FCustomerGroup fCustomerGroup);
    @Delete
    void delete(FCustomerGroup fCustomerGroup);


    @Query("DELETE FROM fCustomerGroup")
    void deleteAllFCustomerGroup();

    @Query("SELECT * FROM fCustomerGroup ")
    LiveData<List<FCustomerGroup>> getAllFCustomerGroupLive();

    @Query("SELECT * FROM fCustomerGroup ")
    List<FCustomerGroup> getAllFCustomerGroup();


    @Query("SELECT * FROM fCustomerGroup WHERE id = :id ")
    List<FCustomerGroup> getAllById(int id);

    @Query("SELECT * FROM fCustomerGroup WHERE fdivisionBean = :id ")
    List<FCustomerGroup> getAllByDivision(int id);

}