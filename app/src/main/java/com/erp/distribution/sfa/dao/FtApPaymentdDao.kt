package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtApPaymentd

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtApPaymentdDao {
    /**
     * @param ftApPaymentd
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftApPaymentd: FtApPaymentd?)

    @Update
    fun update(ftApPaymentd: FtApPaymentd?)

    @Delete
    fun delete(ftApPaymentd: FtApPaymentd?)

    @Query("DELETE FROM ftApPaymentd")
    fun deleteAllFtApPaymentd()

    @get:Query("SELECT * FROM ftApPaymentd ")
    val allFtApPaymentdLive: LiveData<List<FtApPaymentd?>?>?

    @get:Query("SELECT * FROM ftApPaymentd ")
    val allFtApPaymentd: List<FtApPaymentd?>?

    @Query("SELECT * FROM ftApPaymentd WHERE id = :id ")
    fun getAllById(id: Long?): List<FtApPaymentd?>?

    @Query("SELECT * FROM ftApPaymentd WHERE ftApPaymenthBean = :id ")
    fun getAllByParentId(id: Long?): List<FtApPaymentd?>?
}