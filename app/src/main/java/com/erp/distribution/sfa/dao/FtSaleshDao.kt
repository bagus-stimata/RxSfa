package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtSalesh

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtSaleshDao {
    /**
     * @param ftSalesh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftSalesh: FtSalesh?)

    @Update
    fun update(ftSalesh: FtSalesh?)

    @Delete
    fun delete(ftSalesh: FtSalesh?)

    @Query("DELETE FROM ftSalesh")
    fun deleteAllFtSalesh()

    @get:Query("SELECT * FROM ftSalesh ")
    val allFtSaleshLive: LiveData<List<FtSalesh?>?>?

    @get:Query("SELECT * FROM ftSalesh ")
    val allFtSalesh: List<FtSalesh?>?

    @Query("SELECT * FROM ftSalesh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtSalesh?>?

    @Query("SELECT * FROM ftSalesh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtSalesh?>?
}