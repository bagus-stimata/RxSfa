package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FPromotionRulesdValidCustsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRulesdValidCustsDao {
    /**
     * @param fPromotionRulesdValidCustsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRulesdValidCustsEntity: FPromotionRulesdValidCustsEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFPromotionRulesdValidCustEntities: List<FPromotionRulesdValidCustsEntity>)


    @Update
    fun update(fPromotionRulesdValidCustsEntity: FPromotionRulesdValidCustsEntity?)

    @Delete
    fun delete(fPromotionRulesdValidCustsEntity: FPromotionRulesdValidCustsEntity?)

    @Query("DELETE FROM fPromotionRulesdValidCusts")
    fun deleteAllFPromotionRulesdValidCusts()

    @get:Query("SELECT * FROM fPromotionRulesdValidCusts ")
    val allFPromotionRulesdValidCustsEntityLive: LiveData<List<FPromotionRulesdValidCustsEntity?>?>?

    @get:Query("SELECT * FROM fPromotionRulesdValidCusts ")
    val allFPromotionRulesdValidCustEntities: List<FPromotionRulesdValidCustsEntity?>?

    @Query("SELECT * FROM fPromotionRulesdValidCusts WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRulesdValidCustsEntity?>?

    @Query("SELECT * FROM fPromotionRulesdValidCusts WHERE fpromotionRuleshBean = :id ")
    fun getAllByParentId(id: Int): List<FPromotionRulesdValidCustsEntity?>?
}