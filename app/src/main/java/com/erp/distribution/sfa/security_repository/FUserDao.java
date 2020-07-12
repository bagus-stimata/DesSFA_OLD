package com.erp.distribution.sfa.security_repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.security_model.FUser;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FUserDao {
    /**
     * @param fUser
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FUser fUser);
    @Update
    void update(FUser fUser);
    @Delete
    void delete(FUser fUser);

    @Query("DELETE FROM fUser")
    void deleteAllFUser();

    @Query("SELECT * FROM fUser ")
    LiveData<List<FUser>> getAllFUserLive();

    @Query("SELECT * FROM fUser ")
    List<FUser> getAllFUser();

    @Query("SELECT * FROM fUser WHERE id = :id ")
    List<FUser> getAllById(int id);

    @Query("SELECT * FROM fUser WHERE fdivisionBean = :id ")
    List<FUser> getAllByDivision(int id);

}