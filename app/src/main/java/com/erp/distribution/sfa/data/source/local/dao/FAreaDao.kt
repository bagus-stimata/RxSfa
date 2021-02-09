package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FAreaEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FAreaDao {
    /**
     * @param fAreaEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fAreaEntity: FAreaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFAreaEntity: List<FAreaEntity>)

    @Update
    fun update(fAreaEntity: FAreaEntity)

    @Delete
    fun delete(fAreaEntity: FAreaEntity)

    @Query("DELETE FROM fArea")
    fun deleteAllFArea()

    @Query("SELECT * FROM fArea WHERE id = :id ")
    fun getAllById(id: Int): FAreaEntity
    @Query("SELECT * FROM fArea WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FAreaEntity>


    @get:Query("SELECT * FROM fArea ")
    val getAllFAreaEntity: List<FAreaEntity>
    @get:Query("SELECT * FROM fArea ")
    val getAllFAreaEntityLive: LiveData<List<FAreaEntity>>

    @Query("SELECT * FROM fArea WHERE kode1 LIKE :kode1 ")
    fun getAllFAreaByKode(kode1: String): List<FAreaEntity>

    @Query("SELECT * FROM fArea WHERE kode1 LIKE :kode1 ")
    fun getAllFAreaByKodeLive(kode1: String): LiveData<List<FAreaEntity>>


    @Query("SELECT * FROM fArea WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FAreaEntity>

    @Query("SELECT * FROM fArea WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FAreaEntity>>


}