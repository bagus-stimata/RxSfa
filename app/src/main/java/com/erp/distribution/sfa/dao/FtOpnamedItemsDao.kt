package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtOpnamedItems

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtOpnamedItemsDao {
    /**
     * @param ftOpnamedItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftOpnamedItems: FtOpnamedItems?)

    @Update
    fun update(ftOpnamedItems: FtOpnamedItems?)

    @Delete
    fun delete(ftOpnamedItems: FtOpnamedItems?)

    @Query("DELETE FROM ftOpnamedItems")
    fun deleteAllFtOpnamedItems()

    @get:Query("SELECT * FROM ftOpnamedItems ")
    val allFtOpnamedItemsLive: LiveData<List<FtOpnamedItems?>?>?

    @get:Query("SELECT * FROM ftOpnamedItems ")
    val allFtOpnamedItems: List<FtOpnamedItems?>?

    @Query("SELECT * FROM ftOpnamedItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtOpnamedItems?>?

    @Query("SELECT * FROM ftOpnamedItems WHERE ftOpnamehBean = :id ")
    fun getAllByParentId(id: Long?): List<FtOpnamedItems?>?
}