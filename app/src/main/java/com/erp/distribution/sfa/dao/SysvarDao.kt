package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.Sysvar

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface SysvarDao {
    /**
     * @param sysvar
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(sysvar: Sysvar?)

    @Update
    fun update(sysvar: Sysvar?)

    @Delete
    fun delete(sysvar: Sysvar?)

    @Query("DELETE FROM sysvar")
    fun deleteAllSysvar()

    @get:Query("SELECT * FROM sysvar ")
    val allSysvarLive: LiveData<List<Sysvar?>?>?

    @get:Query("SELECT * FROM sysvar ")
    val allSysvar: List<Sysvar?>?

    @Query("SELECT * FROM sysvar WHERE id = :id ")
    fun getAllById(id: Int): List<Sysvar?>?

    @Query("SELECT * FROM sysvar WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<Sysvar?>?
}