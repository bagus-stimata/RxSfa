package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterial

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialDao {
    /**
     * @param fMaterial
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fMaterial: FMaterial?)

    @Update
    fun update(fMaterial: FMaterial?)

    @Delete
    fun delete(fMaterial: FMaterial?)

    @Query("DELETE FROM fMaterial")
    fun deleteAllFMaterial()

    @get:Query("SELECT * FROM fMaterial ")
    val allFMaterialLive: LiveData<List<FMaterial?>?>?

    @get:Query("SELECT * FROM fMaterial ")
    val allFMaterial: List<FMaterial?>?

    @Query("SELECT * FROM fMaterial WHERE id = :id ")
    fun getAllById(id: Int): List<FMaterial?>?

    @Query("SELECT * FROM fMaterial WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterial?>?
}