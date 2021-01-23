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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sysvar: Sysvar)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listSysvar: List<Sysvar>)


    @Update
    fun update(sysvar: Sysvar)

    @Delete
    fun delete(sysvar: Sysvar)

    @Query("DELETE FROM Sysvar")
    fun deleteAllSysvar()

    @Query("SELECT * FROM sysvar WHERE id = :id ")
    fun getAllById(id: Int): Sysvar
    @Query("SELECT * FROM sysvar WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<Sysvar>


    @get:Query("SELECT * FROM sysvar ")
    val getAllSysvar: List<Sysvar>
    @get:Query("SELECT * FROM sysvar ")
    val getAllSysvarLive: LiveData<List<Sysvar>>

    @Query("SELECT * FROM sysvar WHERE deskripsi LIKE :deskripsi ")
    fun getAllSysvarByDeskripsi(deskripsi: String): List<Sysvar>
    @Query("SELECT * FROM sysvar WHERE deskripsi LIKE :deskripsi ")
    fun getAllSysvarByDeskripsiLive(deskripsi: String): LiveData<List<Sysvar>>


    @Query("SELECT * FROM sysvar WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<Sysvar>

    @Query("SELECT * FROM sysvar WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<Sysvar>>


}