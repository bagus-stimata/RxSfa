package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FParamDiskonItem

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FParamDiskonItemDao {
    /**
     * @param fParamDiskonItem
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fParamDiskonItem: FParamDiskonItem?)

    @Update
    fun update(fParamDiskonItem: FParamDiskonItem?)

    @Delete
    fun delete(fParamDiskonItem: FParamDiskonItem?)

    @Query("DELETE FROM fParamDiskonItem")
    fun deleteAllFParamDiskonItem()

    @get:Query("SELECT * FROM fParamDiskonItem ")
    val allFParamDiskonItemLive: LiveData<List<FParamDiskonItem?>?>?

    @get:Query("SELECT * FROM fParamDiskonItem ")
    val allFParamDiskonItem: List<FParamDiskonItem?>?

    @Query("SELECT * FROM fParamDiskonItem WHERE id = :id ")
    fun getAllById(id: Int): List<FParamDiskonItem?>?

    @Query("SELECT * FROM fParamDiskonItem WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FParamDiskonItem?>?
}