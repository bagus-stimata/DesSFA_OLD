package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FMaterialGroup1;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FMaterialGroup1Dao {
    /**
     * @param fMaterialGroup1
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FMaterialGroup1 fMaterialGroup1);
    @Update
    void update(FMaterialGroup1 fMaterialGroup1);
    @Delete
    void delete(FMaterialGroup1 fMaterialGroup1);


    @Query("DELETE FROM fMaterialGroup1")
    void deleteAllFMaterialGroup1();

    @Query("SELECT * FROM fMaterialGroup1 ")
    LiveData<List<FMaterialGroup1>> getAllFMaterialGroup1Live();

    @Query("SELECT * FROM fMaterialGroup1 ")
    List<FMaterialGroup1> getAllFMaterialGroup1();


    @Query("SELECT * FROM fMaterialGroup1 WHERE id = :id ")
    List<FMaterialGroup1> getAllById(int id);

    @Query("SELECT * FROM fMaterialGroup1 WHERE fdivisionBean = :id ")
    List<FMaterialGroup1> getAllByDivision(int id);

}