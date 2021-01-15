package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtArPaymentd

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtArPaymentdDao {
    /**
     * @param ftArPaymentd
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftArPaymentd: FtArPaymentd?)

    @Update
    fun update(ftArPaymentd: FtArPaymentd?)

    @Delete
    fun delete(ftArPaymentd: FtArPaymentd?)

    @Query("DELETE FROM ftArPaymentd")
    fun deleteAllFtArPaymentd()

    @get:Query("SELECT * FROM ftArPaymentd ")
    val allFtArPaymentdLive: LiveData<List<FtArPaymentd?>?>?

    @get:Query("SELECT * FROM ftArPaymentd ")
    val allFtArPaymentd: List<FtArPaymentd?>?

    @Query("SELECT * FROM ftArPaymentd WHERE id = :id ")
    fun getAllById(id: Long?): List<FtArPaymentd?>?

    @Query("SELECT * FROM ftArPaymentd WHERE ftArPaymenthBean = :id ")
    fun getAllByParentId(id: Long?): List<FtArPaymentd?>?
}