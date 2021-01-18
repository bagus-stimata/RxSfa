package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FDivision

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FDivisionDao {
    /**
     * @param fDivision
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fDivision: FDivision)

    @Update
    fun update(fDivision: FDivision)

    @Delete
    fun delete(fDivision: FDivision)

    @Query("DELETE FROM fDivision")
    fun deleteAllFDivision()

    @Query("SELECT * FROM fDivision WHERE id = :id ")
    fun getAllById(id: Int): FDivision
    @Query("SELECT * FROM fDivision WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FDivision>


    @get:Query("SELECT * FROM fDivision ")
    val getAllFDivision: List<FDivision>
    @get:Query("SELECT * FROM fDivision ")
    val getAllFDivisionLive: LiveData<List<FDivision>>

    @Query("SELECT * FROM fDivision WHERE kode1 LIKE :kode1 ")
    fun getAllFDivisionByKode(kode1: String): List<FDivision>
    @Query("SELECT * FROM fDivision WHERE kode1 LIKE :kode1 ")
    fun getAllFDivisionByKodeLive(kode1: String): LiveData<List<FDivision>>


    @Query("SELECT * FROM fDivision WHERE fcompanyBean = :parentId ")
    fun getAllByParent(parentId: Int): List<FDivision>

    @Query("SELECT * FROM fDivision WHERE fcompanyBean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FDivision>>
}