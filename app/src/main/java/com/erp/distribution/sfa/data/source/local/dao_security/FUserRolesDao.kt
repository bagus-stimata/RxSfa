package com.erp.distribution.sfa.data.source.local.dao_security

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity_security.FUserRolesEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FUserRolesDao {
    /**
     * @param fUserRolesEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fUserRolesEntity: FUserRolesEntity)

    @Update
    fun update(fUserRolesEntity: FUserRolesEntity)

    @Delete
    fun delete(fUserRolesEntity: FUserRolesEntity)

    @Query("DELETE FROM fUserRoles")
    fun deleteAllFUserRoles()

    @get:Query("SELECT * FROM fUserRoles ")
    val allFUserRolesEntityLive: LiveData<List<FUserRolesEntity>>

    @get:Query("SELECT * FROM fUserRoles ")
    val allFUserRoleEntities: List<FUserRolesEntity>

    @Query("SELECT * FROM fUserRoles WHERE id = :id ")
    fun getAllById(id: Int): List<FUserRolesEntity>

    @Query("SELECT * FROM fUserRoles WHERE fuserBeanInt = :id ")
    fun getAllByParentId(id: Int): List<FUserRolesEntity>

    @Query("SELECT * FROM fUserRoles WHERE fuserBeanInt = :id ")
    fun getAllByParentIdLive(id: Int): LiveData<List<FUserRolesEntity>>
}