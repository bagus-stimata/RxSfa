package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialGroup3

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup3Dao {
    /**
     * @param fMaterialGroup3
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fMaterialGroup3: FMaterialGroup3?)

    @Update
    fun update(fMaterialGroup3: FMaterialGroup3?)

    @Delete
    fun delete(fMaterialGroup3: FMaterialGroup3?)

    @Query("DELETE FROM fMaterialGroup3")
    fun deleteAllFMaterialGroup3()

    @get:Query("SELECT * FROM fMaterialGroup3 ")
    val allFMaterialGroup3Live: LiveData<List<FMaterialGroup3?>?>?

    @get:Query("SELECT * FROM fMaterialGroup3 ")
    val allFMaterialGroup3: List<FMaterialGroup3?>?

    @Query("SELECT * FROM fMaterialGroup3 WHERE id = :id ")
    fun getAllById(id: Int): List<FMaterialGroup3?>?

    @Query("SELECT * FROM fMaterialGroup3 WHERE fmaterialGroup2Bean = :id ")
    fun getAllByParentId(id: Int): List<FMaterialGroup3?>?
}