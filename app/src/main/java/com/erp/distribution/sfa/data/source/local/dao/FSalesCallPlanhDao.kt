package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSalesCallPlanhDao {
    /**
     * @param fSalesCallPlanhEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fSalesCallPlanhEntity: FSalesCallPlanhEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFSalesCallPlanhEntity: List<FSalesCallPlanhEntity>)


    @Update
    fun update(fSalesCallPlanhEntity: FSalesCallPlanhEntity?)

    @Delete
    fun delete(fSalesCallPlanhEntity: FSalesCallPlanhEntity?)

    @Query("DELETE FROM fSalesCallPlanh")
    fun deleteAllFSalesCallPlanh()

    @get:Query("SELECT * FROM fSalesCallPlanh ")
    val getAllFSalesCallPlanhLive: LiveData<List<FSalesCallPlanhEntity>>

    @Query("SELECT * FROM fSalesCallPlanh WHERE fdivisionBean = :fdivisionBean ")
    fun getAllByDivisionLive(fdivisionBean: Int): LiveData<List<FSalesCallPlanhEntity>>
}