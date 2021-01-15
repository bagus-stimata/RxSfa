package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FTax

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FTaxDao {
    /**
     * @param fTax
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fTax: FTax?)

    @Update
    fun update(fTax: FTax?)

    @Delete
    fun delete(fTax: FTax?)

    @Query("DELETE FROM fTax")
    fun deleteAllFTax()

    @get:Query("SELECT * FROM fTax ")
    val allFTaxLive: LiveData<List<FTax?>?>?

    @get:Query("SELECT * FROM fTax ")
    val allFTax: List<FTax?>?

    @Query("SELECT * FROM fTax WHERE id = :id ")
    fun getAllById(id: Int): List<FTax?>?

    @Query("SELECT * FROM fTax WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FTax?>?
}