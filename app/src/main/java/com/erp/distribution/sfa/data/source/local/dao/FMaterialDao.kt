package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FMaterial

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialDao {
    /**
     * @param fMaterial
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterial: FMaterial)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterial: List<FMaterial>)
    
    @Update
    fun update(fMaterial: FMaterial)

    @Delete
    fun delete(fMaterial: FMaterial)

    @Query("DELETE FROM fMaterial")
    fun deleteAllFMaterial()

    @Query("SELECT * FROM fMaterial WHERE id = :id ")
    fun getAllById(id: Int): FMaterial
    @Query("SELECT * FROM fMaterial WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterial>


    @get:Query("SELECT * FROM fMaterial ")
    val getAllFMaterial: List<FMaterial>
    @get:Query("SELECT * FROM fMaterial ")
    val getAllFMaterialLive: LiveData<List<FMaterial>>

    @Query("SELECT * FROM fMaterial WHERE pcode LIKE :pcode ")
    fun getAllFMaterialByKode(pcode: String): List<FMaterial>
    @Query("SELECT * FROM fMaterial WHERE pcode LIKE :pcode ")
    fun getAllFMaterialByKodeLive(pcode: String): LiveData<List<FMaterial>>


    @Query("SELECT * FROM fMaterial WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterial>

    @Query("SELECT * FROM fMaterial WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FMaterial>>
    
}