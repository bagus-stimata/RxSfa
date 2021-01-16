package com.erp.distribution.sfa.security_repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.security_model.FUserRoles

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FUserRolesDao {
    /**
     * @param fUserRoles
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fUserRoles: FUserRoles)

    @Update
    fun update(fUserRoles: FUserRoles)

    @Delete
    fun delete(fUserRoles: FUserRoles)

    @Query("DELETE FROM fUserRoles")
    fun deleteAllFUserRoles()

    @get:Query("SELECT * FROM fUserRoles ")
    val allFUserRolesLive: LiveData<List<FUserRoles>>

    @get:Query("SELECT * FROM fUserRoles ")
    val allFUserRoles: List<FUserRoles>

    @Query("SELECT * FROM fUserRoles WHERE id = :id ")
    fun getAllById(id: Int): List<FUserRoles>

    @Query("SELECT * FROM fUserRoles WHERE fuserBeanInt = :id ")
    fun getAllByParentId(id: Int): List<FUserRoles>

    @Query("SELECT * FROM fUserRoles WHERE fuserBeanInt = :id ")
    fun getAllByParentIdLive(id: Int): LiveData<List<FUserRoles>>
}