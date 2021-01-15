package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FParamDiskonItemVendor

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FParamDiskonItemVendorDao {
    /**
     * @param fParamDiskonItemVendor
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fParamDiskonItemVendor: FParamDiskonItemVendor?)

    @Update
    fun update(fParamDiskonItemVendor: FParamDiskonItemVendor?)

    @Delete
    fun delete(fParamDiskonItemVendor: FParamDiskonItemVendor?)

    @Query("DELETE FROM fParamDiskonItemVendor")
    fun deleteAllFParamDiskonItemVendor()

    @get:Query("SELECT * FROM fParamDiskonItemVendor ")
    val allFParamDiskonItemVendorLive: LiveData<List<FParamDiskonItemVendor?>?>?

    @get:Query("SELECT * FROM fParamDiskonItemVendor ")
    val allFParamDiskonItemVendor: List<FParamDiskonItemVendor?>?

    @Query("SELECT * FROM fParamDiskonItemVendor WHERE id = :id ")
    fun getAllById(id: Int): List<FParamDiskonItemVendor?>?

    @Query("SELECT * FROM fParamDiskonItemVendor WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FParamDiskonItemVendor?>?
}