package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FCustomerPic

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCustomerPicDao {
    /**
     * @param fCustomerPic
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fCustomerPic: FCustomerPic?)

    @Update
    fun update(fCustomerPic: FCustomerPic?)

    @Delete
    fun delete(fCustomerPic: FCustomerPic?)

    @Query("DELETE FROM fCustomerPic")
    fun deleteAllFCustomerPic()

    @get:Query("SELECT * FROM fCustomerPic ")
    val allFCustomerPicLive: LiveData<List<FCustomerPic?>?>?

    @get:Query("SELECT * FROM fCustomerPic ")
    val allFCustomerPic: List<FCustomerPic?>?

    @Query("SELECT * FROM fCustomerPic WHERE id = :id ")
    fun getAllById(id: Int): List<FCustomerPic?>?

    @Query("SELECT * FROM fCustomerPic WHERE fcustomerBean = :id ")
    fun getAllByParentId(id: Int): List<FCustomerPic?>?
}