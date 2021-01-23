package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialGroup3

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialGroup3Dao {
    /**
     * @param fMaterialGroup3
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialGroup3: FMaterialGroup3)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialGroup3: List<FMaterialGroup3>)
    
    @Update
    fun update(fMaterialGroup3: FMaterialGroup3)

    @Delete
    fun delete(fMaterialGroup3: FMaterialGroup3)

    @Query("DELETE FROM fMaterialGroup3")
    fun deleteAllFMaterialGroup3()

    @Query("SELECT * FROM fMaterialGroup3 WHERE id = :id ")
    fun getAllById(id: Int): FMaterialGroup3
    @Query("SELECT * FROM fMaterialGroup3 WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialGroup3>


    @get:Query("SELECT * FROM fMaterialGroup3 ")
    val getAllFMaterialGroup3: List<FMaterialGroup3>
    @get:Query("SELECT * FROM fMaterialGroup3 ")
    val getAllFMaterialGroup3Live: LiveData<List<FMaterialGroup3>>

    @Query("SELECT * FROM fMaterialGroup3 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup3ByKode(kode1: String): List<FMaterialGroup3>
    @Query("SELECT * FROM fMaterialGroup3 WHERE kode1 LIKE :kode1 ")
    fun getAllFMaterialGroup3ByKodeLive(kode1: String): LiveData<List<FMaterialGroup3>>


    @Query("SELECT * FROM fMaterialGroup3 WHERE fmaterialGroup2Bean = :parentId ")
    fun getAllByParent(parentId: Int): List<FMaterialGroup3>

    @Query("SELECT * FROM fMaterialGroup3 WHERE fmaterialGroup2Bean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FMaterialGroup3>>

}