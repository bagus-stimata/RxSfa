package com.erp.distribution.sfa.data.repository_security

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity_security.FUser

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

    @Query("SELECT * FROM fUser ")
    fun getAllFUserLive(): LiveData<List<FUser>>

    @get:Query("SELECT * FROM fUser ")
    val getAllFUser: List<FUser>


    @Query("SELECT * FROM fUser WHERE id = :id ")
    fun getAllById(id: Int): FUser

    @Query("SELECT * FROM fUser WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FUser>

    @Query("SELECT * FROM fUser WHERE username = :username ")
    fun getAllByUsername(username: String): FUser

    @Query("SELECT * FROM fUser WHERE username = :username ")
    fun getAllByUsernameLive(username: String): LiveData<FUser>

    @Query("SELECT * FROM fUser WHERE email = :email ")
    fun getAllByEmailLive(email: String): LiveData<FUser>


    @Query("SELECT * FROM fUser WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FUser>

    @Query("SELECT * FROM fUser WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FUser>>


}