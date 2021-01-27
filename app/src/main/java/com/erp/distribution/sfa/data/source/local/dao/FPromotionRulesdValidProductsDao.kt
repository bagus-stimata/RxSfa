package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FPromotionRulesdValidProductsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRulesdValidProductsDao {
    /**
     * @param fPromotionRulesdValidProductsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRulesdValidProductsEntity: FPromotionRulesdValidProductsEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFPromotionRulesdValidProductEntities: List<FPromotionRulesdValidProductsEntity>)


    @Update
    fun update(fPromotionRulesdValidProductsEntity: FPromotionRulesdValidProductsEntity?)

    @Delete
    fun delete(fPromotionRulesdValidProductsEntity: FPromotionRulesdValidProductsEntity?)

    @Query("DELETE FROM fPromotionRulesdValidProducts")
    fun deleteAllFPromotionRulesdValidProducts()

    @get:Query("SELECT * FROM fPromotionRulesdValidProducts ")
    val allFPromotionRulesdValidProductsEntityLive: LiveData<List<FPromotionRulesdValidProductsEntity?>?>?

    @get:Query("SELECT * FROM fPromotionRulesdValidProducts ")
    val allFPromotionRulesdValidProductEntities: List<FPromotionRulesdValidProductsEntity?>?

    @Query("SELECT * FROM fPromotionRulesdValidProducts WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRulesdValidProductsEntity?>?

    @Query("SELECT * FROM fPromotionRulesdValidProducts WHERE fpromotionRuleshBean = :id ")
    fun getAllByParentId(id: Int): List<FPromotionRulesdValidProductsEntity?>?
}