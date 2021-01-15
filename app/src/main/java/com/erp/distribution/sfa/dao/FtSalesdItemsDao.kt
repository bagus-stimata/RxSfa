package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FtSalesdItems

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtSalesdItemsDao {
    /**
     * @param ftSalesdItems
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(ftSalesdItems: FtSalesdItems?)

    @Update
    fun update(ftSalesdItems: FtSalesdItems?)

    @Delete
    fun delete(ftSalesdItems: FtSalesdItems?)

    @Query("DELETE FROM ftSalesdItems")
    fun deleteAllFtSalesdItems()

    @get:Query("SELECT * FROM ftSalesdItems ")
    val allFtSalesdItemsLive: LiveData<List<FtSalesdItems?>?>?

    @get:Query("SELECT * FROM ftSalesdItems ")
    val allFtSalesdItems: List<FtSalesdItems?>?

    @Query("SELECT * FROM ftSalesdItems WHERE id = :id ")
    fun getAllById(id: Long?): List<FtSalesdItems?>?

    @Query("SELECT * FROM ftSalesdItems WHERE ftSaleshBean = :id ")
    fun getAllByParentId(id: Long?): List<FtSalesdItems?>?
}