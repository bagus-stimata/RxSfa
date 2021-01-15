package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtPricedItems

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPricedItemsDao {
    /**
     * @param ftPricedItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPricedItems: FtPricedItems?)

    @Update
    fun update(ftPricedItems: FtPricedItems?)

    @Delete
    fun delete(ftPricedItems: FtPricedItems?)

    @Query("DELETE FROM ftPricedItems")
    fun deleteAllFtPricedItems()

    @get:Query("SELECT * FROM ftPricedItems ")
    val allFtPricedItemsLive: LiveData<List<FtPricedItems?>?>?

    @get:Query("SELECT * FROM ftPricedItems ")
    val allFtPricedItems: List<FtPricedItems?>?

    @Query("SELECT * FROM ftPricedItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtPricedItems?>?

    @Query("SELECT * FROM ftPricedItems WHERE ftPricehBean = :id ")
    fun getAllByDivision(id: Long?): List<FtPricedItems?>?
}