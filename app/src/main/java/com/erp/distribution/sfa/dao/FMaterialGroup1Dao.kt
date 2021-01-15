package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialGroup1

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup1Dao {
    /**
     * @param fMaterialGroup1
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fMaterialGroup1: FMaterialGroup1?)

    @Update
    fun update(fMaterialGroup1: FMaterialGroup1?)

    @Delete
    fun delete(fMaterialGroup1: FMaterialGroup1?)

    @Query("DELETE FROM fMaterialGroup1")
    fun deleteAllFMaterialGroup1()

    @get:Query("SELECT * FROM fMaterialGroup1 ")
    val allFMaterialGroup1Live: LiveData<List<FMaterialGroup1?>?>?

    @get:Query("SELECT * FROM fMaterialGroup1 ")
    val allFMaterialGroup1: List<FMaterialGroup1?>?

    @Query("SELECT * FROM fMaterialGroup1 WHERE id = :id ")
    fun getAllById(id: Int): List<FMaterialGroup1?>?

    @Query("SELECT * FROM fMaterialGroup1 WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterialGroup1?>?
}