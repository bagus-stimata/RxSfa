package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtApPaymenth

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtApPaymenthDao {
    /**
     * @param ftApPaymenth
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftApPaymenth: FtApPaymenth?)

    @Update
    fun update(ftApPaymenth: FtApPaymenth?)

    @Delete
    fun delete(ftApPaymenth: FtApPaymenth?)

    @Query("DELETE FROM ftApPaymenth")
    fun deleteAllFtApPaymenth()

    @get:Query("SELECT * FROM ftApPaymenth ")
    val allFtApPaymenthLive: LiveData<List<FtApPaymenth?>?>?

    @get:Query("SELECT * FROM ftApPaymenth ")
    val allFtApPaymenth: List<FtApPaymenth?>?

    @Query("SELECT * FROM ftApPaymenth WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtApPaymenth?>?

    @Query("SELECT * FROM ftApPaymenth WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int?): List<FtApPaymenth?>?
}