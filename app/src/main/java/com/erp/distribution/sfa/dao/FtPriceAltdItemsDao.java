package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtPriceAltdItems;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtPriceAltdItemsDao {
    /**
     * @param ftPriceAltdItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtPriceAltdItems ftPriceAltdItems);
    @Update
    void update(FtPriceAltdItems ftPriceAltdItems);
    @Delete
    void delete(FtPriceAltdItems ftPriceAltdItems);


    @Query("DELETE FROM ftPriceAltdItems")
    void deleteAllFtPriceAltdItems();

    @Query("SELECT * FROM ftPriceAltdItems ")
    LiveData<List<FtPriceAltdItems>> getAllFtPriceAltdItemsLive();

    @Query("SELECT * FROM ftPriceAltdItems ")
    List<FtPriceAltdItems> getAllFtPriceAltdItems();


    @Query("SELECT * FROM ftPriceAltdItems WHERE id = :id ")
    List<FtPriceAltdItems> getAllById(Long id);

    @Query("SELECT * FROM ftPriceAltdItems WHERE ftPriceAlthBean = :id ")
    List<FtPriceAltdItems> getAllByDivision(Long id);

}