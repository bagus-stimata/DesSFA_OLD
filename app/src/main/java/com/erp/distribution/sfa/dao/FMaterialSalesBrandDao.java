package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FMaterialSalesBrand;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FMaterialSalesBrandDao {
    /**
     * @param fMaterialSalesBrand
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FMaterialSalesBrand fMaterialSalesBrand);
    @Update
    void update(FMaterialSalesBrand fMaterialSalesBrand);
    @Delete
    void delete(FMaterialSalesBrand fMaterialSalesBrand);


    @Query("DELETE FROM fMaterialSalesBrand")
    void deleteAllFMaterialSalesBrand();

    @Query("SELECT * FROM fMaterialSalesBrand ")
    LiveData<List<FMaterialSalesBrand>> getAllFMaterialSalesBrandLive();

    @Query("SELECT * FROM fMaterialSalesBrand ")
    List<FMaterialSalesBrand> getAllFMaterialSalesBrand();


    @Query("SELECT * FROM fMaterialSalesBrand WHERE id = :id ")
    List<FMaterialSalesBrand> getAllById(int id);

    @Query("SELECT * FROM fMaterialSalesBrand WHERE fdivisionBean = :id ")
    List<FMaterialSalesBrand> getAllByDivision(int id);

}