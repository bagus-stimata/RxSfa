package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FDistributionChannel

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
    @Insert
    fun insert(fDistributionChannel: FDistributionChannel?)

    @Update
    fun update(fDistributionChannel: FDistributionChannel?)

    @Delete
    fun delete(fDistributionChannel: FDistributionChannel?)

    @Query("DELETE FROM fDistributionChannel")
    fun deleteAllFDistributionChannel()

    @get:Query("SELECT * FROM fDistributionChannel ")
    val allFDistributionChannelLive: LiveData<List<FDistributionChannel?>?>?

    @get:Query("SELECT * FROM fDistributionChannel ")
    val allFDistributionChannel: List<FDistributionChannel?>?

    @Query("SELECT * FROM fDistributionChannel WHERE id = :id ")
    fun getAllById(id: Int): List<FDistributionChannel?>?

    @Query("SELECT * FROM fDistributionChannel WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FDistributionChannel?>?
}