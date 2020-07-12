package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FParamDiskonItem;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FParamDiskonItemDao {
    /**
     * @param fParamDiskonItem
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FParamDiskonItem fParamDiskonItem);
    @Update
    void update(FParamDiskonItem fParamDiskonItem);
    @Delete
    void delete(FParamDiskonItem fParamDiskonItem);


    @Query("DELETE FROM fParamDiskonItem")
    void deleteAllFParamDiskonItem();

    @Query("SELECT * FROM fParamDiskonItem ")
    LiveData<List<FParamDiskonItem>> getAllFParamDiskonItemLive();

    @Query("SELECT * FROM fParamDiskonItem ")
    List<FParamDiskonItem> getAllFParamDiskonItem();


    @Query("SELECT * FROM fParamDiskonItem WHERE id = :id ")
    List<FParamDiskonItem> getAllById(int id);

    @Query("SELECT * FROM fParamDiskonItem WHERE fdivisionBean = :id ")
    List<FParamDiskonItem> getAllByDivision(int id);

}