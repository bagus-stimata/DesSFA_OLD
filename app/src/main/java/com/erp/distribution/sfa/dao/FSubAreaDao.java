package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FSubArea;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FSubAreaDao {
    /**
     * @param fSubArea
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FSubArea fSubArea);
    @Update
    void update(FSubArea fSubArea);
    @Delete
    void delete(FSubArea fSubArea);


    @Query("DELETE FROM fSubArea")
    void deleteAllFSubArea();

    @Query("SELECT * FROM fSubArea ")
    LiveData<List<FSubArea>> getAllFSubAreaLive();

    @Query("SELECT * FROM fSubArea ")
    List<FSubArea> getAllFSubArea();


    @Query("SELECT * FROM fSubArea WHERE id = :id ")
    List<FSubArea> getAllById(int id);

    @Query("SELECT * FROM fSubArea WHERE fareaBean = :id ")
    List<FSubArea> getAllByParentId(int id);

}