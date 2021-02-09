package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import com.erp.distribution.sfa.data.source.entity.FSubAreaWithFArea

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSubAreaDao {
    /**
     * @param fSubAreaEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fSubAreaEntity: FSubAreaEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFSubAreaEntity: List<FSubAreaEntity>)


    @Update
    fun update(fSubAreaEntity: FSubAreaEntity)

    @Delete
    fun delete(fSubAreaEntity: FSubAreaEntity)

    @Query("DELETE FROM fSubArea")
    fun deleteAllFSubArea()

    @Query("SELECT * FROM fSubArea WHERE id = :id ")
    fun getAllById(id: Int): FSubAreaEntity
    @Query("SELECT * FROM fSubArea WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FSubAreaEntity>


    @get:Query("SELECT * FROM fSubArea ")
    val getAllFSubAreaEntity: List<FSubAreaEntity>
    @get:Query("SELECT * FROM fSubArea ")
    val getAllFSubAreaEntityLive: LiveData<List<FSubAreaEntity>>

    @Query("SELECT * FROM fSubArea ")
    fun getAllFSubAreaEntityLive(): LiveData<List<FSubAreaWithFArea>>

    @Query("SELECT * FROM fSubArea WHERE kode1 LIKE :kode1 ")
    fun getAllFSubAreaByKode(kode1: String): List<FSubAreaEntity>
    @Query("SELECT * FROM fSubArea WHERE kode1 LIKE :kode1 ")
    fun getAllFSubAreaByKodeLive(kode1: String): LiveData<List<FSubAreaEntity>>


    @Query("SELECT * FROM fSubArea WHERE fareaBean = :parentId ")
    fun getAllByParent(parentId: Int): List<FSubAreaEntity>

    @Query("SELECT * FROM fSubArea WHERE fareaBean = :parentId ")
    fun getAllByParentLive(parentId: Int): LiveData<List<FSubAreaEntity>>

}