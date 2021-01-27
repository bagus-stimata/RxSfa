package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FSalesmanEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSalesmanDao {
    /**
     * @param fSalesman
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fSalesmanEnitty: FSalesmanEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFSalesmanEntity: List<FSalesmanEntity>)


    @Update
    fun update(fSalesmanEntity: FSalesmanEntity)

    @Delete
    fun delete(fSalesmanEntity: FSalesmanEntity)

    @Query("DELETE FROM fSalesman")
    fun deleteAllFSalesman()

    @Query("SELECT * FROM fSalesman WHERE id = :id ")
    fun getAllById(id: Int): FSalesmanEntity
    @Query("SELECT * FROM fSalesman WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FSalesmanEntity>


    @get:Query("SELECT * FROM fSalesman ")
    val getAllFSalesmanEnitty: List<FSalesmanEntity>
    @get:Query("SELECT * FROM fSalesman ")
    val getAllFSalesmanEnittyLive: LiveData<List<FSalesmanEntity>>

    @Query("SELECT * FROM fSalesman WHERE spcode LIKE :spcode ")
    fun getAllFSalesmanByKode(spcode: String): List<FSalesmanEntity>
    @Query("SELECT * FROM fSalesman WHERE spcode LIKE :spcode ")
    fun getAllFSalesmanByKodeLive(spcode: String): LiveData<List<FSalesmanEntity>>


    @Query("SELECT * FROM fSalesman WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FSalesmanEntity>

    @Query("SELECT * FROM fSalesman WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FSalesmanEntity>>
}