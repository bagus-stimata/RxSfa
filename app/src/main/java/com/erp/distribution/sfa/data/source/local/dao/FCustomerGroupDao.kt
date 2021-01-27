package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FCustomerGroupEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCustomerGroupDao {
    /**
     * @param fCustomerGroupEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCustomerGroupEntity: FCustomerGroupEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFCustomerGroupEntity: List<FCustomerGroupEntity>)


    @Update
    fun update(fCustomerGroupEntity: FCustomerGroupEntity)

    @Delete
    fun delete(fCustomerGroupEntity: FCustomerGroupEntity)

    @Query("DELETE FROM fCustomerGroup")
    fun deleteAllFCustomerGroup()

    @Query("SELECT * FROM fCustomerGroup WHERE id = :id ")
    fun getAllById(id: Int): FCustomerGroupEntity
    @Query("SELECT * FROM fCustomerGroup WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCustomerGroupEntity>


    @get:Query("SELECT * FROM fCustomerGroup ")
    val getAllFCustomerGroupEntity: List<FCustomerGroupEntity>
    @get:Query("SELECT * FROM fCustomerGroup ")
    val getAllFCustomerGroupEntityLive: LiveData<List<FCustomerGroupEntity>>

    @Query("SELECT * FROM fCustomerGroup WHERE kode1 LIKE :kode1 ")
    fun getAllFCustomerGroupByKode(kode1: String): List<FCustomerGroupEntity>
    @Query("SELECT * FROM fCustomerGroup WHERE kode1 LIKE :kode1 ")
    fun getAllFCustomerGroupByKodeLive(kode1: String): LiveData<List<FCustomerGroupEntity>>


    @Query("SELECT * FROM fCustomerGroup WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FCustomerGroupEntity>

    @Query("SELECT * FROM fCustomerGroup WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FCustomerGroupEntity>>
}