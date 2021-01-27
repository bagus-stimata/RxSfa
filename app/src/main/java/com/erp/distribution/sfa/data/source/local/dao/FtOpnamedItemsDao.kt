package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtOpnamedItemsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtOpnamedItemsDao {
    /**
     * @param ftOpnamedItemsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftOpnamedItemsEntity: FtOpnamedItemsEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtOpnamedItemEntities: List<FtOpnamedItemsEntity>)


    @Update
    fun update(ftOpnamedItemsEntity: FtOpnamedItemsEntity?)

    @Delete
    fun delete(ftOpnamedItemsEntity: FtOpnamedItemsEntity?)

    @Query("DELETE FROM ftOpnamedItems")
    fun deleteAllFtOpnamedItems()

    @get:Query("SELECT * FROM ftOpnamedItems ")
    val allFtOpnamedItemsEntityLive: LiveData<List<FtOpnamedItemsEntity?>?>?

    @get:Query("SELECT * FROM ftOpnamedItems ")
    val allFtOpnamedItemEntities: List<FtOpnamedItemsEntity?>?

    @Query("SELECT * FROM ftOpnamedItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtOpnamedItemsEntity?>?

    @Query("SELECT * FROM ftOpnamedItems WHERE ftOpnamehBean = :id ")
    fun getAllByParentId(id: Long?): List<FtOpnamedItemsEntity?>?
}