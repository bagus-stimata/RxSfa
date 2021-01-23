package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialSalesBrand

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialSalesBrandDao {
    /**
     * @param fMaterialSalesBrand
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialSalesBrand: FMaterialSalesBrand)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialSalesBrand: List<FMaterialSalesBrand>)
    
    @Update
    fun update(fMaterialSalesBrand: FMaterialSalesBrand)

    @Delete
    fun delete(fMaterialSalesBrand: FMaterialSalesBrand)

    @Query("DELETE FROM fMaterialSalesBrand")
    fun deleteAllFMaterialSalesBrand()

    @Query("SELECT * FROM fMaterialSalesBrand WHERE id = :id ")
    fun getAllById(id: Int): FMaterialSalesBrand
    @Query("SELECT * FROM fMaterialSalesBrand WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialSalesBrand>


    @get:Query("SELECT * FROM fMaterialSalesBrand ")
    val getAllFMaterialSalesBrand: List<FMaterialSalesBrand>
    @get:Query("SELECT * FROM fMaterialSalesBrand ")
    val getAllFMaterialSalesBrandLive: LiveData<List<FMaterialSalesBrand>>

    @Query("SELECT * FROM fMaterialSalesBrand WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialSalesBrandByKode(kode1: String): List<FMaterialSalesBrand>
    @Query("SELECT * FROM fMaterialSalesBrand WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialSalesBrandByKodeLive(kode1: String): LiveData<List<FMaterialSalesBrand>>


    @Query("SELECT * FROM fMaterialSalesBrand WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterialSalesBrand>

    @Query("SELECT * FROM fMaterialSalesBrand WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FMaterialSalesBrand>>


}