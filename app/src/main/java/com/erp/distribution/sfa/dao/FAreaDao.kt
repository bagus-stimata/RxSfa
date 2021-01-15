package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FArea

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FAreaDao {
    /**
     * @param fArea
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fArea: FArea?)

    @Update
    fun update(fArea: FArea?)

    @Delete
    fun delete(fArea: FArea?)

    @Query("DELETE FROM fArea")
    fun deleteAllFArea()

    @get:Query("SELECT * FROM fArea ")
    val allFAreaLive: LiveData<List<FArea?>?>?

    @get:Query("SELECT * FROM fArea ")
    val allFArea: List<FArea?>?

    @Query("SELECT * FROM fArea WHERE id = :id ")
    fun getAllById(id: Int): List<FArea?>?

    @Query("SELECT * FROM fArea WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FArea?>?
}