package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtStockTransferdItems

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtStockTransferdItemsDao {
    /**
     * @param ftStockTransferdItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftStockTransferdItems: FtStockTransferdItems?)

    @Update
    fun update(ftStockTransferdItems: FtStockTransferdItems?)

    @Delete
    fun delete(ftStockTransferdItems: FtStockTransferdItems?)

    @Query("DELETE FROM ftStockTransferdItems")
    fun deleteAllFtStockTransferdItems()

    @get:Query("SELECT * FROM ftStockTransferdItems ")
    val allFtStockTransferdItemsLive: LiveData<List<FtStockTransferdItems?>?>?

    @get:Query("SELECT * FROM ftStockTransferdItems ")
    val allFtStockTransferdItems: List<FtStockTransferdItems?>?

    @Query("SELECT * FROM ftStockTransferdItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtStockTransferdItems?>?

    @Query("SELECT * FROM ftStockTransferdItems WHERE ftStockTransferhBean = :id ")
    fun getAllByDivision(id: Long?): List<FtStockTransferdItems?>?
}