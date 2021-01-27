package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup2Entity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup2Dao {
    /**
     * @param fMaterialGroup2Entity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialGroup2Entity: FMaterialGroup2Entity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialGroup2Entity: List<FMaterialGroup2Entity>)
    
    @Update
    fun update(fMaterialGroup2Entity: FMaterialGroup2Entity)

    @Delete
    fun delete(fMaterialGroup2Entity: FMaterialGroup2Entity)

    @Query("DELETE FROM fMaterialGroup2")
    fun deleteAllFMaterialGroup2()

    @Query("SELECT * FROM fMaterialGroup2 WHERE id = :id ")
    fun getAllById(id: Int): FMaterialGroup2Entity
    @Query("SELECT * FROM fMaterialGroup2 WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialGroup2Entity>


    @get:Query("SELECT * FROM fMaterialGroup2 ")
    val getAllFMaterialGroup2Entity: List<FMaterialGroup2Entity>
    @get:Query("SELECT * FROM fMaterialGroup2 ")
    val getAllFMaterialGroup2EntityLive: LiveData<List<FMaterialGroup2Entity>>

    @Query("SELECT * FROM fMaterialGroup2 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup2ByKode(kode1: String): List<FMaterialGroup2Entity>
    @Query("SELECT * FROM fMaterialGroup2 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup2ByKodeLive(kode1: String): LiveData<List<FMaterialGroup2Entity>>


    @Query("SELECT * FROM fMaterialGroup2 WHERE fmaterialGroup1Bean = :parentId ")
    fun getAllByParent(parentId: Int): List<FMaterialGroup2Entity>

    @Query("SELECT * FROM fMaterialGroup2 WHERE fmaterialGroup1Bean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FMaterialGroup2Entity>>

}