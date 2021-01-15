package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FGiro

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FGiroDao {
    /**
     * @param fGiro
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fGiro: FGiro?)

    @Update
    fun update(fGiro: FGiro?)

    @Delete
    fun delete(fGiro: FGiro?)

    @Query("DELETE FROM fGiro")
    fun deleteAllFGiro()

    @get:Query("SELECT * FROM fGiro ")
    val allFGiroLive: LiveData<List<FGiro?>?>?

    @get:Query("SELECT * FROM fGiro ")
    val allFGiro: List<FGiro?>?

    @Query("SELECT * FROM fGiro WHERE id = :id ")
    fun getAllById(id: Int): List<FGiro?>?

    @Query("SELECT * FROM fGiro WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FGiro?>?
}