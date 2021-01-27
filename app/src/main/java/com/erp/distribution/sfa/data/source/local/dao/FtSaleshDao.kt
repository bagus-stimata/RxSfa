package com.erp.distribution.sfa.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import kotlinx.coroutines.flow.Flow

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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listFtSaleshEntities: List<FtSaleshEntity>)


    @Update
    fun update(ftSaleshEntity: FtSaleshEntity)

    @Delete
    fun delete(ftSaleshEntity: FtSaleshEntity)

    @Query("DELETE FROM ftSalesh")
    fun deleteAllFtSalesh()

    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getAllById(id: Long): FtSaleshEntity
    @Query("SELECT * FROM ftSalesh WHERE refno = :id ")
    fun getAllByIdLive(id: Long): LiveData<FtSaleshEntity>

    fun getAllFtSaleshFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean?): Flow<List<FtSaleshEntity>> =
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
    fun getAllFtSaleshSortedByInvoiceDateFLow(searchQuery: String): Flow<List<FtSaleshEntity>>
    @Query("SELECT * FROM ftSalesh WHERE  orderno LIKE '%' || :searchQuery || '%'  ORDER BY orderDate ")
    fun getAllFtSaleshSortedByOrderDateFLow(searchQuery: String): Flow<List<FtSaleshEntity>>

    @Query("SELECT * FROM ftSalesh ")
    fun getAllFtSaleshFLow(): Flow<List<FtSaleshEntity>>


    @get:Query("SELECT * FROM ftSalesh ")
    val getAllFtSaleshEntities: List<FtSaleshEntity>
    @get:Query("SELECT * FROM ftSalesh ")
    val getAllFtSaleshEntityLive: LiveData<List<FtSaleshEntity>>

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