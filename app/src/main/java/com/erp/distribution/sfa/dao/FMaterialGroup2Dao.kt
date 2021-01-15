package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialGroup2

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup2Dao {
    /**
     * @param fMaterialGroup2
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fMaterialGroup2: FMaterialGroup2?)

    @Update
    fun update(fMaterialGroup2: FMaterialGroup2?)

    @Delete
    fun delete(fMaterialGroup2: FMaterialGroup2?)

    @Query("DELETE FROM fMaterialGroup2")
    fun deleteAllFMaterialGroup2()

    @get:Query("SELECT * FROM fMaterialGroup2 ")
    val allFMaterialGroup2Live: LiveData<List<FMaterialGroup2?>?>?

    @get:Query("SELECT * FROM fMaterialGroup2 ")
    val allFMaterialGroup2: List<FMaterialGroup2?>?

    @Query("SELECT * FROM fMaterialGroup2 WHERE id = :id ")
    fun getAllById(id: Int): List<FMaterialGroup2?>?

    @Query("SELECT * FROM fMaterialGroup2 WHERE fmaterialGroup1Bean = :id ")
    fun getAllByParentId(id: Int): List<FMaterialGroup2?>?
}