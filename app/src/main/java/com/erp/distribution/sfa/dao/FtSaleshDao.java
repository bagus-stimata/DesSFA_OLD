package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtSalesh;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtSaleshDao {
    /**
     * @param ftSalesh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtSalesh ftSalesh);
    @Update
    void update(FtSalesh ftSalesh);
    @Delete
    void delete(FtSalesh ftSalesh);


    @Query("DELETE FROM ftSalesh")
    void deleteAllFtSalesh();

    @Query("SELECT * FROM ftSalesh ")
    LiveData<List<FtSalesh>> getAllFtSaleshLive();

    @Query("SELECT * FROM ftSalesh ")
    List<FtSalesh> getAllFtSalesh();


    @Query("SELECT * FROM ftSalesh WHERE refno = :refno ")
    List<FtSalesh> getAllById(Long refno);

    @Query("SELECT * FROM ftSalesh WHERE fdivisionBean = :id ")
    List<FtSalesh> getAllByDivision(int id);

}