package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FUangMuka

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FUangMukaDao {
    /**
     * @param fUangMuka
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert
    fun insert(fUangMuka: FUangMuka?)

    @Update
    fun update(fUangMuka: FUangMuka?)

    @Delete
    fun delete(fUangMuka: FUangMuka?)

    @Query("DELETE FROM fUangMuka")
    fun deleteAllFUangMuka()

    @get:Query("SELECT * FROM fUangMuka ")
    val allFUangMukaLive: LiveData<List<FUangMuka?>?>?

    @get:Query("SELECT * FROM fUangMuka ")
    val allFUangMuka: List<FUangMuka?>?

    @Query("SELECT * FROM fUangMuka WHERE id = :id ")
    fun getAllById(id: Int): List<FUangMuka?>?

    @Query("SELECT * FROM fUangMuka WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FUangMuka?>?
}