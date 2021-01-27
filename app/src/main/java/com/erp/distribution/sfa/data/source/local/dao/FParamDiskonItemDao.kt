package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FParamDiskonItemEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FParamDiskonItemDao {
    /**
     * @param fParamDiskonItemEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fParamDiskonItemEntity: FParamDiskonItemEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFParamDiskonItemEntity: List<FParamDiskonItemEntity>)
    
    @Update
    fun update(fParamDiskonItemEntity: FParamDiskonItemEntity?)

    @Delete
    fun delete(fParamDiskonItemEntity: FParamDiskonItemEntity?)

    @Query("DELETE FROM fParamDiskonItem")
    fun deleteAllFParamDiskonItem()

    @get:Query("SELECT * FROM fParamDiskonItem ")
    val allFParamDiskonItemEntityLive: LiveData<List<FParamDiskonItemEntity?>?>?

    @get:Query("SELECT * FROM fParamDiskonItem ")
    val allFParamDiskonItemEntity: List<FParamDiskonItemEntity?>?

    @Query("SELECT * FROM fParamDiskonItem WHERE id = :id ")
    fun getAllById(id: Int): List<FParamDiskonItemEntity?>?

    @Query("SELECT * FROM fParamDiskonItem WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FParamDiskonItemEntity?>?
}