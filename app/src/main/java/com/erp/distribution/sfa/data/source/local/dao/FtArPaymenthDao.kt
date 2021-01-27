package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FtArPaymenthEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtArPaymenthDao {
    /**
     * @param ftArPaymenthEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftArPaymenthEntity: FtArPaymenthEntity?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtArPaymenthEntity: List<FtArPaymenthEntity>)


    @Update
    fun update(ftArPaymenthEntity: FtArPaymenthEntity?)

    @Delete
    fun delete(ftArPaymenthEntity: FtArPaymenthEntity?)

    @Query("DELETE FROM ftArPaymenth")
    fun deleteAllFtArPaymenth()

    @get:Query("SELECT * FROM ftArPaymenth ")
    val allFtArPaymenthEntityLive: LiveData<List<FtArPaymenthEntity?>?>?

    @get:Query("SELECT * FROM ftArPaymenth ")
    val allFtArPaymenthEntity: List<FtArPaymenthEntity?>?

    @Query("SELECT * FROM ftArPaymenth WHERE refno = :refno ")
    fun getAllById(refno: Long?): List<FtArPaymenthEntity?>?

    @Query("SELECT * FROM ftArPaymenth WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int?): List<FtArPaymenthEntity?>?
}