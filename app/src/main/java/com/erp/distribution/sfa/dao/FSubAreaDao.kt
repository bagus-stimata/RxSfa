package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FSubArea

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSubAreaDao {
    /**
     * @param fSubArea
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fSubArea: FSubArea)

    @Update
    fun update(fSubArea: FSubArea)

    @Delete
    fun delete(fSubArea: FSubArea)

    @Query("DELETE FROM FSubArea")
    fun deleteAllFSubArea()

    @Query("SELECT * FROM fSubArea WHERE id = :id ")
    fun getAllById(id: Int): FSubArea
    @Query("SELECT * FROM fSubArea WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FSubArea>


    @get:Query("SELECT * FROM fSubArea ")
    val getAllFSubArea: List<FSubArea>
    @get:Query("SELECT * FROM fSubArea ")
    val getAllFSubAreaLive: LiveData<List<FSubArea>>

    @Query("SELECT * FROM fSubArea WHERE kode1 LIKE :kode1 ")
    fun getAllFSubAreaByKode(kode1: String): List<FSubArea>
    @Query("SELECT * FROM fSubArea WHERE kode1 LIKE :kode1 ")
    fun getAllFSubAreaByKodeLive(kode1: String): LiveData<List<FSubArea>>


    @Query("SELECT * FROM fSubArea WHERE fareaBean = :id ")
    fun getAllByParent(id: Int): List<FSubArea>

    @Query("SELECT * FROM fSubArea WHERE fareaBean = :id ")
    fun getAllByParentLive(id: Int): LiveData<List<FSubArea>>
}