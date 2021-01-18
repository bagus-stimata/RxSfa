package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FArea

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FAreaDao {
    /**
     * @param fArea
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fArea: FArea)

    @Update
    fun update(fArea: FArea)

    @Delete
    fun delete(fArea: FArea)

    @Query("DELETE FROM FArea")
    fun deleteAllFArea()

    @Query("SELECT * FROM fArea WHERE id = :id ")
    fun getAllById(id: Int): FArea
    @Query("SELECT * FROM fArea WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FArea>


    @get:Query("SELECT * FROM fArea ")
    val getAllFArea: List<FArea>
    @get:Query("SELECT * FROM fArea ")
    val getAllFAreaLive: LiveData<List<FArea>>

    @Query("SELECT * FROM fArea WHERE kode1 LIKE :kode1 ")
    fun getAllFAreaByKode(kode1: String): List<FArea>
    @Query("SELECT * FROM fArea WHERE kode1 LIKE :kode1 ")
    fun getAllFAreaByKodeLive(kode1: String): LiveData<List<FArea>>


    @Query("SELECT * FROM fArea WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FArea>

    @Query("SELECT * FROM fArea WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FArea>>


}