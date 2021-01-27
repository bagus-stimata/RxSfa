package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FParamDiskonNotaEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FParamDiskonNotaDao {
    /**
     * @param fParamDiskonNotaEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fParamDiskonNotaEntity: FParamDiskonNotaEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFParamDiskonNotaEntity: List<FParamDiskonNotaEntity>)
    
    @Update
    fun update(fParamDiskonNotaEntity: FParamDiskonNotaEntity?)

    @Delete
    fun delete(fParamDiskonNotaEntity: FParamDiskonNotaEntity?)

    @Query("DELETE FROM fParamDiskonNota")
    fun deleteAllFParamDiskonNota()

    @get:Query("SELECT * FROM fParamDiskonNota ")
    val allFParamDiskonNotaEntityLive: LiveData<List<FParamDiskonNotaEntity?>?>?

    @get:Query("SELECT * FROM fParamDiskonNota ")
    val allFParamDiskonNotaEntity: List<FParamDiskonNotaEntity?>?

    @Query("SELECT * FROM fParamDiskonNota WHERE id = :id ")
    fun getAllById(id: Int): List<FParamDiskonNotaEntity?>?

    @Query("SELECT * FROM fParamDiskonNota WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FParamDiskonNotaEntity?>?
}