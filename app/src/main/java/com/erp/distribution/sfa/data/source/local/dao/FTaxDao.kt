package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FTaxEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FTaxDao {
    /**
     * @param fTaxEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fTaxEntity: FTaxEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFTaxEntities: List<FTaxEntity>)


    @Update
    fun update(fTaxEntity: FTaxEntity)

    @Delete
    fun delete(fTaxEntity: FTaxEntity)

    @Query("DELETE FROM fTax")
    fun deleteAllFTax()

    @Query("SELECT * FROM fTax WHERE id = :id ")
    fun getAllById(id: Int): FTaxEntity
    @Query("SELECT * FROM fTax WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FTaxEntity>


    @get:Query("SELECT * FROM fTax ")
    val getAllFTaxEntities: List<FTaxEntity>
    @get:Query("SELECT * FROM fTax ")
    val getAllFTaxEntityLive: LiveData<List<FTaxEntity>>

    @Query("SELECT * FROM fTax WHERE kode1 LIKE :kode1 ")
    fun getAllFTaxByKode(kode1: String): List<FTaxEntity>
    @Query("SELECT * FROM fTax WHERE kode1 LIKE :kode1 ")
    fun getAllFTaxByKodeLive(kode1: String): LiveData<List<FTaxEntity>>


    @Query("SELECT * FROM fTax WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FTaxEntity>

    @Query("SELECT * FROM fTax WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FTaxEntity>>


}