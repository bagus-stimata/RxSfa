package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FParamDiskonItemVendorEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FParamDiskonItemVendorDao {
    /**
     * @param fParamDiskonItemVendorEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fParamDiskonItemVendorEntity: FParamDiskonItemVendorEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFParamDiskonItemVendorEntity: List<FParamDiskonItemVendorEntity>)
    
    @Update
    fun update(fParamDiskonItemVendorEntity: FParamDiskonItemVendorEntity?)

    @Delete
    fun delete(fParamDiskonItemVendorEntity: FParamDiskonItemVendorEntity?)

    @Query("DELETE FROM fParamDiskonItemVendor")
    fun deleteAllFParamDiskonItemVendor()

    @get:Query("SELECT * FROM fParamDiskonItemVendor ")
    val allFParamDiskonItemVendorEntityLive: LiveData<List<FParamDiskonItemVendorEntity?>?>?

    @get:Query("SELECT * FROM fParamDiskonItemVendor ")
    val allFParamDiskonItemVendorEntity: List<FParamDiskonItemVendorEntity?>?

    @Query("SELECT * FROM fParamDiskonItemVendor WHERE id = :id ")
    fun getAllById(id: Int): List<FParamDiskonItemVendorEntity?>?

    @Query("SELECT * FROM fParamDiskonItemVendor WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FParamDiskonItemVendorEntity?>?
}