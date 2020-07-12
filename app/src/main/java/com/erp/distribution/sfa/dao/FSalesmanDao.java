package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FSalesman;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FSalesmanDao {
    /**
     * @param fSalesman
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FSalesman fSalesman);
    @Update
    void update(FSalesman fSalesman);
    @Delete
    void delete(FSalesman fSalesman);


    @Query("DELETE FROM fSalesman")
    void deleteAllFSalesman();

    @Query("SELECT * FROM fSalesman ")
    LiveData<List<FSalesman>> getAllFSalesmanLive();

    @Query("SELECT * FROM fSalesman ")
    List<FSalesman> getAllFSalesman();


    @Query("SELECT * FROM fSalesman WHERE id = :id ")
    List<FSalesman> getAllById(int id);

    @Query("SELECT * FROM fSalesman WHERE fdivisionBean = :id ")
    List<FSalesman> getAllByDivision(int id);

}