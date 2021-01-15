package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FCustomer

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCustomerDao {
    /**
     * @param fCustomer
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fCustomer: FCustomer?)

    @Update
    fun update(fCustomer: FCustomer?)

    @Delete
    fun delete(fCustomer: FCustomer?)

    @Query("DELETE FROM fCustomer")
    fun deleteAllFCustomer()

    @get:Query("SELECT * FROM fCustomer ")
    val allFCustomerLive: LiveData<List<FCustomer?>?>?

    @get:Query("SELECT * FROM fCustomer ")
    val allFCustomer: List<FCustomer?>?

    @Query("SELECT * FROM fCustomer WHERE id = :id ")
    fun getAllById(id: Int): List<FCustomer?>?

    @Query("SELECT * FROM fCustomer WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FCustomer?>?
}