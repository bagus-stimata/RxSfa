package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FMaterialPicEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FMaterialPicDao {
    /**
     * @param fMaterialPicEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fMaterialPicEntity: FMaterialPicEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFMaterialPicEntity: List<FMaterialPicEntity>)
    
    @Update
    fun update(fMaterialPicEntity: FMaterialPicEntity)

    @Delete
    fun delete(fMaterialPicEntity: FMaterialPicEntity)

    @Query("DELETE FROM fMaterialPic")
    fun deleteAllFMaterialPic()

    @Query("SELECT * FROM fMaterialPic WHERE id = :id ")
    fun getAllById(id: Int): FMaterialPicEntity
    @Query("SELECT * FROM fMaterialPic WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FMaterialPicEntity>


    @get:Query("SELECT * FROM fMaterialPic ")
    val getAllFMaterialPicEntity: List<FMaterialPicEntity>
    @get:Query("SELECT * FROM fMaterialPic ")
    val getAllFMaterialPicEntityLive: LiveData<List<FMaterialPicEntity>>


    @Query("SELECT * FROM fMaterialPic WHERE fmaterialBean = :parentId ")
    fun getAllByParent(parentId: Int): List<FMaterialPicEntity>

    @Query("SELECT * FROM fMaterialPic WHERE fmaterialBean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FMaterialPicEntity>>

}