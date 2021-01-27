package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FUangMukaEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FUangMukaDao {
    /**
     * @param fUangMukaEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fUangMukaEntity: FUangMukaEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFUangMukaEntity: List<FUangMukaEntity>)


    @Update
    fun update(fUangMukaEntity: FUangMukaEntity?)

    @Delete
    fun delete(fUangMukaEntity: FUangMukaEntity?)

    @Query("DELETE FROM fUangMuka")
    fun deleteAllFUangMuka()

    @get:Query("SELECT * FROM fUangMuka ")
    val allFUangMukaEntityLive: LiveData<List<FUangMukaEntity?>?>?

    @get:Query("SELECT * FROM fUangMuka ")
    val allFUangMukaEntity: List<FUangMukaEntity?>?

    @Query("SELECT * FROM fUangMuka WHERE id = :id ")
    fun getAllById(id: Int): List<FUangMukaEntity?>?

    @Query("SELECT * FROM fUangMuka WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FUangMukaEntity?>?
}