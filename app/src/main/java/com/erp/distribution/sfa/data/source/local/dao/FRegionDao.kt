package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FRegionEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FRegionDao {
    /**
     * @param fRegionEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fRegionEntity: FRegionEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFRegionEntity: List<FRegionEntity>)


    @Update
    fun update(fRegionEntity: FRegionEntity?)

    @Delete
    fun delete(fRegionEntity: FRegionEntity?)

    @Query("DELETE FROM fRegion")
    fun deleteAllFRegion()

    @get:Query("SELECT * FROM fRegion ")
    val allFRegionEntityLive: LiveData<List<FRegionEntity?>?>?

    @get:Query("SELECT * FROM fRegion ")
    val allFRegionEntity: List<FRegionEntity?>?

    @Query("SELECT * FROM fRegion WHERE id = :id ")
    fun getAllById(id: Int): List<FRegionEntity?>?

    @Query("SELECT * FROM fRegion WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FRegionEntity?>?
}