package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FMaterialGroup3;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FMaterialGroup3Dao {
    /**
     * @param fMaterialGroup3
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FMaterialGroup3 fMaterialGroup3);
    @Update
    void update(FMaterialGroup3 fMaterialGroup3);
    @Delete
    void delete(FMaterialGroup3 fMaterialGroup3);


    @Query("DELETE FROM fMaterialGroup3")
    void deleteAllFMaterialGroup3();

    @Query("SELECT * FROM fMaterialGroup3 ")
    LiveData<List<FMaterialGroup3>> getAllFMaterialGroup3Live();

    @Query("SELECT * FROM fMaterialGroup3 ")
    List<FMaterialGroup3> getAllFMaterialGroup3();


    @Query("SELECT * FROM fMaterialGroup3 WHERE id = :id ")
    List<FMaterialGroup3> getAllById(int id);

    @Query("SELECT * FROM fMaterialGroup3 WHERE fmaterialGroup2Bean = :id ")
    List<FMaterialGroup3> getAllByParentId(int id);

}