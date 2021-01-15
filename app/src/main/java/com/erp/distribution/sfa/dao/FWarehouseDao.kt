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
    @Insert
    fun insert(fWarehouse: FWarehouse?)

    @Update
    fun update(fWarehouse: FWarehouse?)

    @Delete
    fun delete(fWarehouse: FWarehouse?)

    @Query("DELETE FROM fWarehouse")
    fun deleteAllFWarehouse()

    @get:Query("SELECT * FROM fWarehouse ")
    val allFWarehouseLive: LiveData<List<FWarehouse?>?>?

    @get:Query("SELECT * FROM fWarehouse ")
    val allFWarehouse: List<FWarehouse?>?

    @Query("SELECT * FROM fWarehouse WHERE id = :id ")
    fun getAllById(id: Int): List<FWarehouse?>?

    @Query("SELECT * FROM fWarehouse WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FWarehouse?>?
}