package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FDistributionChannelEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FDistributionChannelDao {
    /**
     * @param fDistributionChannelEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fDistributionChannelEntity: FDistributionChannelEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFDistributionChannelEntity: List<FDistributionChannelEntity>)

    @Update
    fun update(fDistributionChannelEntity: FDistributionChannelEntity)

    @Delete
    fun delete(fDistributionChannelEntity: FDistributionChannelEntity)

    @Query("DELETE FROM fDistributionChannel")
    fun deleteAllFDistributionChannel()

    @Query("SELECT * FROM fDistributionChannel WHERE id = :id ")
    fun getAllById(id: Int): FDistributionChannelEntity
    @Query("SELECT * FROM fDistributionChannel WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FDistributionChannelEntity>


    @get:Query("SELECT * FROM fDistributionChannel ")
    val getAllFDistributionChannelEntity: List<FDistributionChannelEntity>
    @get:Query("SELECT * FROM fDistributionChannel ")
    val getAllFDistributionChannelEntityLive: LiveData<List<FDistributionChannelEntity>>

    @Query("SELECT * FROM fDistributionChannel WHERE kode1 LIKE :kode1 ")
    fun getAllFDistributionChannelByKode(kode1: String): List<FDistributionChannelEntity>
    @Query("SELECT * FROM fDistributionChannel WHERE kode1 LIKE :kode1 ")
    fun getAllFDistributionChannelByKodeLive(kode1: String): LiveData<List<FDistributionChannelEntity>>


    @Query("SELECT * FROM fDistributionChannel WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FDistributionChannelEntity>

    @Query("SELECT * FROM fDistributionChannel WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FDistributionChannelEntity>>


    
}