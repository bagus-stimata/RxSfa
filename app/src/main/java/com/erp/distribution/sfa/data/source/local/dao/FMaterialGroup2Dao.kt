package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup2

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup2Dao {
    /**
     * @param fMaterialGroup2
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialGroup2: FMaterialGroup2)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialGroup2: List<FMaterialGroup2>)
    
    @Update
    fun update(fMaterialGroup2: FMaterialGroup2)

    @Delete
    fun delete(fMaterialGroup2: FMaterialGroup2)

    @Query("DELETE FROM fMaterialGroup2")
    fun deleteAllFMaterialGroup2()

    @Query("SELECT * FROM fMaterialGroup2 WHERE id = :id ")
    fun getAllById(id: Int): FMaterialGroup2
    @Query("SELECT * FROM fMaterialGroup2 WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialGroup2>


    @get:Query("SELECT * FROM fMaterialGroup2 ")
    val getAllFMaterialGroup2: List<FMaterialGroup2>
    @get:Query("SELECT * FROM fMaterialGroup2 ")
    val getAllFMaterialGroup2Live: LiveData<List<FMaterialGroup2>>

    @Query("SELECT * FROM fMaterialGroup2 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup2ByKode(kode1: String): List<FMaterialGroup2>
    @Query("SELECT * FROM fMaterialGroup2 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup2ByKodeLive(kode1: String): LiveData<List<FMaterialGroup2>>


    @Query("SELECT * FROM fMaterialGroup2 WHERE fmaterialGroup1Bean = :parentId ")
    fun getAllByParent(parentId: Int): List<FMaterialGroup2>

    @Query("SELECT * FROM fMaterialGroup2 WHERE fmaterialGroup1Bean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FMaterialGroup2>>

}