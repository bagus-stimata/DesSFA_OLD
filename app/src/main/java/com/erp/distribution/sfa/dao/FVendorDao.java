package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FVendor;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FVendorDao {
    /**
     * @param fVendor
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FVendor fVendor);
    @Update
    void update(FVendor fVendor);
    @Delete
    void delete(FVendor fVendor);


    @Query("DELETE FROM fVendor")
    void deleteAllFVendor();

    @Query("SELECT * FROM fVendor ")
    LiveData<List<FVendor>> getAllFVendorLive();

    @Query("SELECT * FROM fVendor ")
    List<FVendor> getAllFVendor();


    @Query("SELECT * FROM fVendor WHERE id = :id ")
    List<FVendor> getAllById(int id);

    @Query("SELECT * FROM fVendor WHERE fdivisionBean = :id ")
    List<FVendor> getAllByDivision(int id);

}