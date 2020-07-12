package com.erp.distribution.sfa.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.model.FArea;
import com.erp.distribution.sfa.model.FCompany;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FCompanyDao {

    @Insert
    void insert(FCompany fCompany);
    @Update
    void update(FCompany fCompany);
    @Delete
    void delete(FCompany fCompany);

    @Query("DELETE FROM fCompany")
    void deleteAllFCompany();

    @Query("SELECT * FROM fCompany ")
    LiveData<List<FCompany>> getAllFCompanyLive();

    @Query("SELECT * FROM fCompany ")
    List<FCompany> getAllFCompany();


    @Query("SELECT * FROM fCompany WHERE id = :id ")
    List<FCompany> getAllById(int id);


}