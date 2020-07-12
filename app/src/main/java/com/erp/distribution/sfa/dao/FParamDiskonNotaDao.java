package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FParamDiskonNota;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FParamDiskonNotaDao {
    /**
     * @param fParamDiskonNota
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FParamDiskonNota fParamDiskonNota);
    @Update
    void update(FParamDiskonNota fParamDiskonNota);
    @Delete
    void delete(FParamDiskonNota fParamDiskonNota);


    @Query("DELETE FROM fParamDiskonNota")
    void deleteAllFParamDiskonNota();

    @Query("SELECT * FROM fParamDiskonNota ")
    LiveData<List<FParamDiskonNota>> getAllFParamDiskonNotaLive();

    @Query("SELECT * FROM fParamDiskonNota ")
    List<FParamDiskonNota> getAllFParamDiskonNota();


    @Query("SELECT * FROM fParamDiskonNota WHERE id = :id ")
    List<FParamDiskonNota> getAllById(int id);

    @Query("SELECT * FROM fParamDiskonNota WHERE fdivisionBean = :id ")
    List<FParamDiskonNota> getAllByDivision(int id);

}