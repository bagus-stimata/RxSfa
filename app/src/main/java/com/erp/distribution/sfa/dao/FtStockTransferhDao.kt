package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtStockTransferh

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtStockTransferhDao {
    /**
     * @param ftStockTransferh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftStockTransferh: FtStockTransferh?)

    @Update
    fun update(ftStockTransferh: FtStockTransferh?)

    @Delete
    fun delete(ftStockTransferh: FtStockTransferh?)

    @Query("DELETE FROM ftStockTransferh")
    fun deleteAllFtStockTransferh()

    @get:Query("SELECT * FROM ftStockTransferh ")
    val allFtStockTransferhLive: LiveData<List<FtStockTransferh?>?>?

    @get:Query("SELECT * FROM ftStockTransferh ")
    val allFtStockTransferh: List<FtStockTransferh?>?

    @Query("SELECT * FROM ftStockTransferh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtStockTransferh?>?

    @Query("SELECT * FROM ftStockTransferh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtStockTransferh?>?
}