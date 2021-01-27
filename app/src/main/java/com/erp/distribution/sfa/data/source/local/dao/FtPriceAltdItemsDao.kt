package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPriceAltdItemsDao {
    /**
     * @param ftPriceAltdItemsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPriceAltdItemEntities: List<FtPriceAltdItemsEntity>)


    @Update
    fun update(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity?)

    @Delete
    fun delete(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity?)

    @Query("DELETE FROM ftPriceAltdItems")
    fun deleteAllFtPriceAltdItems()

    @get:Query("SELECT * FROM ftPriceAltdItems ")
    val allFtPriceAltdItemsEntityLive: LiveData<List<FtPriceAltdItemsEntity?>?>?

    @get:Query("SELECT * FROM ftPriceAltdItems ")
    val allFtPriceAltdItemEntities: List<FtPriceAltdItemsEntity?>?

    @Query("SELECT * FROM ftPriceAltdItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtPriceAltdItemsEntity?>?

    @Query("SELECT * FROM ftPriceAltdItems WHERE ftPriceAlthBean = :id ")
    fun getAllByDivision(id: Long?): List<FtPriceAltdItemsEntity?>?
}