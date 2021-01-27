package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FCustomerPicEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCustomerPicDao {
    /**
     * @param fCustomerPicEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCustomerPicEntity: FCustomerPicEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFCustomerPicEntity: List<FCustomerPicEntity>)

    @Update
    fun update(fCustomerPicEntity: FCustomerPicEntity)

    @Delete
    fun delete(fCustomerPicEntity: FCustomerPicEntity)

    @Query("DELETE FROM fCustomerPic")
    fun deleteAllFCustomerPic()

    @Query("SELECT * FROM fCustomerPic WHERE id = :id ")
    fun getAllById(id: Int): FCustomerPicEntity
    @Query("SELECT * FROM fCustomerPic WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCustomerPicEntity>


    @get:Query("SELECT * FROM fCustomerPic ")
    val getAllFCustomerPicEntity: List<FCustomerPicEntity>
    @get:Query("SELECT * FROM fCustomerPic ")
    val getAllFCustomerPicEntityLive: LiveData<List<FCustomerPicEntity>>

    @Query("SELECT * FROM fCustomerPic WHERE fcustomerBean = :parentId ")
    fun getAllByParent(parentId: Int): List<FCustomerPicEntity>

    @Query("SELECT * FROM fCustomerPic WHERE fcustomerBean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FCustomerPicEntity>>


}