package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FWarehouseEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FWarehouseDao {
    /**
     * @param fWarehouseEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fWarehouseEntity: FWarehouseEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFWarehouseEntity: List<FWarehouseEntity>)


    @Update
    fun update(fWarehouseEntity: FWarehouseEntity)

    @Delete
    fun delete(fWarehouseEntity: FWarehouseEntity)

    @Query("DELETE FROM fWarehouse")
    fun deleteAllFWarehouse()

    @Query("SELECT * FROM fWarehouse WHERE id = :id ")
    fun getAllById(id: Int): FWarehouseEntity
    @Query("SELECT * FROM fWarehouse WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FWarehouseEntity>


    @get:Query("SELECT * FROM fWarehouse ")
    val getAllFWarehouseEntity: List<FWarehouseEntity>
    @get:Query("SELECT * FROM fWarehouse ")
    val getAllFWarehouseEntityLive: LiveData<List<FWarehouseEntity>>

    @Query("SELECT * FROM fWarehouse WHERE kode1 LIKE :kode1 ")
    fun getAllFWarehouseByKode(kode1: String): List<FWarehouseEntity>
    @Query("SELECT * FROM fWarehouse WHERE kode1 LIKE :kode1 ")
    fun getAllFWarehouseByKodeLive(kode1: String): LiveData<List<FWarehouseEntity>>


    @Query("SELECT * FROM fWarehouse WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FWarehouseEntity>

    @Query("SELECT * FROM fWarehouse WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FWarehouseEntity>>

}