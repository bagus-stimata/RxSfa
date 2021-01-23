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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCustomerPic: FCustomerPic)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFCustomerPic: List<FCustomerPic>)

    @Update
    fun update(fCustomerPic: FCustomerPic)

    @Delete
    fun delete(fCustomerPic: FCustomerPic)

    @Query("DELETE FROM fCustomerPic")
    fun deleteAllFCustomerPic()

    @Query("SELECT * FROM fCustomerPic WHERE id = :id ")
    fun getAllById(id: Int): FCustomerPic
    @Query("SELECT * FROM fCustomerPic WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCustomerPic>


    @get:Query("SELECT * FROM fCustomerPic ")
    val getAllFCustomerPic: List<FCustomerPic>
    @get:Query("SELECT * FROM fCustomerPic ")
    val getAllFCustomerPicLive: LiveData<List<FCustomerPic>>

    @Query("SELECT * FROM fCustomerPic WHERE fcustomerBean = :parentId ")
    fun getAllByParent(parentId: Int): List<FCustomerPic>

    @Query("SELECT * FROM fCustomerPic WHERE fcustomerBean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FCustomerPic>>


}