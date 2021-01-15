package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FCustomerSalesman

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCustomerSalesmanDao {
    /**
     * @param fCustomerSalesman
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fCustomerSalesman: FCustomerSalesman?)

    @Update
    fun update(fCustomerSalesman: FCustomerSalesman?)

    @Delete
    fun delete(fCustomerSalesman: FCustomerSalesman?)

    @Query("DELETE FROM fCustomerSalesman")
    fun deleteAllFCustomerSalesman()

    @get:Query("SELECT * FROM fCustomerSalesman ")
    val allFCustomerSalesmanLive: LiveData<List<FCustomerSalesman?>?>?

    @get:Query("SELECT * FROM fCustomerSalesman ")
    val allFCustomerSalesman: List<FCustomerSalesman?>?

    @Query("SELECT * FROM fCustomerSalesman WHERE id = :id ")
    fun getAllById(id: Int): List<FCustomerSalesman?>?

    @Query("SELECT * FROM fCustomerSalesman WHERE fcustomerBean = :id ")
    fun getAllByDivision(id: Int): List<FCustomerSalesman?>?
}