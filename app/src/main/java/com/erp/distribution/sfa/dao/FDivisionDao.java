package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FDivision;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FDivisionDao {
    /**
     * @param fDivision
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FDivision fDivision);
    @Update
    void update(FDivision fDivision);
    @Delete
    void delete(FDivision fDivision);


    @Query("DELETE FROM fDivision")
    void deleteAllFDivision();

    @Query("SELECT * FROM fDivision ")
    LiveData<List<FDivision>> getAllFDivisionLive();

    @Query("SELECT * FROM fDivision ")
    List<FDivision> getAllFDivision();


    @Query("SELECT * FROM fDivision WHERE id = :id ")
    List<FDivision> getAllById(int id);

    @Query("SELECT * FROM fDivision WHERE fcompanyBean = :id ")
    List<FDivision> getAllByParentId(int id);

}