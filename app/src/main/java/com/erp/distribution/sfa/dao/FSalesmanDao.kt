package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FSalesman

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FSalesmanDao {
    /**
     * @param fSalesman
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fSalesman: FSalesman?)

    @Update
    fun update(fSalesman: FSalesman?)

    @Delete
    fun delete(fSalesman: FSalesman?)

    @Query("DELETE FROM fSalesman")
    fun deleteAllFSalesman()

    @get:Query("SELECT * FROM fSalesman ")
    val allFSalesmanLive: LiveData<List<FSalesman?>?>?

    @get:Query("SELECT * FROM fSalesman ")
    val allFSalesman: List<FSalesman?>?

    @Query("SELECT * FROM fSalesman WHERE id = :id ")
    fun getAllById(id: Int): List<FSalesman?>?

    @Query("SELECT * FROM fSalesman WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FSalesman?>?
}