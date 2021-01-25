package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPriceh

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPricehDao {
    /**
     * @param ftPriceh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPriceh: FtPriceh?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPriceh: List<FtPriceh>)


    @Update
    fun update(ftPriceh: FtPriceh?)

    @Delete
    fun delete(ftPriceh: FtPriceh?)

    @Query("DELETE FROM ftPriceh")
    fun deleteAllFtPriceh()

    @get:Query("SELECT * FROM ftPriceh ")
    val allFtPricehLive: LiveData<List<FtPriceh?>?>?

    @get:Query("SELECT * FROM ftPriceh ")
    val allFtPriceh: List<FtPriceh?>?

    @Query("SELECT * FROM ftPriceh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtPriceh?>?

    @Query("SELECT * FROM ftPriceh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtPriceh?>?
}