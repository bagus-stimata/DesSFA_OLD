package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FArea;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FAreaDao {
    /**
     * @param fArea
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FArea fArea);
    @Update
    void update(FArea fArea);
    @Delete
    void delete(FArea fArea);

    @Query("DELETE FROM fArea")
    void deleteAllFArea();

    @Query("SELECT * FROM fArea ")
    LiveData<List<FArea>> getAllFAreaLive();

    @Query("SELECT * FROM fArea ")
    List<FArea> getAllFArea();

    @Query("SELECT * FROM fArea WHERE id = :id ")
    List<FArea> getAllById(int id);

    @Query("SELECT * FROM fArea WHERE fdivisionBean = :id ")
    List<FArea> getAllByDivision(int id);

}