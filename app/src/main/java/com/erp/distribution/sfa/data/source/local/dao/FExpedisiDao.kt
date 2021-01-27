package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FExpedisiEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FExpedisiDao {
    /**
     * @param fExpedisiEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fExpedisiEntity: FExpedisiEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFExpedisiEntity: List<FExpedisiEntity>)
    
    @Update
    fun update(fExpedisiEntity: FExpedisiEntity?)

    @Delete
    fun delete(fExpedisiEntity: FExpedisiEntity?)

    @Query("DELETE FROM fexpedisi")
    fun deleteAllFExpedisi()

    @get:Query("SELECT * FROM fexpedisi ")
    val allFExpedisiEntityLive: LiveData<List<FExpedisiEntity?>?>?

    @get:Query("SELECT * FROM fexpedisi ")
    val allFExpedisiEntity: List<FExpedisiEntity?>?

    @Query("SELECT * FROM fexpedisi WHERE id = :id ")
    fun getAllById(id: Int): List<FExpedisiEntity?>?

    @Query("SELECT * FROM fexpedisi WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FExpedisiEntity?>?
}