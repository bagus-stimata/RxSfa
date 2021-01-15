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
    @Insert
    fun insert(fCustomerGroup: FCustomerGroup?)

    @Update
    fun update(fCustomerGroup: FCustomerGroup?)

    @Delete
    fun delete(fCustomerGroup: FCustomerGroup?)

    @Query("DELETE FROM fCustomerGroup")
    fun deleteAllFCustomerGroup()

    @get:Query("SELECT * FROM fCustomerGroup ")
    val allFCustomerGroupLive: LiveData<List<FCustomerGroup?>?>?

    @get:Query("SELECT * FROM fCustomerGroup ")
    val allFCustomerGroup: List<FCustomerGroup?>?

    @Query("SELECT * FROM fCustomerGroup WHERE id = :id ")
    fun getAllById(id: Int): List<FCustomerGroup?>?

    @Query("SELECT * FROM fCustomerGroup WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FCustomerGroup?>?
}