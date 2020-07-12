package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FGiro;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FGiroDao {
    /**
     * @param fGiro
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FGiro fGiro);
    @Update
    void update(FGiro fGiro);
    @Delete
    void delete(FGiro fGiro);


    @Query("DELETE FROM fGiro")
    void deleteAllFGiro();

    @Query("SELECT * FROM fGiro ")
    LiveData<List<FGiro>> getAllFGiroLive();

    @Query("SELECT * FROM fGiro ")
    List<FGiro> getAllFGiro();


    @Query("SELECT * FROM fGiro WHERE id = :id ")
    List<FGiro> getAllById(int id);

    @Query("SELECT * FROM fGiro WHERE fdivisionBean = :id ")
    List<FGiro> getAllByDivision(int id);

}