package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup1Entity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup1Dao {
    /**
     * @param fMaterialGroup1Entity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialGroup1Entity: FMaterialGroup1Entity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialGroup1Entity: List<FMaterialGroup1Entity>)

    @Update
    fun update(fMaterialGroup1Entity: FMaterialGroup1Entity)

    @Delete
    fun delete(fMaterialGroup1Entity: FMaterialGroup1Entity)

    @Query("DELETE FROM fMaterialGroup1")
    fun deleteAllFMaterialGroup1()

    @Query("SELECT * FROM fMaterialGroup1 WHERE id = :id ")
    fun getAllById(id: Int): FMaterialGroup1Entity
    @Query("SELECT * FROM fMaterialGroup1 WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialGroup1Entity>


    @get:Query("SELECT * FROM fMaterialGroup1 ")
    val getAllFMaterialGroup1Entity: List<FMaterialGroup1Entity>
    @get:Query("SELECT * FROM fMaterialGroup1 ")
    val getAllFMaterialGroup1EntityLive: LiveData<List<FMaterialGroup1Entity>>

    @Query("SELECT * FROM fMaterialGroup1 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup1ByKode(kode1: String): List<FMaterialGroup1Entity>
    @Query("SELECT * FROM fMaterialGroup1 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup1ByKodeLive(kode1: String): LiveData<List<FMaterialGroup1Entity>>


    @Query("SELECT * FROM fMaterialGroup1 WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FMaterialGroup1Entity>

    @Query("SELECT * FROM fMaterialGroup1 WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FMaterialGroup1Entity>>
 }