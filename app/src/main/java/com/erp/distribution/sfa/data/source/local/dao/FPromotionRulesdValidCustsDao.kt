package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FPromotionRulesdValidCusts

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRulesdValidCustsDao {
    /**
     * @param fPromotionRulesdValidCusts
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRulesdValidCusts: FPromotionRulesdValidCusts?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFPromotionRulesdValidCusts: List<FPromotionRulesdValidCusts>)


    @Update
    fun update(fPromotionRulesdValidCusts: FPromotionRulesdValidCusts?)

    @Delete
    fun delete(fPromotionRulesdValidCusts: FPromotionRulesdValidCusts?)

    @Query("DELETE FROM fPromotionRulesdValidCusts")
    fun deleteAllFPromotionRulesdValidCusts()

    @get:Query("SELECT * FROM fPromotionRulesdValidCusts ")
    val allFPromotionRulesdValidCustsLive: LiveData<List<FPromotionRulesdValidCusts?>?>?

    @get:Query("SELECT * FROM fPromotionRulesdValidCusts ")
    val allFPromotionRulesdValidCusts: List<FPromotionRulesdValidCusts?>?

    @Query("SELECT * FROM fPromotionRulesdValidCusts WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRulesdValidCusts?>?

    @Query("SELECT * FROM fPromotionRulesdValidCusts WHERE fpromotionRuleshBean = :id ")
    fun getAllByParentId(id: Int): List<FPromotionRulesdValidCusts?>?
}