package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FPromotionRulesdValidProducts

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRulesdValidProductsDao {
    /**
     * @param fPromotionRulesdValidProducts
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRulesdValidProducts: FPromotionRulesdValidProducts?)

    @Update
    fun update(fPromotionRulesdValidProducts: FPromotionRulesdValidProducts?)

    @Delete
    fun delete(fPromotionRulesdValidProducts: FPromotionRulesdValidProducts?)

    @Query("DELETE FROM fPromotionRulesdValidProducts")
    fun deleteAllFPromotionRulesdValidProducts()

    @get:Query("SELECT * FROM fPromotionRulesdValidProducts ")
    val allFPromotionRulesdValidProductsLive: LiveData<List<FPromotionRulesdValidProducts?>?>?

    @get:Query("SELECT * FROM fPromotionRulesdValidProducts ")
    val allFPromotionRulesdValidProducts: List<FPromotionRulesdValidProducts?>?

    @Query("SELECT * FROM fPromotionRulesdValidProducts WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRulesdValidProducts?>?

    @Query("SELECT * FROM fPromotionRulesdValidProducts WHERE fpromotionRuleshBean = :id ")
    fun getAllByParentId(id: Int): List<FPromotionRulesdValidProducts?>?
}