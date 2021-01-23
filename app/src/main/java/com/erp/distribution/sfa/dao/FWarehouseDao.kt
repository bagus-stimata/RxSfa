package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FWarehouse

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FWarehouseDao {
    /**
     * @param fWarehouse
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fWarehouse: FWarehouse)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFWarehouse: List<FWarehouse>)


    @Update
    fun update(fWarehouse: FWarehouse)

    @Delete
    fun delete(fWarehouse: FWarehouse)

    @Query("DELETE FROM fWarehouse")
    fun deleteAllFWarehouse()

    @Query("SELECT * FROM fWarehouse WHERE id = :id ")
    fun getAllById(id: Int): FWarehouse
    @Query("SELECT * FROM fWarehouse WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FWarehouse>


    @get:Query("SELECT * FROM fWarehouse ")
    val getAllFWarehouse: List<FWarehouse>
    @get:Query("SELECT * FROM fWarehouse ")
    val getAllFWarehouseLive: LiveData<List<FWarehouse>>

    @Query("SELECT * FROM fWarehouse WHERE kode1 LIKE :kode1 ")
    fun getAllFWarehouseByKode(kode1: String): List<FWarehouse>
    @Query("SELECT * FROM fWarehouse WHERE kode1 LIKE :kode1 ")
    fun getAllFWarehouseByKodeLive(kode1: String): LiveData<List<FWarehouse>>


    @Query("SELECT * FROM fWarehouse WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FWarehouse>

    @Query("SELECT * FROM fWarehouse WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FWarehouse>>

}