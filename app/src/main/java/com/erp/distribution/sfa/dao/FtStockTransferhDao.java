package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FtStockTransferh;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FtStockTransferhDao {
    /**
     * @param ftStockTransferh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FtStockTransferh ftStockTransferh);
    @Update
    void update(FtStockTransferh ftStockTransferh);
    @Delete
    void delete(FtStockTransferh ftStockTransferh);


    @Query("DELETE FROM ftStockTransferh")
    void deleteAllFtStockTransferh();

    @Query("SELECT * FROM ftStockTransferh ")
    LiveData<List<FtStockTransferh>> getAllFtStockTransferhLive();

    @Query("SELECT * FROM ftStockTransferh ")
    List<FtStockTransferh> getAllFtStockTransferh();


    @Query("SELECT * FROM ftStockTransferh WHERE refno = :refno ")
    List<FtStockTransferh> getAllById(Long refno);

    @Query("SELECT * FROM ftStockTransferh WHERE fdivisionBean = :id ")
    List<FtStockTransferh> getAllByDivision(int id);

}