package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtOpnamehEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtOpnamehDao {
    /**
     * @param ftOpnamehEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftOpnamehEntity: FtOpnamehEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtOpnamehEntity: List<FtOpnamehEntity>)


    @Update
    fun update(ftOpnamehEntity: FtOpnamehEntity?)

    @Delete
    fun delete(ftOpnamehEntity: FtOpnamehEntity?)

    @Query("DELETE FROM ftOpnameh")
    fun deleteAllFtOpnameh()

    @get:Query("SELECT * FROM ftOpnameh ")
    val allFtOpnamehEntityLive: LiveData<List<FtOpnamehEntity?>?>?

    @get:Query("SELECT * FROM ftOpnameh ")
    val allFtOpnamehEntity: List<FtOpnamehEntity?>?

    @Query("SELECT * FROM ftOpnameh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtOpnamehEntity?>?

    @Query("SELECT * FROM ftOpnameh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int?): List<FtOpnamehEntity?>?
}