package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FTax;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FTaxDao {
    /**
     * @param fTax
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FTax fTax);
    @Update
    void update(FTax fTax);
    @Delete
    void delete(FTax fTax);


    @Query("DELETE FROM fTax")
    void deleteAllFTax();

    @Query("SELECT * FROM fTax ")
    LiveData<List<FTax>> getAllFTaxLive();

    @Query("SELECT * FROM fTax ")
    List<FTax> getAllFTax();


    @Query("SELECT * FROM fTax WHERE id = :id ")
    List<FTax> getAllById(int id);

    @Query("SELECT * FROM fTax WHERE fdivisionBean = :id ")
    List<FTax> getAllByDivision(int id);

}