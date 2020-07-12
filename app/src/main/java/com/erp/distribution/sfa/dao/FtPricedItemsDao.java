package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtPricedItems;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtPricedItemsDao {
    /**
     * @param ftPricedItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtPricedItems ftPricedItems);
    @Update
    void update(FtPricedItems ftPricedItems);
    @Delete
    void delete(FtPricedItems ftPricedItems);


    @Query("DELETE FROM ftPricedItems")
    void deleteAllFtPricedItems();

    @Query("SELECT * FROM ftPricedItems ")
    LiveData<List<FtPricedItems>> getAllFtPricedItemsLive();

    @Query("SELECT * FROM ftPricedItems ")
    List<FtPricedItems> getAllFtPricedItems();


    @Query("SELECT * FROM ftPricedItems WHERE id = :id ")
    List<FtPricedItems> getAllById(Long id);

    @Query("SELECT * FROM ftPricedItems WHERE ftPricehBean = :id ")
    List<FtPricedItems> getAllByDivision(Long id);

}