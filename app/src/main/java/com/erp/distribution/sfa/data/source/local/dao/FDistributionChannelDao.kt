package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FDistributionChannel

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FDistributionChannelDao {
    /**
     * @param fDistributionChannel
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fDistributionChannel: FDistributionChannel)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFDistributionChannel: List<FDistributionChannel>)

    @Update
    fun update(fDistributionChannel: FDistributionChannel)

    @Delete
    fun delete(fDistributionChannel: FDistributionChannel)

    @Query("DELETE FROM fDistributionChannel")
    fun deleteAllFDistributionChannel()

    @Query("SELECT * FROM fDistributionChannel WHERE id = :id ")
    fun getAllById(id: Int): FDistributionChannel
    @Query("SELECT * FROM fDistributionChannel WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FDistributionChannel>


    @get:Query("SELECT * FROM fDistributionChannel ")
    val getAllFDistributionChannel: List<FDistributionChannel>
    @get:Query("SELECT * FROM fDistributionChannel ")
    val getAllFDistributionChannelLive: LiveData<List<FDistributionChannel>>

    @Query("SELECT * FROM fDistributionChannel WHERE kode1 LIKE :kode1 ")
    fun getAllFDistributionChannelByKode(kode1: String): List<FDistributionChannel>
    @Query("SELECT * FROM fDistributionChannel WHERE kode1 LIKE :kode1 ")
    fun getAllFDistributionChannelByKodeLive(kode1: String): LiveData<List<FDistributionChannel>>


    @Query("SELECT * FROM fDistributionChannel WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FDistributionChannel>

    @Query("SELECT * FROM fDistributionChannel WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FDistributionChannel>>


    
}