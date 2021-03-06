package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPriceAlthDao {
    /**
     * @param ftPriceAlthEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPriceAlthEntity: FtPriceAlthEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPriceAlthEntity: List<FtPriceAlthEntity>)


    @Update
    fun update(ftPriceAlthEntity: FtPriceAlthEntity?)

    @Delete
    fun delete(ftPriceAlthEntity: FtPriceAlthEntity?)

    @Query("DELETE FROM ftPriceAlth")
    fun deleteAllFtPriceAlth()

    @get:Query("SELECT * FROM ftPriceAlth ")
    val getAllFtPriceAlthLive: LiveData<List<FtPriceAlthEntity>>

    @Query("SELECT * FROM ftPriceAlth WHERE fdivisionBean = :fdivisionBean ")
    fun getAllByDivisionLive(fdivisionBean: Int): LiveData<List<FtPriceAlthEntity>>
}