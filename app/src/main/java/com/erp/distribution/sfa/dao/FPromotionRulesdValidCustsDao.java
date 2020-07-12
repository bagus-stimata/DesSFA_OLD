package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FPromotionRulesdValidCusts;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FPromotionRulesdValidCustsDao {
    /**
     * @param fPromotionRulesdValidCusts
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FPromotionRulesdValidCusts fPromotionRulesdValidCusts);
    @Update
    void update(FPromotionRulesdValidCusts fPromotionRulesdValidCusts);
    @Delete
    void delete(FPromotionRulesdValidCusts fPromotionRulesdValidCusts);


    @Query("DELETE FROM fPromotionRulesdValidCusts")
    void deleteAllFPromotionRulesdValidCusts();

    @Query("SELECT * FROM fPromotionRulesdValidCusts ")
    LiveData<List<FPromotionRulesdValidCusts>> getAllFPromotionRulesdValidCustsLive();

    @Query("SELECT * FROM fPromotionRulesdValidCusts ")
    List<FPromotionRulesdValidCusts> getAllFPromotionRulesdValidCusts();


    @Query("SELECT * FROM fPromotionRulesdValidCusts WHERE id = :id ")
    List<FPromotionRulesdValidCusts> getAllById(int id);

    @Query("SELECT * FROM fPromotionRulesdValidCusts WHERE fpromotionRuleshBean = :id ")
    List<FPromotionRulesdValidCusts> getAllByParentId(int id);

}