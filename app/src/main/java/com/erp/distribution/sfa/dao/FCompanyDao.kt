package com.erp.distribution.sfa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.model.FCompany

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCompanyDao {
    @Insert
    fun insert(fCompany: FCompany?)

    @Update
    fun update(fCompany: FCompany?)

    @Delete
    fun delete(fCompany: FCompany?)

    @Query("DELETE FROM fCompany")
    fun deleteAllFCompany()

    @get:Query("SELECT * FROM fCompany ")
    val allFCompanyLive: LiveData<List<FCompany?>?>?

    @get:Query("SELECT * FROM fCompany ")
    val allFCompany: List<FCompany?>?

    @Query("SELECT * FROM fCompany WHERE id = :id ")
    fun getAllById(id: Int): List<FCompany?>?
}