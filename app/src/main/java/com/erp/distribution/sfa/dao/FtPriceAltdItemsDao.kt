package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtPriceAltdItems

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtPriceAltdItemsDao {
    /**
     * @param ftPriceAltdItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftPriceAltdItems: FtPriceAltdItems?)

    @Update
    fun update(ftPriceAltdItems: FtPriceAltdItems?)

    @Delete
    fun delete(ftPriceAltdItems: FtPriceAltdItems?)

    @Query("DELETE FROM ftPriceAltdItems")
    fun deleteAllFtPriceAltdItems()

    @get:Query("SELECT * FROM ftPriceAltdItems ")
    val allFtPriceAltdItemsLive: LiveData<List<FtPriceAltdItems?>?>?

    @get:Query("SELECT * FROM ftPriceAltdItems ")
    val allFtPriceAltdItems: List<FtPriceAltdItems?>?

    @Query("SELECT * FROM ftPriceAltdItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtPriceAltdItems?>?

    @Query("SELECT * FROM ftPriceAltdItems WHERE ftPriceAlthBean = :id ")
    fun getAllByDivision(id: Long?): List<FtPriceAltdItems?>?
}