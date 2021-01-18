package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FCustomerGroup

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCustomerGroupDao {
    /**
     * @param fCustomerGroup
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCustomerGroup: FCustomerGroup)

    @Update
    fun update(fCustomerGroup: FCustomerGroup)

    @Delete
    fun delete(fCustomerGroup: FCustomerGroup)

    @Query("DELETE FROM fCustomerGroup")
    fun deleteAllFCustomerGroup()

    @Query("SELECT * FROM fCustomerGroup WHERE id = :id ")
    fun getAllById(id: Int): FCustomerGroup
    @Query("SELECT * FROM fCustomerGroup WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCustomerGroup>


    @get:Query("SELECT * FROM fCustomerGroup ")
    val getAllFCustomerGroup: List<FCustomerGroup>
    @get:Query("SELECT * FROM fCustomerGroup ")
    val getAllFCustomerGroupLive: LiveData<List<FCustomerGroup>>

    @Query("SELECT * FROM fCustomerGroup WHERE kode1 LIKE :kode1 ")
    fun getAllFCustomerGroupByKode(kode1: String): List<FCustomerGroup>
    @Query("SELECT * FROM fCustomerGroup WHERE kode1 LIKE :kode1 ")
    fun getAllFCustomerGroupByKodeLive(kode1: String): LiveData<List<FCustomerGroup>>


    @Query("SELECT * FROM fCustomerGroup WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FCustomerGroup>

    @Query("SELECT * FROM fCustomerGroup WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FCustomerGroup>>
}