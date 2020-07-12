package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FParamDiskonItemVendor;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FParamDiskonItemVendorDao {
    /**
     * @param fParamDiskonItemVendor
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FParamDiskonItemVendor fParamDiskonItemVendor);
    @Update
    void update(FParamDiskonItemVendor fParamDiskonItemVendor);
    @Delete
    void delete(FParamDiskonItemVendor fParamDiskonItemVendor);


    @Query("DELETE FROM fParamDiskonItemVendor")
    void deleteAllFParamDiskonItemVendor();

    @Query("SELECT * FROM fParamDiskonItemVendor ")
    LiveData<List<FParamDiskonItemVendor>> getAllFParamDiskonItemVendorLive();

    @Query("SELECT * FROM fParamDiskonItemVendor ")
    List<FParamDiskonItemVendor> getAllFParamDiskonItemVendor();


    @Query("SELECT * FROM fParamDiskonItemVendor WHERE id = :id ")
    List<FParamDiskonItemVendor> getAllById(int id);

    @Query("SELECT * FROM fParamDiskonItemVendor WHERE fdivisionBean = :id ")
    List<FParamDiskonItemVendor> getAllByDivision(int id);

}