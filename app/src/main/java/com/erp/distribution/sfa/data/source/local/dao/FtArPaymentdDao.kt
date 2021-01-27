package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtArPaymentdEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtArPaymentdDao {
    /**
     * @param ftArPaymentdEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftArPaymentdEntity: FtArPaymentdEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtArPaymentdEntity: List<FtArPaymentdEntity>)


    @Update
    fun update(ftArPaymentdEntity: FtArPaymentdEntity?)

    @Delete
    fun delete(ftArPaymentdEntity: FtArPaymentdEntity?)

    @Query("DELETE FROM ftArPaymentd")
    fun deleteAllFtArPaymentd()

    @get:Query("SELECT * FROM ftArPaymentd ")
    val allFtArPaymentdEntityLive: LiveData<List<FtArPaymentdEntity?>?>?

    @get:Query("SELECT * FROM ftArPaymentd ")
    val allFtArPaymentdEntity: List<FtArPaymentdEntity?>?

    @Query("SELECT * FROM ftArPaymentd WHERE id = :id ")
    fun getAllById(id: Long?): List<FtArPaymentdEntity?>?

    @Query("SELECT * FROM ftArPaymentd WHERE ftArPaymenthBean = :id ")
    fun getAllByParentId(id: Long?): List<FtArPaymentdEntity?>?
}