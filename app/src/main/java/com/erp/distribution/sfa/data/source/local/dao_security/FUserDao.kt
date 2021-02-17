package com.erp.distribution.sfa.data.source.local.dao_security

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.data.source.entity_security.FUserWithFDivisionAndSalesmanAndWarehouse
import kotlinx.coroutines.flow.Flow

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FUserDao {
    /**
     * @param fUserEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fUserEntity: FUserEntity)

    @Update
    fun update(fUserEntity: FUserEntity)

    @Delete
    fun delete(fUserEntity: FUserEntity)

    @Query("DELETE FROM fUser")
    fun deleteAllFUser()

    @Query("SELECT * FROM fUser ")
    fun getAllFUserLive(): LiveData<List<FUserEntity>>

    @get:Query("SELECT * FROM fUser ")
    val getAllFUserEntity: List<FUserEntity>


    @Query("SELECT * FROM fUser WHERE id = :id ")
    fun getAllById(id: Int): FUserEntity

    @Query("SELECT * FROM fUser WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FUserEntity>

    @Query("SELECT * FROM fUser WHERE username = :username ")
    fun getAllByUsername(username: String): FUserEntity

    @Query("SELECT * FROM fUser WHERE username = :username ")
    fun getAllByUsernameLive(username: String): LiveData<FUserEntity>

    @Query("SELECT * FROM fUser WHERE email = :email ")
    fun getAllByEmailLive(email: String): LiveData<FUserEntity>


    @Query("SELECT * FROM fUser WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FUserEntity>

    @Query("SELECT * FROM fUser WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FUserEntity>>

//    @Query("SELECT * FROM fUser u " +
//            " LEFT JOIN fDivision a ON a.id = u.fdivisionBean " +
//            " LEFT JOIN fSalesman b ON b.id = u.fsalesmanBean" +
//            " LEFT JOIN fWarehouse c ON c.id = u.fwarehouseBean ")
//    fun getAllFUserFlow(): Flow<List<FUserWithFDivisionAndSalesmanAndWarehouse>>

    @Transaction
    @Query("SELECT * FROM fUser ")
    fun getAllFUserFlow(): Flow<List<FUserWithFDivisionAndSalesmanAndWarehouse>>

    @Transaction
    @Query("SELECT * FROM fUser ")
    fun getAllFUserLiveData(): LiveData<List<FUserWithFDivisionAndSalesmanAndWarehouse>>

}