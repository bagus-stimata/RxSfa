package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtPurchaseh

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPurchasehDao {
    /**
     * @param ftPurchaseh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPurchaseh: FtPurchaseh?)

    @Update
    fun update(ftPurchaseh: FtPurchaseh?)

    @Delete
    fun delete(ftPurchaseh: FtPurchaseh?)

    @Query("DELETE FROM ftPurchaseh")
    fun deleteAllFtPurchaseh()

    @get:Query("SELECT * FROM ftPurchaseh ")
    val allFtPurchasehLive: LiveData<List<FtPurchaseh?>?>?

    @get:Query("SELECT * FROM ftPurchaseh ")
    val allFtPurchaseh: List<FtPurchaseh?>?

    @Query("SELECT * FROM ftPurchaseh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtPurchaseh?>?

    @Query("SELECT * FROM ftPurchaseh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtPurchaseh?>?
}