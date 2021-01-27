package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPricedItemsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPricedItemsDao {
    /**
     * @param ftPricedItemsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPricedItemsEntity: FtPricedItemsEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPricedItemEntities: List<FtPricedItemsEntity>)


    @Update
    fun update(ftPricedItemsEntity: FtPricedItemsEntity?)

    @Delete
    fun delete(ftPricedItemsEntity: FtPricedItemsEntity?)

    @Query("DELETE FROM ftPricedItems")
    fun deleteAllFtPricedItems()

    @get:Query("SELECT * FROM ftPricedItems ")
    val allFtPricedItemsEntityLive: LiveData<List<FtPricedItemsEntity?>?>?

    @get:Query("SELECT * FROM ftPricedItems ")
    val allFtPricedItemEntities: List<FtPricedItemsEntity?>?

    @Query("SELECT * FROM ftPricedItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtPricedItemsEntity?>?

    @Query("SELECT * FROM ftPricedItems WHERE ftPricehBean = :id ")
    fun getAllByDivision(id: Long?): List<FtPricedItemsEntity?>?
}