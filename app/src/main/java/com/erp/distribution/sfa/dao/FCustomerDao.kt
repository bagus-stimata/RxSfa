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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCustomer: FCustomer)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFCustomer: List<FCustomer>)

    @Update
    fun update(fCustomer: FCustomer)

    @Delete
    fun delete(fCustomer: FCustomer)

    @Query("DELETE FROM fCustomer")
    fun deleteAllFCustomer()

    @Query("SELECT * FROM fCustomer WHERE id = :id ")
    fun getAllById(id: Int): FCustomer
    @Query("SELECT * FROM fCustomer WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCustomer>


    @get:Query("SELECT * FROM fCustomer ")
    val getAllFCustomer: List<FCustomer>
    @get:Query("SELECT * FROM fCustomer ")
    val getAllFCustomerLive: LiveData<List<FCustomer>>

    @Query("SELECT * FROM fCustomer WHERE custname LIKE :custname ")
    fun getAllFCustomerByKode(custname: String): List<FCustomer>
    @Query("SELECT * FROM fCustomer WHERE custname LIKE :custname ")
    fun getAllFCustomerByKodeLive(custname: String): LiveData<List<FCustomer>>


    @Query("SELECT * FROM fCustomer WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FCustomer>

    @Query("SELECT * FROM fCustomer WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FCustomer>>

}