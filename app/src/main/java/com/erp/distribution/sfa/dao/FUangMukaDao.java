package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FUangMuka;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FUangMukaDao {
    /**
     * @param fUangMuka
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FUangMuka fUangMuka);
    @Update
    void update(FUangMuka fUangMuka);
    @Delete
    void delete(FUangMuka fUangMuka);


    @Query("DELETE FROM fUangMuka")
    void deleteAllFUangMuka();

    @Query("SELECT * FROM fUangMuka ")
    LiveData<List<FUangMuka>> getAllFUangMukaLive();

    @Query("SELECT * FROM fUangMuka ")
    List<FUangMuka> getAllFUangMuka();


    @Query("SELECT * FROM fUangMuka WHERE id = :id ")
    List<FUangMuka> getAllById(int id);

    @Query("SELECT * FROM fUangMuka WHERE fdivisionBean = :id ")
    List<FUangMuka> getAllByDivision(int id);

}