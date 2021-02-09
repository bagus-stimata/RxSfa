package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FCustomerWithFDivision
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.data.source.entity.FCustomerWithFDivisionAndGroup
import com.erp.distribution.sfa.data.source.entity.FCustomerWithGroup
import kotlinx.coroutines.flow.Flow

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FCustomerDao {
    /**
     * @param fCustomerEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fCustomerEntity: FCustomerEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFCustomerEntity: List<FCustomerEntity>)

    @Update
    fun update(fCustomerEntity: FCustomerEntity)

    @Delete
    fun delete(fCustomerEntity: FCustomerEntity)

    @Query("DELETE FROM fCustomer")
    fun deleteAllFCustomer()

//
    fun getAllFCustomerFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean?): Flow<List<FCustomerWithFDivisionAndGroup>> =
        when (sortOrder) {
            SortOrder.BY_KODE -> {
                getAllFCustomerSortedByIDFLow(query)
            }
            SortOrder.BY_NAME -> {
                getAllFCustomerSortedByNameFLow(query)
            }
                else -> getAllFCustomerFLow()
        }


//    @Query("SELECT * FROM fCustomer WHERE  custname LIKE '%' || :searchQuery || '%'  OR custno LIKE '%' || :searchQuery || '%'  ORDER BY custname ")
//    fun getAllFCustomerSortedByNameFLow(searchQuery: String): Flow<List<FCustomerEntity>>
//    @Query("SELECT * FROM fCustomer WHERE  custname LIKE '%' || :searchQuery || '%'  OR custno LIKE '%' || :searchQuery || '%'    ORDER BY custno ")
//    fun getAllFCustomerSortedByIDFLow(searchQuery: String): Flow<List<FCustomerEntity>>

    @Transaction
    @Query("SELECT * FROM fCustomer WHERE  custname LIKE '%' || :searchQuery || '%'  OR custno LIKE '%' || :searchQuery || '%'  ORDER BY custname ")
    fun getAllFCustomerSortedByNameFLow(searchQuery: String): Flow<List<FCustomerWithFDivisionAndGroup>>
    @Transaction
    @Query("SELECT * FROM fCustomer WHERE  custname LIKE '%' || :searchQuery || '%'  OR custno LIKE '%' || :searchQuery || '%'    ORDER BY custno ")
    fun getAllFCustomerSortedByIDFLow(searchQuery: String): Flow<List<FCustomerWithFDivisionAndGroup>>


//    @Query("SELECT * FROM fCustomer ")
//    fun getAllFCustomerFLow(): Flow<List<FCustomerEntity>>
    @Transaction
    @Query("SELECT * FROM fCustomer ")
    fun getAllFCustomerFLow(): Flow<List<FCustomerWithFDivisionAndGroup>>

    @Transaction
    @Query("SELECT * FROM fCustomer ")
    fun getAllFCustomerWithFDivisionLive(): LiveData<List<FCustomerWithFDivision>>
    @Transaction
    @Query("SELECT * FROM fCustomer ")
    fun getAllFCustomerWithGroupLive(): LiveData<List<FCustomerWithGroup>>
    @Transaction
    @Query("SELECT * FROM fCustomer ")
    fun getAllFCustomerWithFDivisionAndGroupLive(): LiveData<List<FCustomerWithFDivisionAndGroup>>

    @Query("SELECT * FROM fCustomer WHERE id IN (:listId)")
    fun getAllFCustomerEntityLive(listId: List<Int>): LiveData<List<FCustomerEntity>>


    @Query("SELECT * FROM fCustomer WHERE id = :id ")
    fun getAllById(id: Int): FCustomerEntity
    @Query("SELECT * FROM fCustomer WHERE id = :id ")
    fun getAllByIdLive(id: Int): LiveData<FCustomerEntity>
    @Query("SELECT * FROM fCustomer WHERE id = :id ")
    fun getAllByIdFlow(id: Int): Flow<FCustomerEntity>


    @get:Query("SELECT * FROM fCustomer ")
    val getAllFCustomerEntity: List<FCustomerEntity>
    @get:Query("SELECT * FROM fCustomer ")
    val getAllFCustomerEntityLive: LiveData<List<FCustomerEntity>>

    @Query("SELECT * FROM fCustomer WHERE custname LIKE :custname ")
    fun getAllFCustomerByKode(custname: String): List<FCustomerEntity>
    @Query("SELECT * FROM fCustomer WHERE custname LIKE :custname ")
    fun getAllFCustomerByKodeLive(custname: String): LiveData<List<FCustomerEntity>>


    @Query("SELECT * FROM fCustomer WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FCustomerEntity>

    @Query("SELECT * FROM fCustomer WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FCustomerEntity>>

}