package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity.FCompanyEntity

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCompanyEntity: FCompanyEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFCompanyEntity: List<FCompanyEntity>)

    @Update
    fun update(fCompanyEntity: FCompanyEntity)

    @Delete
    fun delete(fCompanyEntity: FCompanyEntity)

    @Query("DELETE FROM fCompany")
    fun deleteAllFCompany()

    @Query("SELECT * FROM fCompany WHERE id = :id ")
    fun getAllById(id: Int): FCompanyEntity

    @Query("SELECT * FROM fCompany WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCompanyEntity>

    @get:Query("SELECT * FROM fCompany ")
    val getAllFCompanyEntityLive: LiveData<List<FCompanyEntity>>

    @get:Query("SELECT * FROM fCompany ")
    val getAllFCompanyEntityByKodeLive: LiveData<List<FCompanyEntity>>

    @get:Query("SELECT * FROM fCompany ")
    val getAllFCompanyEntity: List<FCompanyEntity>


//    @Query("SELECT * FROM fCompany WHERE fdivisionBean = :id ")
//    fun getAllByDivision(id: Int): List<FCompany>


}