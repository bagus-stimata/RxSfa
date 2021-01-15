package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FExpedisi

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FExpedisiDao {
    /**
     * @param fExpedisi
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fExpedisi: FExpedisi?)

    @Update
    fun update(fExpedisi: FExpedisi?)

    @Delete
    fun delete(fExpedisi: FExpedisi?)

    @Query("DELETE FROM fexpedisi")
    fun deleteAllFExpedisi()

    @get:Query("SELECT * FROM fexpedisi ")
    val allFExpedisiLive: LiveData<List<FExpedisi?>?>?

    @get:Query("SELECT * FROM fexpedisi ")
    val allFExpedisi: List<FExpedisi?>?

    @Query("SELECT * FROM fexpedisi WHERE id = :id ")
    fun getAllById(id: Int): List<FExpedisi?>?

    @Query("SELECT * FROM fexpedisi WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FExpedisi?>?
}