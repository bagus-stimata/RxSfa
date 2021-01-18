package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FTax

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FTaxDao {
    /**
     * @param fTax
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fTax: FTax)

    @Update
    fun update(fTax: FTax)

    @Delete
    fun delete(fTax: FTax)

    @Query("DELETE FROM fTax")
    fun deleteAllFTax()

    @Query("SELECT * FROM fTax WHERE id = :id ")
    fun getAllById(id: Int): FTax
    @Query("SELECT * FROM fTax WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FTax>


    @get:Query("SELECT * FROM fTax ")
    val getAllFTax: List<FTax>
    @get:Query("SELECT * FROM fTax ")
    val getAllFTaxLive: LiveData<List<FTax>>

    @Query("SELECT * FROM fTax WHERE kode1 LIKE :kode1 ")
    fun getAllFTaxByKode(kode1: String): List<FTax>
    @Query("SELECT * FROM fTax WHERE kode1 LIKE :kode1 ")
    fun getAllFTaxByKodeLive(kode1: String): LiveData<List<FTax>>


    @Query("SELECT * FROM fTax WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FTax>

    @Query("SELECT * FROM fTax WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FTax>>


}