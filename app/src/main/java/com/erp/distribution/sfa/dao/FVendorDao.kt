package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FVendor

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FVendorDao {
    /**
     * @param fVendor
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fVendor: FVendor?)

    @Update
    fun update(fVendor: FVendor?)

    @Delete
    fun delete(fVendor: FVendor?)

    @Query("DELETE FROM fVendor")
    fun deleteAllFVendor()

    @get:Query("SELECT * FROM fVendor ")
    val allFVendorLive: LiveData<List<FVendor?>?>?

    @get:Query("SELECT * FROM fVendor ")
    val allFVendor: List<FVendor?>?

    @Query("SELECT * FROM fVendor WHERE id = :id ")
    fun getAllById(id: Int): List<FVendor?>?

    @Query("SELECT * FROM fVendor WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FVendor?>?
}