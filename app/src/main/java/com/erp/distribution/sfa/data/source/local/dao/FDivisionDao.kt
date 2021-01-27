package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FDivisionEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FDivisionDao {
    /**
     * @param fDivisionEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fDivisionEntity: FDivisionEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFDivisionEntity: List<FDivisionEntity>)
    
    @Update
    fun update(fDivisionEntity: FDivisionEntity)

    @Delete
    fun delete(fDivisionEntity: FDivisionEntity)

    @Query("DELETE FROM fDivision")
    fun deleteAllFDivision()

    @Query("SELECT * FROM fDivision WHERE id = :id ")
    fun getAllById(id: Int): FDivisionEntity
    @Query("SELECT * FROM fDivision WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FDivisionEntity>


    @get:Query("SELECT * FROM fDivision ")
    val getAllFDivisionEntity: List<FDivisionEntity>
    @get:Query("SELECT * FROM fDivision ")
    val getAllFDivisionEntityLive: LiveData<List<FDivisionEntity>>

    @Query("SELECT * FROM fDivision WHERE kode1 LIKE :kode1 ")
    fun getAllFDivisionByKode(kode1: String): List<FDivisionEntity>
    @Query("SELECT * FROM fDivision WHERE kode1 LIKE :kode1 ")
    fun getAllFDivisionByKodeLive(kode1: String): LiveData<List<FDivisionEntity>>


    @Query("SELECT * FROM fDivision WHERE fcompanyBean = :parentId ")
    fun getAllByParent(parentId: Int): List<FDivisionEntity>

    @Query("SELECT * FROM fDivision WHERE fcompanyBean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FDivisionEntity>>
}