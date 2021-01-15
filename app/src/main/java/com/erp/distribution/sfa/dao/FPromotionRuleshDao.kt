package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FPromotionRulesh

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRuleshDao {
    /**
     * @param fPromotionRulesh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRulesh: FPromotionRulesh?)

    @Update
    fun update(fPromotionRulesh: FPromotionRulesh?)

    @Delete
    fun delete(fPromotionRulesh: FPromotionRulesh?)

    @Query("DELETE FROM fPromotionRulesh")
    fun deleteAllFPromotionRulesh()

    @get:Query("SELECT * FROM fPromotionRulesh ")
    val allFPromotionRuleshLive: LiveData<List<FPromotionRulesh?>?>?

    @get:Query("SELECT * FROM fPromotionRulesh ")
    val allFPromotionRulesh: List<FPromotionRulesh?>?

    @Query("SELECT * FROM fPromotionRulesh WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRulesh?>?

    @Query("SELECT * FROM fPromotionRulesh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FPromotionRulesh?>?
}