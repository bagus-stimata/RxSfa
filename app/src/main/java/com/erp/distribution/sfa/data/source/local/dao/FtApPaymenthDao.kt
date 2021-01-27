package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtApPaymenthEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtApPaymenthDao {
    /**
     * @param ftApPaymenthEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftApPaymenthEntity: FtApPaymenthEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtApPaymenthEntity: List<FtApPaymenthEntity>)


    @Update
    fun update(ftApPaymenthEntity: FtApPaymenthEntity?)

    @Delete
    fun delete(ftApPaymenthEntity: FtApPaymenthEntity?)

    @Query("DELETE FROM ftApPaymenth")
    fun deleteAllFtApPaymenth()

    @get:Query("SELECT * FROM ftApPaymenth ")
    val allFtApPaymenthEntityLive: LiveData<List<FtApPaymenthEntity?>?>?

    @get:Query("SELECT * FROM ftApPaymenth ")
    val allFtApPaymenthEntity: List<FtApPaymenthEntity?>?

    @Query("SELECT * FROM ftApPaymenth WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtApPaymenthEntity?>?

    @Query("SELECT * FROM ftApPaymenth WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int?): List<FtApPaymenthEntity?>?
}