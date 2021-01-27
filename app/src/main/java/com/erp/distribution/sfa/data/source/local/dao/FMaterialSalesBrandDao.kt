package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FMaterialSalesBrandEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialSalesBrandDao {
    /**
     * @param fMaterialSalesBrandEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialSalesBrandEntity: List<FMaterialSalesBrandEntity>)
    
    @Update
    fun update(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity)

    @Delete
    fun delete(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity)

    @Query("DELETE FROM fMaterialSalesBrand")
    fun deleteAllFMaterialSalesBrand()

    @Query("SELECT * FROM fMaterialSalesBrand WHERE id = :id ")
    fun getAllById(id: Int): FMaterialSalesBrandEntity
    @Query("SELECT * FROM fMaterialSalesBrand WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialSalesBrandEntity>


    @get:Query("SELECT * FROM fMaterialSalesBrand ")
    val getAllFMaterialSalesBrandEntity: List<FMaterialSalesBrandEntity>
    @get:Query("SELECT * FROM fMaterialSalesBrand ")
    val getAllFMaterialSalesBrandEntityLive: LiveData<List<FMaterialSalesBrandEntity>>

    @Query("SELECT * FROM fMaterialSalesBrand WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialSalesBrandByKode(kode1: String): List<FMaterialSalesBrandEntity>
    @Query("SELECT * FROM fMaterialSalesBrand WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialSalesBrandByKodeLive(kode1: String): LiveData<List<FMaterialSalesBrandEntity>>


    @Query("SELECT * FROM fMaterialSalesBrand WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterialSalesBrandEntity>

    @Query("SELECT * FROM fMaterialSalesBrand WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FMaterialSalesBrandEntity>>


}