package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FMaterial;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FMaterialDao {
    /**
     * @param fMaterial
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FMaterial fMaterial);
    @Update
    void update(FMaterial fMaterial);
    @Delete
    void delete(FMaterial fMaterial);


    @Query("DELETE FROM fMaterial")
    void deleteAllFMaterial();

    @Query("SELECT * FROM fMaterial ")
    LiveData<List<FMaterial>> getAllFMaterialLive();

    @Query("SELECT * FROM fMaterial ")
    List<FMaterial> getAllFMaterial();


    @Query("SELECT * FROM fMaterial WHERE id = :id ")
    List<FMaterial> getAllById(int id);

    @Query("SELECT * FROM fMaterial WHERE fdivisionBean = :id ")
    List<FMaterial> getAllByDivision(int id);

}