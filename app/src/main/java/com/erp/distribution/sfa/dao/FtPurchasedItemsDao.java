package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtPurchasedItems;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtPurchasedItemsDao {
    /**
     * @param ftPurchasedItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtPurchasedItems ftPurchasedItems);
    @Update
    void update(FtPurchasedItems ftPurchasedItems);
    @Delete
    void delete(FtPurchasedItems ftPurchasedItems);


    @Query("DELETE FROM ftPurchasedItems")
    void deleteAllFtPurchasedItems();

    @Query("SELECT * FROM ftPurchasedItems ")
    LiveData<List<FtPurchasedItems>> getAllFtPurchasedItemsLive();

    @Query("SELECT * FROM ftPurchasedItems ")
    List<FtPurchasedItems> getAllFtPurchasedItems();


    @Query("SELECT * FROM ftPurchasedItems WHERE id = :id ")
    List<FtPurchasedItems> getAllById(Long id);

    @Query("SELECT * FROM ftPurchasedItems WHERE ftPurchasehBean = :id ")
    List<FtPurchasedItems> getAllByParentId(Long id);

}