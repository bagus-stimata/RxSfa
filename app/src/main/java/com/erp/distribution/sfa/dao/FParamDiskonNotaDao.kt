package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FParamDiskonNota

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FParamDiskonNotaDao {
    /**
     * @param fParamDiskonNota
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fParamDiskonNota: FParamDiskonNota?)

    @Update
    fun update(fParamDiskonNota: FParamDiskonNota?)

    @Delete
    fun delete(fParamDiskonNota: FParamDiskonNota?)

    @Query("DELETE FROM fParamDiskonNota")
    fun deleteAllFParamDiskonNota()

    @get:Query("SELECT * FROM fParamDiskonNota ")
    val allFParamDiskonNotaLive: LiveData<List<FParamDiskonNota?>?>?

    @get:Query("SELECT * FROM fParamDiskonNota ")
    val allFParamDiskonNota: List<FParamDiskonNota?>?

    @Query("SELECT * FROM fParamDiskonNota WHERE id = :id ")
    fun getAllById(id: Int): List<FParamDiskonNota?>?

    @Query("SELECT * FROM fParamDiskonNota WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FParamDiskonNota?>?
}