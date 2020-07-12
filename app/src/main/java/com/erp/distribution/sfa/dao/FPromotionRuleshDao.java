package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FPromotionRulesh;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FPromotionRuleshDao {
    /**
     * @param fPromotionRulesh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FPromotionRulesh fPromotionRulesh);
    @Update
    void update(FPromotionRulesh fPromotionRulesh);
    @Delete
    void delete(FPromotionRulesh fPromotionRulesh);


    @Query("DELETE FROM fPromotionRulesh")
    void deleteAllFPromotionRulesh();

    @Query("SELECT * FROM fPromotionRulesh ")
    LiveData<List<FPromotionRulesh>> getAllFPromotionRuleshLive();

    @Query("SELECT * FROM fPromotionRulesh ")
    List<FPromotionRulesh> getAllFPromotionRulesh();


    @Query("SELECT * FROM fPromotionRulesh WHERE id = :id ")
    List<FPromotionRulesh> getAllById(int id);

    @Query("SELECT * FROM fPromotionRulesh WHERE fdivisionBean = :id ")
    List<FPromotionRulesh> getAllByDivision(int id);

}