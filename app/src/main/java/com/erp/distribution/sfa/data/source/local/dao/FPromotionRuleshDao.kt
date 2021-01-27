package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FPromotionRuleshEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FPromotionRuleshDao {
    /**
     * @param fPromotionRuleshEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fPromotionRuleshEntity: FPromotionRuleshEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFPromotionRuleshEntities: List<FPromotionRuleshEntity>)


    @Update
    fun update(fPromotionRuleshEntity: FPromotionRuleshEntity?)

    @Delete
    fun delete(fPromotionRuleshEntity: FPromotionRuleshEntity?)

    @Query("DELETE FROM fPromotionRulesh")
    fun deleteAllFPromotionRulesh()

    @get:Query("SELECT * FROM fPromotionRulesh ")
    val allFPromotionRuleshEntityLive: LiveData<List<FPromotionRuleshEntity?>?>?

    @get:Query("SELECT * FROM fPromotionRulesh ")
    val allFPromotionRuleshEntities: List<FPromotionRuleshEntity?>?

    @Query("SELECT * FROM fPromotionRulesh WHERE id = :id ")
    fun getAllById(id: Int): List<FPromotionRuleshEntity?>?

    @Query("SELECT * FROM fPromotionRulesh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FPromotionRuleshEntity?>?
}