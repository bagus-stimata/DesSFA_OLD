package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FWarehouse;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FWarehouseDao {
    /**
     * @param fWarehouse
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FWarehouse fWarehouse);
    @Update
    void update(FWarehouse fWarehouse);
    @Delete
    void delete(FWarehouse fWarehouse);


    @Query("DELETE FROM fWarehouse")
    void deleteAllFWarehouse();

    @Query("SELECT * FROM fWarehouse ")
    LiveData<List<FWarehouse>> getAllFWarehouseLive();

    @Query("SELECT * FROM fWarehouse ")
    List<FWarehouse> getAllFWarehouse();


    @Query("SELECT * FROM fWarehouse WHERE id = :id ")
    List<FWarehouse> getAllById(int id);

    @Query("SELECT * FROM fWarehouse WHERE fdivisionBean = :id ")
    List<FWarehouse> getAllByDivision(int id);

}