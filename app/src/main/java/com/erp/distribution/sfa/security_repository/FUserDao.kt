package com.erp.distribution.sfa.security_repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.security_model.FUser

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FUserDao {
    /**
     * @param fUser
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fUser: FUser)

    @Update
    fun update(fUser: FUser)

    @Delete
    fun delete(fUser: FUser)

    @Query("DELETE FROM fUser")
    fun deleteAllFUser()

    @get:Query("SELECT * FROM fUser ")
    val allFUserLive: LiveData<List<FUser>>

    @get:Query("SELECT * FROM fUser ")
    val allFUser: List<FUser>

    @Query("SELECT * FROM fUser WHERE id = :id ")
    fun getAllById(id: Int): List<FUser>

    @Query("SELECT * FROM fUser WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FUser>

//    @Query("SELECT * FROM fUser WHERE fdivisionBean = :id ")
//    fun getAllByDivision(id: Int): List<FUser>
//
//    @Query("SELECT * FROM fUser WHERE fdivisionBean = :id ")
//    fun getAllByDivisionLive(id: Int): LiveData<List<FUser>>

}