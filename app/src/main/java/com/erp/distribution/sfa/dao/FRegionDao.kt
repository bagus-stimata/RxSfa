package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FRegion

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FRegionDao {
    /**
     * @param fRegion
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fRegion: FRegion?)

    @Update
    fun update(fRegion: FRegion?)

    @Delete
    fun delete(fRegion: FRegion?)

    @Query("DELETE FROM fRegion")
    fun deleteAllFRegion()

    @get:Query("SELECT * FROM fRegion ")
    val allFRegionLive: LiveData<List<FRegion?>?>?

    @get:Query("SELECT * FROM fRegion ")
    val allFRegion: List<FRegion?>?

    @Query("SELECT * FROM fRegion WHERE id = :id ")
    fun getAllById(id: Int): List<FRegion?>?

    @Query("SELECT * FROM fRegion WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FRegion?>?
}