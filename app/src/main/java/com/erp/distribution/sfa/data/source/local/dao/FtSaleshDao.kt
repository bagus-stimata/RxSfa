package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.*
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtSaleshDao {
    /**
     * @param ftSaleshEntity
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ftSaleshEntity: FtSaleshEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNoReplace(ftSaleshEntity: FtSaleshEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(ftSaleshEntity: FtSaleshEntity): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSingleNoReplace(ftSaleshEntity: FtSaleshEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtSaleshEntities: List<FtSaleshEntity>)


    @Update
    fun update(ftSaleshEntity: FtSaleshEntity)

    @Delete
    fun delete(ftSaleshEntity: FtSaleshEntity)

    @Query("DELETE FROM ftSalesh")
    fun deleteAllFtSalesh()

    @Query("DELETE FROM ftSalesh WHERE invoiceDate < :trDate")
    fun deleteAllBeforeDate(trDate: Date)


    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getAllById(id: Long): FtSaleshEntity

    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getAllByIdLive(id: Long): LiveData<FtSaleshEntity>

    @Transaction
    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getFtSaleshWithItemsByIdFLow(id: Long): Flow<FtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItems>

    @Transaction
    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getFtSaleshWithItemsByIdLive(id: Long): LiveData<FtSaleshWithFDivisionAndFCustomer>



    fun getAllFtSaleshFlow(query: String, sortOrder: SortOrder, limit: Int, currentOffset: Int, hideSelected: Boolean?): Flow<List<FtSaleshWithFDivisionAndFCustomer>> =
        when (sortOrder) {
            SortOrder.BY_INVOICE_DATE -> {
                getAllFtSaleshSortedByInvoiceDateFLow(query, limit, currentOffset)
            }
            SortOrder.BY_ORDER_DATE -> {
                getAllFtSaleshSortedByOrderDateFLow(query, limit, currentOffset)
            }
                else -> getAllFtSaleshFLow( limit, currentOffset)
        }

    @Transaction
    @Query("SELECT * FROM ftSalesh WHERE  orderno LIKE '%' || :searchQuery || '%'  ORDER BY invoiceDate   LIMIT :limit OFFSET :currentOffset ")
    fun getAllFtSaleshSortedByInvoiceDateFLow(searchQuery: String, limit: Int, currentOffset: Int): Flow<List<FtSaleshWithFDivisionAndFCustomer>>
    @Transaction
    @Query("SELECT * FROM ftSalesh WHERE  orderno LIKE '%' || :searchQuery || '%'  ORDER BY orderDate   LIMIT :limit OFFSET :currentOffset ")
    fun getAllFtSaleshSortedByOrderDateFLow(searchQuery: String, limit: Int, currentOffset: Int): Flow<List<FtSaleshWithFDivisionAndFCustomer>>
    @Transaction
    @Query("SELECT * FROM ftSalesh   LIMIT :limit OFFSET :currentOffset ")
    fun getAllFtSaleshFLow(limit: Int, currentOffset: Int): Flow<List<FtSaleshWithFDivisionAndFCustomer>>

    @Transaction
    @Query("SELECT * FROM ftSalesh ")
    fun getAllFtSaleshLive(): LiveData<List<FtSaleshWithFDivisionAndFCustomer>>

    @Transaction
    @Query("SELECT * FROM ftSalesh ")
    fun getAllFtSaleshWithItemsLive(): LiveData<List<FtSaleshWithFCustomerAndItems>>

    @Transaction
    @Query("SELECT * FROM ftSalesh ")
    fun getAllFtSaleshWithItems(): List<FtSaleshWithFCustomerAndItems>


    @Transaction
    @Query("SELECT * FROM ftSalesh   LIMIT :limit OFFSET :currentOffset ")
    fun getAllFtSaleshWithDivisionAndFCustomerFLow(limit: Int, currentOffset: Int): Flow<List<FtSaleshWithFDivisionAndFCustomer>>

    @Transaction
    @Query("SELECT * FROM ftSalesh   LIMIT :limit OFFSET :currentOffset ")
    fun getAllFtSaleshWithFDivisionAndFSalesmanAndFCustomerAndCFLow(limit: Int, currentOffset: Int): Flow<List<FtSaleshWithFDivisionAndFSalesmanAndFCustomer>>

    @Transaction
    @Query("SELECT * FROM ftSalesh   LIMIT :limit OFFSET :currentOffset ")
    fun getAllFtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItemsFLow(limit: Int, currentOffset: Int): Flow<List<FtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItems>>



    @get:Query("SELECT * FROM ftSalesh ")
    val getAllFtSaleshEntities: List<FtSaleshEntity>
    @get:Query("SELECT * FROM ftSalesh ")
    val getAllFtSaleshEntityLive: LiveData<List<FtSaleshEntity>>

    @Query("SELECT * FROM ftSalesh WHERE refno IN (:listRefno)")
    fun getAllFtSaleshEntityLive(listRefno: List<Long>): LiveData<List<FtSaleshEntity>>

    @Query("SELECT * FROM ftSalesh WHERE orderno LIKE :orderno ")
    fun getAllFtSaleshByOrderNo(orderno: String): List<FtSaleshEntity>
    @Query("SELECT * FROM ftSalesh WHERE orderno LIKE :orderno ")
    fun getAllFtSaleshByOrderNoLive(orderno: String): LiveData<List<FtSaleshEntity>>

    @Query("SELECT * FROM ftSalesh WHERE invoiceno LIKE :invoiceno ")
    fun getAllFtSaleshByInvoiceNo(invoiceno: String): List<FtSaleshEntity>
    @Query("SELECT * FROM ftSalesh WHERE orderno LIKE :invoiceno ")
    fun getAllFtSaleshByInvoiceNoLive(invoiceno: String): LiveData<List<FtSaleshEntity>>


    @Query("SELECT * FROM ftSalesh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtSaleshEntity>

    @Query("SELECT * FROM ftSalesh WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FtSaleshEntity>>


}