package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FPromotionRulesdValidProducts;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FPromotionRulesdValidProductsDao {
    /**
     * @param fPromotionRulesdValidProducts
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FPromotionRulesdValidProducts fPromotionRulesdValidProducts);
    @Update
    void update(FPromotionRulesdValidProducts fPromotionRulesdValidProducts);
    @Delete
    void delete(FPromotionRulesdValidProducts fPromotionRulesdValidProducts);


    @Query("DELETE FROM fPromotionRulesdValidProducts")
    void deleteAllFPromotionRulesdValidProducts();

    @Query("SELECT * FROM fPromotionRulesdValidProducts ")
    LiveData<List<FPromotionRulesdValidProducts>> getAllFPromotionRulesdValidProductsLive();

    @Query("SELECT * FROM fPromotionRulesdValidProducts ")
    List<FPromotionRulesdValidProducts> getAllFPromotionRulesdValidProducts();


    @Query("SELECT * FROM fPromotionRulesdValidProducts WHERE id = :id ")
    List<FPromotionRulesdValidProducts> getAllById(int id);

    @Query("SELECT * FROM fPromotionRulesdValidProducts WHERE fpromotionRuleshBean = :id ")
    List<FPromotionRulesdValidProducts> getAllByParentId(int id);

}