package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtSalesdItems;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtSalesdItemsDao {
    /**
     * @param ftSalesdItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtSalesdItems ftSalesdItems);
    @Update
    void update(FtSalesdItems ftSalesdItems);
    @Delete
    void delete(FtSalesdItems ftSalesdItems);


    @Query("DELETE FROM ftSalesdItems")
    void deleteAllFtSalesdItems();

    @Query("SELECT * FROM ftSalesdItems ")
    LiveData<List<FtSalesdItems>> getAllFtSalesdItemsLive();

    @Query("SELECT * FROM ftSalesdItems ")
    List<FtSalesdItems> getAllFtSalesdItems();


    @Query("SELECT * FROM ftSalesdItems WHERE id = :id ")
    List<FtSalesdItems> getAllById(Long id);

    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :id ")
    List<FtSalesdItems> getAllByParentId(Long id);

}