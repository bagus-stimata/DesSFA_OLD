package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FStock;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FStockDao {
    /**
     * @param fStock
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FStock fStock);
    @Update
    void update(FStock fStock);
    @Delete
    void delete(FStock fStock);


    @Query("DELETE FROM fStock")
    void deleteAllFStock();

    @Query("SELECT * FROM fStock ")
    LiveData<List<FStock>> getAllFStockLive();

    @Query("SELECT * FROM fStock ")
    List<FStock> getAllFStock();


    @Query("SELECT * FROM fStock WHERE refno = :refno ")
    List<FStock> getAllById(Long refno);

    @Query("SELECT * FROM fStock WHERE fwarehouseBean = :fwarehouseBean and fmaterialBean = :fmaterialBean ")
    List<FStock> getAllByParentId(Integer fwarehouseBean, Integer fmaterialBean);

}