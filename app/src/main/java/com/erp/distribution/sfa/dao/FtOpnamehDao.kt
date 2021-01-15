package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtOpnameh

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtOpnamehDao {
    /**
     * @param ftOpnameh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftOpnameh: FtOpnameh?)

    @Update
    fun update(ftOpnameh: FtOpnameh?)

    @Delete
    fun delete(ftOpnameh: FtOpnameh?)

    @Query("DELETE FROM ftOpnameh")
    fun deleteAllFtOpnameh()

    @get:Query("SELECT * FROM ftOpnameh ")
    val allFtOpnamehLive: LiveData<List<FtOpnameh?>?>?

    @get:Query("SELECT * FROM ftOpnameh ")
    val allFtOpnameh: List<FtOpnameh?>?

    @Query("SELECT * FROM ftOpnameh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtOpnameh?>?

    @Query("SELECT * FROM ftOpnameh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int?): List<FtOpnameh?>?
}