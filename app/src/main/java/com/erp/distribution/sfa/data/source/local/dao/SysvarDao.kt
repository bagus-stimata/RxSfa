package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.SysvarEntity
import java.sql.RowId

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface SysvarDao {
    /**
     * @param sysvarEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sysvarEntity: SysvarEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listSysvarEntity: List<SysvarEntity>)


    @Update
    fun update(sysvarEntity: SysvarEntity)

    @Delete
    fun delete(sysvarEntity: SysvarEntity)

    @Query("DELETE FROM Sysvar")
    fun deleteAllSysvar()

    @Query("SELECT * FROM sysvar WHERE id = :id ")
    fun getAllById(id: Int): SysvarEntity
    @Query("SELECT * FROM sysvar WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<SysvarEntity>


    @get:Query("SELECT * FROM sysvar ")
    val getAllSysvarEntity: List<SysvarEntity>
    @get:Query("SELECT * FROM sysvar ")
    val getAllSysvarEntityLive: LiveData<List<SysvarEntity>>

    @Query("SELECT * FROM sysvar WHERE deskripsi LIKE :deskripsi ")
    fun getAllSysvarByDeskripsi(deskripsi: String): List<SysvarEntity>
    @Query("SELECT * FROM sysvar WHERE deskripsi LIKE :deskripsi ")
    fun getAllSysvarByDeskripsiLive(deskripsi: String): LiveData<List<SysvarEntity>>


    @Query("SELECT * FROM sysvar WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<SysvarEntity>

    @Query("SELECT * FROM sysvar WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<SysvarEntity>>

    @Query("DELETE FROM sysvar WHERE sysvarId = :sysvarId ")
    fun deleteBySysvarId(sysvarId: String)

}