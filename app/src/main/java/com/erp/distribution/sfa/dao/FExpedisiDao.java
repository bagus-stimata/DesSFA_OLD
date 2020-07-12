package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FExpedisi;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FExpedisiDao {
    /**
     * @param fExpedisi
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FExpedisi fExpedisi);
    @Update
    void update(FExpedisi fExpedisi);
    @Delete
    void delete(FExpedisi fExpedisi);


    @Query("DELETE FROM fexpedisi")
    void deleteAllFExpedisi();

    @Query("SELECT * FROM fexpedisi ")
    LiveData<List<FExpedisi>> getAllFExpedisiLive();

    @Query("SELECT * FROM fexpedisi ")
    List<FExpedisi> getAllFExpedisi();


    @Query("SELECT * FROM fexpedisi WHERE id = :id ")
    List<FExpedisi> getAllById(int id);

    @Query("SELECT * FROM fexpedisi WHERE fdivisionBean = :id ")
    List<FExpedisi> getAllByDivision(int id);

}