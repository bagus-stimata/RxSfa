package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FCustomer
import com.erp.distribution.sfa.data.source.entity.FtSalesh
import kotlinx.coroutines.flow.Flow

/**
 * Dao ini belum di koneksikan dengan database manapun
 */
@Dao
interface FtSaleshDao {
    /**
     * @param ftSalesh
     * Harus Menggunakan
     * .allowMainThreadQueries() pada Configurasi database utama agar tidak perlu menggunakan AsynT
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ftSalesh: FtSalesh)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtSalesh: List<FtSalesh>)


    @Update
    fun update(ftSalesh: FtSalesh)

    @Delete
    fun delete(ftSalesh: FtSalesh)

    @Query("DELETE FROM ftSalesh")
    fun deleteAllFtSalesh()

    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getAllById(id: Long): FtSalesh
    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getAllByIdLive(id: Long): LiveData<FtSalesh>

    fun getAllFtSaleshFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean?): Flow<List<FtSalesh>> =
        when (sortOrder) {
            SortOrder.BY_INVOICE_DATE -> {
                getAllFtSaleshSortedByInvoiceDateFLow(query)
            }
            SortOrder.BY_ORDER_DATE -> {
                getAllFtSaleshSortedByOrderDateFLow(query)
            }
                else -> getAllFtSaleshFLow()
        }
    
    @Query("SELECT * FROM ftSalesh WHERE  orderno LIKE '%' || :searchQuery || '%'  ORDER BY invoiceDate ")
    fun getAllFtSaleshSortedByInvoiceDateFLow(searchQuery: String): Flow<List<FtSalesh>>
    @Query("SELECT * FROM ftSalesh WHERE  orderno LIKE '%' || :searchQuery || '%'  ORDER BY orderDate ")
    fun getAllFtSaleshSortedByOrderDateFLow(searchQuery: String): Flow<List<FtSalesh>>

    @Query("SELECT * FROM ftSalesh ")
    fun getAllFtSaleshFLow(): Flow<List<FtSalesh>>


    @get:Query("SELECT * FROM ftSalesh ")
    val getAllFtSalesh: List<FtSalesh>
    @get:Query("SELECT * FROM ftSalesh ")
    val getAllFtSaleshLive: LiveData<List<FtSalesh>>

    @Query("SELECT * FROM ftSalesh WHERE orderno LIKE :orderno ")
    fun getAllFtSaleshByOrderNo(orderno: String): List<FtSalesh>
    @Query("SELECT * FROM ftSalesh WHERE orderno LIKE :orderno ")
    fun getAllFtSaleshByOrderNoLive(orderno: String): LiveData<List<FtSalesh>>

    @Query("SELECT * FROM ftSalesh WHERE invoiceno LIKE :invoiceno ")
    fun getAllFtSaleshByInvoiceNo(invoiceno: String): List<FtSalesh>
    @Query("SELECT * FROM ftSalesh WHERE orderno LIKE :invoiceno ")
    fun getAllFtSaleshByInvoiceNoLive(invoiceno: String): LiveData<List<FtSalesh>>


    @Query("SELECT * FROM ftSalesh WHERE fdivisionBean = :id ")
    fun getAllByDivision(id: Int): List<FtSalesh>

    @Query("SELECT * FROM ftSalesh WHERE fdivisionBean = :id ")
    fun getAllByDivisionLive(id: Int): LiveData<List<FtSalesh>>

}