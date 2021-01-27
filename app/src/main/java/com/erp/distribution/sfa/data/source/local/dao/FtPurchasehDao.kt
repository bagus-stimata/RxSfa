package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPurchasehEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPurchasehDao {
    /**
     * @param ftPurchasehEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPurchasehEntity: FtPurchasehEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPurchasehEntity: List<FtPurchasehEntity>)


    @Update
    fun update(ftPurchasehEntity: FtPurchasehEntity?)

    @Delete
    fun delete(ftPurchasehEntity: FtPurchasehEntity?)

    @Query("DELETE FROM ftPurchaseh")
    fun deleteAllFtPurchaseh()

    @get:Query("SELECT * FROM ftPurchaseh ")
    val allFtPurchasehEntityLive: LiveData<List<FtPurchasehEntity?>?>?

    @get:Query("SELECT * FROM ftPurchaseh ")
    val allFtPurchasehEntity: List<FtPurchasehEntity?>?

    @Query("SELECT * FROM ftPurchaseh WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtPurchasehEntity?>?

    @Query("SELECT * FROM ftPurchaseh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtPurchasehEntity?>?
}