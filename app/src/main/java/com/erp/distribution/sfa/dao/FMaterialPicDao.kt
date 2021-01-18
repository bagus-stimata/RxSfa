package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FMaterialPic

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialPicDao {
    /**
     * @param fMaterialPic
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialPic: FMaterialPic)

    @Update
    fun update(fMaterialPic: FMaterialPic)

    @Delete
    fun delete(fMaterialPic: FMaterialPic)

    @Query("DELETE FROM fMaterialPic")
    fun deleteAllFMaterialPic()

    @Query("SELECT * FROM fMaterialPic WHERE id = :id ")
    fun getAllById(id: Int): FMaterialPic
    @Query("SELECT * FROM fMaterialPic WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialPic>


    @get:Query("SELECT * FROM fMaterialPic ")
    val getAllFMaterialPic: List<FMaterialPic>
    @get:Query("SELECT * FROM fMaterialPic ")
    val getAllFMaterialPicLive: LiveData<List<FMaterialPic>>


    @Query("SELECT * FROM fMaterialPic WHERE fmaterialBean = :parentId ")
    fun getAllByParent(parentId: Int): List<FMaterialPic>

    @Query("SELECT * FROM fMaterialPic WHERE fmaterialBean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FMaterialPic>>

}