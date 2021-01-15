package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialSalesBrand

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialSalesBrandDao {
    /**
     * @param fMaterialSalesBrand
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fMaterialSalesBrand: FMaterialSalesBrand?)

    @Update
    fun update(fMaterialSalesBrand: FMaterialSalesBrand?)

    @Delete
    fun delete(fMaterialSalesBrand: FMaterialSalesBrand?)

    @Query("DELETE FROM fMaterialSalesBrand")
    fun deleteAllFMaterialSalesBrand()

    @get:Query("SELECT * FROM fMaterialSalesBrand ")
    val allFMaterialSalesBrandLive: LiveData<List<FMaterialSalesBrand?>?>?

    @get:Query("SELECT * FROM fMaterialSalesBrand ")
    val allFMaterialSalesBrand: List<FMaterialSalesBrand?>?

    @Query("SELECT * FROM fMaterialSalesBrand WHERE id = :id ")
    fun getAllById(id: Int): List<FMaterialSalesBrand?>?

    @Query("SELECT * FROM fMaterialSalesBrand WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterialSalesBrand?>?
}