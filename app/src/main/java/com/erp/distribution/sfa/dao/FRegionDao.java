package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FRegion;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FRegionDao {
    /**
     * @param fRegion
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FRegion fRegion);
    @Update
    void update(FRegion fRegion);
    @Delete
    void delete(FRegion fRegion);


    @Query("DELETE FROM fRegion")
    void deleteAllFRegion();

    @Query("SELECT * FROM fRegion ")
    LiveData<List<FRegion>> getAllFRegionLive();

    @Query("SELECT * FROM fRegion ")
    List<FRegion> getAllFRegion();


    @Query("SELECT * FROM fRegion WHERE id = :id ")
    List<FRegion> getAllById(int id);

    @Query("SELECT * FROM fRegion WHERE fdivisionBean = :id ")
    List<FRegion> getAllByDivision(int id);

}