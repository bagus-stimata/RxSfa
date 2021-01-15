package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FSubArea

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSubAreaDao {
    /**
     * @param fSubArea
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fSubArea: FSubArea?)

    @Update
    fun update(fSubArea: FSubArea?)

    @Delete
    fun delete(fSubArea: FSubArea?)

    @Query("DELETE FROM fSubArea")
    fun deleteAllFSubArea()

    @get:Query("SELECT * FROM fSubArea ")
    val allFSubAreaLive: LiveData<List<FSubArea?>?>?

    @get:Query("SELECT * FROM fSubArea ")
    val allFSubArea: List<FSubArea?>?

    @Query("SELECT * FROM fSubArea WHERE id = :id ")
    fun getAllById(id: Int): List<FSubArea?>?

    @Query("SELECT * FROM fSubArea WHERE fareaBean = :id ")
    fun getAllByParentId(id: Int): List<FSubArea?>?
}