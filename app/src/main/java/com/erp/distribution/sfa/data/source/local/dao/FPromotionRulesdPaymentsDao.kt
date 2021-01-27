package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FPromotionRulesdPaymentsEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRulesdPaymentsDao {
    /**
     * @param fPromotionRulesdPaymentsEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRulesdPaymentsEntity: FPromotionRulesdPaymentsEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFPromotionRulesdPaymentEntities: List<FPromotionRulesdPaymentsEntity>)
    
    @Update
    fun update(fPromotionRulesdPaymentsEntity: FPromotionRulesdPaymentsEntity?)

    @Delete
    fun delete(fPromotionRulesdPaymentsEntity: FPromotionRulesdPaymentsEntity?)

    @Query("DELETE FROM fPromotionRulesdPayments")
    fun deleteAllFPromotionRulesdPayments()

    @get:Query("SELECT * FROM fPromotionRulesdPayments ")
    val allFPromotionRulesdPaymentsEntityLive: LiveData<List<FPromotionRulesdPaymentsEntity?>?>?

    @get:Query("SELECT * FROM fPromotionRulesdPayments ")
    val allFPromotionRulesdPaymentEntities: List<FPromotionRulesdPaymentsEntity?>?

    @Query("SELECT * FROM fPromotionRulesdPayments WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRulesdPaymentsEntity?>?

    @Query("SELECT * FROM fPromotionRulesdPayments WHERE fpromotionRuleshBean = :id ")
    fun getAllByParentId(id: Int): List<FPromotionRulesdPaymentsEntity?>?
}