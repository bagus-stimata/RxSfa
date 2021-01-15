package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FPromotionRulesdPayments

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRulesdPaymentsDao {
    /**
     * @param fPromotionRulesdPayments
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRulesdPayments: FPromotionRulesdPayments?)

    @Update
    fun update(fPromotionRulesdPayments: FPromotionRulesdPayments?)

    @Delete
    fun delete(fPromotionRulesdPayments: FPromotionRulesdPayments?)

    @Query("DELETE FROM fPromotionRulesdPayments")
    fun deleteAllFPromotionRulesdPayments()

    @get:Query("SELECT * FROM fPromotionRulesdPayments ")
    val allFPromotionRulesdPaymentsLive: LiveData<List<FPromotionRulesdPayments?>?>?

    @get:Query("SELECT * FROM fPromotionRulesdPayments ")
    val allFPromotionRulesdPayments: List<FPromotionRulesdPayments?>?

    @Query("SELECT * FROM fPromotionRulesdPayments WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRulesdPayments?>?

    @Query("SELECT * FROM fPromotionRulesdPayments WHERE fpromotionRuleshBean = :id ")
    fun getAllByParentId(id: Int): List<FPromotionRulesdPayments?>?
}