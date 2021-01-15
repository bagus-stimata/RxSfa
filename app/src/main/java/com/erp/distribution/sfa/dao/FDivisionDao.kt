package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FDivision

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FDivisionDao {
    /**
     * @param fDivision
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fDivision: FDivision?)

    @Update
    fun update(fDivision: FDivision?)

    @Delete
    fun delete(fDivision: FDivision?)

    @Query("DELETE FROM fDivision")
    fun deleteAllFDivision()

    @get:Query("SELECT * FROM fDivision ")
    val allFDivisionLive: LiveData<List<FDivision?>?>?

    @get:Query("SELECT * FROM fDivision ")
    val allFDivision: List<FDivision?>?

    @Query("SELECT * FROM fDivision WHERE id = :id ")
    fun getAllById(id: Int): List<FDivision?>?

    @Query("SELECT * FROM fDivision WHERE fcompanyBean = :id ")
    fun getAllByParentId(id: Int): List<FDivision?>?
}