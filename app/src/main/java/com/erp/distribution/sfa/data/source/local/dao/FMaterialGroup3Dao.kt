package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3Entity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup3Dao {
    /**
     * @param fMaterialGroup3Entity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialGroup3Entity: FMaterialGroup3Entity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialGroup3Entity: List<FMaterialGroup3Entity>)
    
    @Update
    fun update(fMaterialGroup3Entity: FMaterialGroup3Entity)

    @Delete
    fun delete(fMaterialGroup3Entity: FMaterialGroup3Entity)

    @Query("DELETE FROM fMaterialGroup3")
    fun deleteAllFMaterialGroup3()

    @Query("SELECT * FROM fMaterialGroup3 WHERE id = :id ")
    fun getAllById(id: Int): FMaterialGroup3Entity
    @Query("SELECT * FROM fMaterialGroup3 WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialGroup3Entity>


    @get:Query("SELECT * FROM fMaterialGroup3 ")
    val getAllFMaterialGroup3Entity: List<FMaterialGroup3Entity>
    @get:Query("SELECT * FROM fMaterialGroup3 ")
    val getAllFMaterialGroup3EntityLive: LiveData<List<FMaterialGroup3Entity>>

    @Query("SELECT * FROM fMaterialGroup3 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup3ByKode(kode1: String): List<FMaterialGroup3Entity>
    @Query("SELECT * FROM fMaterialGroup3 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup3ByKodeLive(kode1: String): LiveData<List<FMaterialGroup3Entity>>


    @Query("SELECT * FROM fMaterialGroup3 WHERE fmaterialGroup2Bean = :parentId ")
    fun getAllByParent(parentId: Int): List<FMaterialGroup3Entity>

    @Query("SELECT * FROM fMaterialGroup3 WHERE fmaterialGroup2Bean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FMaterialGroup3Entity>>

}