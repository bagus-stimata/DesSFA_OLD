package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtOpnamedItems;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtOpnamedItemsDao {
    /**
     * @param ftOpnamedItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtOpnamedItems ftOpnamedItems);
    @Update
    void update(FtOpnamedItems ftOpnamedItems);
    @Delete
    void delete(FtOpnamedItems ftOpnamedItems);


    @Query("DELETE FROM ftOpnamedItems")
    void deleteAllFtOpnamedItems();

    @Query("SELECT * FROM ftOpnamedItems ")
    LiveData<List<FtOpnamedItems>> getAllFtOpnamedItemsLive();

    @Query("SELECT * FROM ftOpnamedItems ")
    List<FtOpnamedItems> getAllFtOpnamedItems();


    @Query("SELECT * FROM ftOpnamedItems WHERE id = :id ")
    List<FtOpnamedItems> getAllById(Long id);

    @Query("SELECT * FROM ftOpnamedItems WHERE ftOpnamehBean = :id ")
    List<FtOpnamedItems> getAllByParentId(Long id);

}