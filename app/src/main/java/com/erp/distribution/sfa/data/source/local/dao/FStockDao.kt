package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FStockEntity
import java.util.*

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FStockDao {
    /**
     * @param fStockEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fStockEntity: FStockEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFStockEntity: List<FStockEntity>)


    @Update
    fun update(fStockEntity: FStockEntity)

    @Delete
    fun delete(fStockEntity: FStockEntity)

    @Query("DELETE FROM fStock")
    fun deleteAllFStock()

    @Query("SELECT * FROM fStock WHERE refno = :id ")
    fun getAllById(id: Int): FStockEntity
    @Query("SELECT * FROM fStock WHERE refno = :id ")
    fun getAllByIdLive(id: Int): LiveData<FStockEntity>


    @get:Query("SELECT * FROM fStock ")
    val getAllFStockEntity: List<FStockEntity>
    @get:Query("SELECT * FROM fStock ")
    val getAllFStockEntityLive: LiveData<List<FStockEntity>>

    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate ")
    fun getAllFStockByTrDate(stockDate: Date): List<FStockEntity>
    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate ")
    fun getAllFStockByTrDateLive(stockDate: Date): LiveData<List<FStockEntity>>


    @Query("SELECT * FROM fStock WHERE fmaterialBean = :fmaterialId ")
    fun getAllByMaterial(fmaterialId: Int): List<FStockEntity>

    @Query("SELECT * FROM fStock WHERE fmaterialBean = :fmaterialId ")
    fun getAllByMaterialLive(fmaterialId: Int): LiveData<List<FStockEntity>>

    @Query("SELECT * FROM fStock WHERE fwarehouseBean = :warehouseId ")
    fun getAllByWarehouse(warehouseId: Int): List<FStockEntity>

    @Query("SELECT * FROM fStock WHERE fwarehouseBean = :warehouseId ")
    fun getAllByWarehouseLive(warehouseId: Int): LiveData<List<FStockEntity>>


    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate AND fmaterialBean = :fmaterialId ")
    fun getAllByMaterialLive(fmaterialId: Int, stockDate: Date): LiveData<List<FStockEntity>>
    @Query("SELECT * FROM fStock WHERE stockDate = :stockDate AND fwarehouseBean = :warehouseId ")
    fun getAllByWarehouseLive(warehouseId: Int, stockDate: Date): LiveData<List<FStockEntity>>

}