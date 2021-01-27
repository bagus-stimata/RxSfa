package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtApPaymentdEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtApPaymentdDao {
    /**
     * @param ftApPaymentdEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftApPaymentdEntity: FtApPaymentdEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtApPaymentdEntity: List<FtApPaymentdEntity>)


    @Update
    fun update(ftApPaymentdEntity: FtApPaymentdEntity?)

    @Delete
    fun delete(ftApPaymentdEntity: FtApPaymentdEntity?)

    @Query("DELETE FROM ftApPaymentd")
    fun deleteAllFtApPaymentd()

    @get:Query("SELECT * FROM ftApPaymentd ")
    val allFtApPaymentdEntityLive: LiveData<List<FtApPaymentdEntity?>?>?

    @get:Query("SELECT * FROM ftApPaymentd ")
    val allFtApPaymentdEntity: List<FtApPaymentdEntity?>?

    @Query("SELECT * FROM ftApPaymentd WHERE id = :id ")
    fun getAllById(id: Long?): List<FtApPaymentdEntity?>?

    @Query("SELECT * FROM ftApPaymentd WHERE ftApPaymenthBean = :id ")
    fun getAllByParentId(id: Long?): List<FtApPaymentdEntity?>?
}