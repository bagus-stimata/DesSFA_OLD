package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.Sysvar;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface SysvarDao {
    /**
     * @param sysvar
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(Sysvar sysvar);
    @Update
    void update(Sysvar sysvar);
    @Delete
    void delete(Sysvar sysvar);


    @Query("DELETE FROM sysvar")
    void deleteAllSysvar();

    @Query("SELECT * FROM sysvar ")
    LiveData<List<Sysvar>> getAllSysvarLive();

    @Query("SELECT * FROM sysvar ")
    List<Sysvar> getAllSysvar();


    @Query("SELECT * FROM sysvar WHERE id = :id ")
    List<Sysvar> getAllById(int id);

    @Query("SELECT * FROM sysvar WHERE fdivisionBean = :id ")
    List<Sysvar> getAllByDivision(int id);

}