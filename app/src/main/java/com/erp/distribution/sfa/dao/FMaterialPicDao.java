package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FMaterialPic;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FMaterialPicDao {
    /**
     * @param fMaterialPic
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FMaterialPic fMaterialPic);
    @Update
    void update(FMaterialPic fMaterialPic);
    @Delete
    void delete(FMaterialPic fMaterialPic);


    @Query("DELETE FROM fMaterialPic")
    void deleteAllFMaterialPic();

    @Query("SELECT * FROM fMaterialPic ")
    LiveData<List<FMaterialPic>> getAllFMaterialPicLive();

    @Query("SELECT * FROM fMaterialPic ")
    List<FMaterialPic> getAllFMaterialPic();


    @Query("SELECT * FROM fMaterialPic WHERE id = :id ")
    List<FMaterialPic> getAllById(int id);

    @Query("SELECT * FROM fMaterialPic WHERE fmaterialBean = :id ")
    List<FMaterialPic> getAllByParentId(int id);

}