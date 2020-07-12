package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FMaterialGroup2;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FMaterialGroup2Dao {
    /**
     * @param fMaterialGroup2
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FMaterialGroup2 fMaterialGroup2);
    @Update
    void update(FMaterialGroup2 fMaterialGroup2);
    @Delete
    void delete(FMaterialGroup2 fMaterialGroup2);


    @Query("DELETE FROM fMaterialGroup2")
    void deleteAllFMaterialGroup2();

    @Query("SELECT * FROM fMaterialGroup2 ")
    LiveData<List<FMaterialGroup2>> getAllFMaterialGroup2Live();

    @Query("SELECT * FROM fMaterialGroup2 ")
    List<FMaterialGroup2> getAllFMaterialGroup2();


    @Query("SELECT * FROM fMaterialGroup2 WHERE id = :id ")
    List<FMaterialGroup2> getAllById(int id);

    @Query("SELECT * FROM fMaterialGroup2 WHERE fmaterialGroup1Bean = :id ")
    List<FMaterialGroup2> getAllByParentId(int id);

}