package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FVendorEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FVendorDao {
    /**
     * @param fVendorEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fVendorEntity: FVendorEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFVendorEntity: List<FVendorEntity>)


    @Update
    fun update(fVendorEntity: FVendorEntity)

    @Delete
    fun delete(fVendorEntity: FVendorEntity)

    @Query("DELETE FROM fVendor")
    fun deleteAllFVendor()

    @Query("SELECT * FROM fVendor WHERE id = :id ")
    fun getAllById(id: Int): FVendorEntity
    @Query("SELECT * FROM fVendor WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FVendorEntity>


    @get:Query("SELECT * FROM fVendor ")
    val getAllFVendorEntity: List<FVendorEntity>
    @get:Query("SELECT * FROM fVendor ")
    val getAllFVendorEntityLive: LiveData<List<FVendorEntity>>

    @Query("SELECT * FROM fVendor WHERE vcode LIKE :vcode ")
    fun getAllFVendorByKode(vcode: String): List<FVendorEntity>
    @Query("SELECT * FROM fVendor WHERE vcode LIKE :vcode ")
    fun getAllFVendorByKodeLive(vcode: String): LiveData<List<FVendorEntity>>


    @Query("SELECT * FROM fVendor WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FVendorEntity>

    @Query("SELECT * FROM fVendor WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FVendorEntity>>

}