package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FGiroEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FGiroDao {
    /**
     * @param fGiroEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fGiroEntity: FGiroEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFGiroEntity: List<FGiroEntity>)
    
    @Update
    fun update(fGiroEntity: FGiroEntity?)

    @Delete
    fun delete(fGiroEntity: FGiroEntity?)

    @Query("DELETE FROM fGiro")
    fun deleteAllFGiro()

    @get:Query("SELECT * FROM fGiro ")
    val allFGiroEntityLive: LiveData<List<FGiroEntity?>?>?

    @get:Query("SELECT * FROM fGiro ")
    val allFGiroEntity: List<FGiroEntity?>?

    @Query("SELECT * FROM fGiro WHERE id = :id ")
    fun getAllById(id: Int): List<FGiroEntity?>?

    @Query("SELECT * FROM fGiro WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FGiroEntity?>?
}