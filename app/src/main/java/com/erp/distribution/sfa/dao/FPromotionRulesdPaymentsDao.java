package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FPromotionRulesdPayments;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FPromotionRulesdPaymentsDao {
    /**
     * @param fPromotionRulesdPayments
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FPromotionRulesdPayments fPromotionRulesdPayments);
    @Update
    void update(FPromotionRulesdPayments fPromotionRulesdPayments);
    @Delete
    void delete(FPromotionRulesdPayments fPromotionRulesdPayments);


    @Query("DELETE FROM fPromotionRulesdPayments")
    void deleteAllFPromotionRulesdPayments();

    @Query("SELECT * FROM fPromotionRulesdPayments ")
    LiveData<List<FPromotionRulesdPayments>> getAllFPromotionRulesdPaymentsLive();

    @Query("SELECT * FROM fPromotionRulesdPayments ")
    List<FPromotionRulesdPayments> getAllFPromotionRulesdPayments();


    @Query("SELECT * FROM fPromotionRulesdPayments WHERE id = :id ")
    List<FPromotionRulesdPayments> getAllById(int id);

    @Query("SELECT * FROM fPromotionRulesdPayments WHERE fpromotionRuleshBean = :id ")
    List<FPromotionRulesdPayments> getAllByParentId(int id);

}