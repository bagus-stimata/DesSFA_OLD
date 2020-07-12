package com.erp.distribution.sfa.security_repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erp.distribution.sfa.security_model.FUserRoles;

import java.util.List;

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
public interface FUserRolesDao {
    /**
     * @param fUserRoles
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    void insert(FUserRoles fUserRoles);
    @Update
    void update(FUserRoles fUserRoles);
    @Delete
    void delete(FUserRoles fUserRoles);


    @Query("DELETE FROM fUserRoles")
    void deleteAllFUserRoles();

    @Query("SELECT * FROM fUserRoles ")
    LiveData<List<FUserRoles>> getAllFUserRolesLive();

    @Query("SELECT * FROM fUserRoles ")
    List<FUserRoles> getAllFUserRoles();


    @Query("SELECT * FROM fUserRoles WHERE id = :id ")
    List<FUserRoles> getAllById(int id);

    @Query("SELECT * FROM fUserRoles WHERE fuserBeanInt = :id ")
    List<FUserRoles> getAllByParentId(int id);

}