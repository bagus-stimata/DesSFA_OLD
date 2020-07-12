package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtStockTransferdItems;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtStockTransferdItemsDao {
    /**
     * @param ftStockTransferdItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtStockTransferdItems ftStockTransferdItems);
    @Update
    void update(FtStockTransferdItems ftStockTransferdItems);
    @Delete
    void delete(FtStockTransferdItems ftStockTransferdItems);


    @Query("DELETE FROM ftStockTransferdItems")
    void deleteAllFtStockTransferdItems();

    @Query("SELECT * FROM ftStockTransferdItems ")
    LiveData<List<FtStockTransferdItems>> getAllFtStockTransferdItemsLive();

    @Query("SELECT * FROM ftStockTransferdItems ")
    List<FtStockTransferdItems> getAllFtStockTransferdItems();


    @Query("SELECT * FROM ftStockTransferdItems WHERE id = :id ")
    List<FtStockTransferdItems> getAllById(Long id);

    @Query("SELECT * FROM ftStockTransferdItems WHERE ftStockTransferhBean = :id ")
    List<FtStockTransferdItems> getAllByDivision(Long id);

}