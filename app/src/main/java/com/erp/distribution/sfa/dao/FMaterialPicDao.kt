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
    @Insert
    fun insert(fMaterialPic: FMaterialPic?)

    @Update
    fun update(fMaterialPic: FMaterialPic?)

    @Delete
    fun delete(fMaterialPic: FMaterialPic?)

    @Query("DELETE FROM fMaterialPic")
    fun deleteAllFMaterialPic()

    @get:Query("SELECT * FROM fMaterialPic ")
    val allFMaterialPicLive: LiveData<List<FMaterialPic?>?>?

    @get:Query("SELECT * FROM fMaterialPic ")
    val allFMaterialPic: List<FMaterialPic?>?

    @Query("SELECT * FROM fMaterialPic WHERE id = :id ")
    fun getAllById(id: Int): List<FMaterialPic?>?

    @Query("SELECT * FROM fMaterialPic WHERE fmaterialBean = :id ")
    fun getAllByParentId(id: Int): List<FMaterialPic?>?
}