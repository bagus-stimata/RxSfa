package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtPriceAlth

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPriceAlthDao {
    /**
     * @param ftPriceAlth
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPriceAlth: FtPriceAlth?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtPriceAlth: List<FtPriceAlth>)


    @Update
    fun update(ftPriceAlth: FtPriceAlth?)

    @Delete
    fun delete(ftPriceAlth: FtPriceAlth?)

    @Query("DELETE FROM ftPriceAlth")
    fun deleteAllFtPriceAlth()

    @get:Query("SELECT * FROM ftPriceAlth ")
    val allFtPriceAlthLive: LiveData<List<FtPriceAlth?>?>?

    @get:Query("SELECT * FROM ftPriceAlth ")
    val allFtPriceAlth: List<FtPriceAlth?>?

    @Query("SELECT * FROM ftPriceAlth WHERE id = :id ")
    fun getAllById(id: Long?): List<FtPriceAlth?>?

    @Query("SELECT * FROM ftPriceAlth WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int?): List<FtPriceAlth?>?
}