package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPurchasedItemsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPurchasedItemsDao {
    /**
     * @param ftPurchasedItemsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPurchasedItemsEntity: FtPurchasedItemsEntity?)
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAll(listFtPurchasedItemEntities: List<FtPurchasedItemsEntity>)


    @Update
    fun update(ftPurchasedItemsEntity: FtPurchasedItemsEntity?)

    @Delete
    fun delete(ftPurchasedItemsEntity: FtPurchasedItemsEntity?)

    @Query("DELETE FROM ftPurchasedItems")
    fun deleteAllFtPurchasedItems()

    @get:Query("SELECT * FROM ftPurchasedItems ")
    val allFtPurchasedItemsEntityLive: LiveData<List<FtPurchasedItemsEntity?>?>?

    @get:Query("SELECT * FROM ftPurchasedItems ")
    val allFtPurchasedItemEntities: List<FtPurchasedItemsEntity?>?

    @Query("SELECT * FROM ftPurchasedItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtPurchasedItemsEntity?>?

    @Query("SELECT * FROM ftPurchasedItems WHERE ftPurchasehBean = :id ")
    fun getAllByParentId(id: Long?): List<FtPurchasedItemsEntity?>?
}