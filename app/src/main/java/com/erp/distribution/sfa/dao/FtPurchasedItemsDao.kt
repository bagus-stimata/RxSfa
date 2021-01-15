package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtPurchasedItems

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPurchasedItemsDao {
    /**
     * @param ftPurchasedItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPurchasedItems: FtPurchasedItems?)

    @Update
    fun update(ftPurchasedItems: FtPurchasedItems?)

    @Delete
    fun delete(ftPurchasedItems: FtPurchasedItems?)

    @Query("DELETE FROM ftPurchasedItems")
    fun deleteAllFtPurchasedItems()

    @get:Query("SELECT * FROM ftPurchasedItems ")
    val allFtPurchasedItemsLive: LiveData<List<FtPurchasedItems?>?>?

    @get:Query("SELECT * FROM ftPurchasedItems ")
    val allFtPurchasedItems: List<FtPurchasedItems?>?

    @Query("SELECT * FROM ftPurchasedItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtPurchasedItems?>?

    @Query("SELECT * FROM ftPurchasedItems WHERE ftPurchasehBean = :id ")
    fun getAllByParentId(id: Long?): List<FtPurchasedItems?>?
}