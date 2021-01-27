package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPricehEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPricehDao {
    /**
     * @param ftPricehEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPricehEntity: FtPricehEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPricehEntity: List<FtPricehEntity>)


    @Update
    fun update(ftPricehEntity: FtPricehEntity?)

    @Delete
    fun delete(ftPricehEntity: FtPricehEntity?)

    @Query("DELETE FROM ftPriceh")
    fun deleteAllFtPriceh()

    @get:Query("SELECT * FROM ftPriceh ")
    val allFtPricehEntityLive: LiveData<List<FtPricehEntity?>?>?

    @get:Query("SELECT * FROM ftPriceh ")
    val allFtPricehEntity: List<FtPricehEntity?>?

    @Query("SELECT * FROM ftPriceh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtPricehEntity?>?

    @Query("SELECT * FROM ftPriceh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtPricehEntity?>?
}