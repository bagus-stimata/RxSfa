package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtArPaymenth

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtArPaymenthDao {
    /**
     * @param ftArPaymenth
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftArPaymenth: FtArPaymenth?)

    @Update
    fun update(ftArPaymenth: FtArPaymenth?)

    @Delete
    fun delete(ftArPaymenth: FtArPaymenth?)

    @Query("DELETE FROM ftArPaymenth")
    fun deleteAllFtArPaymenth()

    @get:Query("SELECT * FROM ftArPaymenth ")
    val allFtArPaymenthLive: LiveData<List<FtArPaymenth?>?>?

    @get:Query("SELECT * FROM ftArPaymenth ")
    val allFtArPaymenth: List<FtArPaymenth?>?

    @Query("SELECT * FROM ftArPaymenth WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtArPaymenth?>?

    @Query("SELECT * FROM ftArPaymenth WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int?): List<FtArPaymenth?>?
}