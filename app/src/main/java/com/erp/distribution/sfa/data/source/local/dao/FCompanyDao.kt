package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FCompany

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCompany: FCompany)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFCompany: List<FCompany>)

    @Update
    fun update(fCompany: FCompany)

    @Delete
    fun delete(fCompany: FCompany)

    @Query("DELETE FROM fCompany")
    fun deleteAllFCompany()

    @Query("SELECT * FROM fCompany WHERE id = :id ")
    fun getAllById(id: Int): FCompany

    @Query("SELECT * FROM fCompany WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCompany>

    @get:Query("SELECT * FROM fCompany ")
    val getAllFCompanyLive: LiveData<List<FCompany>>

    @get:Query("SELECT * FROM fCompany ")
    val getAllFCompanyByKodeLive: LiveData<List<FCompany>>

    @get:Query("SELECT * FROM fCompany ")
    val getAllFCompany: List<FCompany>


//    @Query("SELECT * FROM fCompany WHERE fdivisionBean = :id ")
//    fun getAllByDivision(id: Int): List<FCompany>


}