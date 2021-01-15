package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FStock

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FStockDao {
    /**
     * @param fStock
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fStock: FStock?)

    @Update
    fun update(fStock: FStock?)

    @Delete
    fun delete(fStock: FStock?)

    @Query("DELETE FROM fStock")
    fun deleteAllFStock()

    @get:Query("SELECT * FROM fStock ")
    val allFStockLive: LiveData<List<FStock?>?>?

    @get:Query("SELECT * FROM fStock ")
    val allFStock: List<FStock?>?

    @Query("SELECT * FROM fStock WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FStock?>?

    @Query("SELECT * FROM fStock WHERE fwarehouseBean = :fwarehouseBean and fmaterialBean = :fmaterialBean ")
    fun getAllByParentId(fwarehouseBean: Int?, fmaterialBean: Int?): List<FStock?>?
}