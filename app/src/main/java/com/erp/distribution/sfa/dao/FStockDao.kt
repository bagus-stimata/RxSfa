package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FStock
import java.util.*

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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fStock: FStock)

    @Update
    fun update(fStock: FStock)

    @Delete
    fun delete(fStock: FStock)

    @Query("DELETE FROM FStock")
    fun deleteAllFStock()

    @Query("SELECT * FROM fStock WHERE refno = :id ")
    fun getAllById(id: Int): FStock
    @Query("SELECT * FROM fStock WHERE refno = :id ")
    fun getAllByIdLive(id: Int): LiveData<FStock>


    @get:Query("SELECT * FROM fStock ")
    val getAllFStock: List<FStock>
    @get:Query("SELECT * FROM fStock ")
    val getAllFStockLive: LiveData<List<FStock>>

    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate ")
    fun getAllFStockByTrDate(stockDate: Date): List<FStock>
    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate ")
    fun getAllFStockByTrDateLive(stockDate: Date): LiveData<List<FStock>>


    @Query("SELECT * FROM fStock WHERE fmaterialBean = :fmaterialId ")
    fun getAllByMaterial(fmaterialId: Int): List<FStock>

    @Query("SELECT * FROM fStock WHERE fmaterialBean = :fmaterialId ")
    fun getAllByMaterialLive(fmaterialId: Int): LiveData<List<FStock>>

    @Query("SELECT * FROM fStock WHERE fwarehouseBean = :warehouseId ")
    fun getAllByWarehouse(warehouseId: Int): List<FStock>

    @Query("SELECT * FROM fStock WHERE fwarehouseBean = :warehouseId ")
    fun getAllByWarehouseLive(warehouseId: Int): LiveData<List<FStock>>


    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate AND fmaterialBean = :fmaterialId ")
    fun getAllFStockByTrDateAndMaterial(stockDate: Date, fmaterialId: Int): List<FStock>
    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate AND fmaterialBean = :fmaterialId ")
    fun getAllFStockByTrDateAndMaterialLive(stockDate: Date, fmaterialId: Int): LiveData<List<FStock>>

}