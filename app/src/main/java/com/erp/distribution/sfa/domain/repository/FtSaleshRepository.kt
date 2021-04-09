package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.domain.model.FtSalesh
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSaleshRepository {
    fun getRemoteAllFtSalesh(authHeader: String ): Single<List<FtSaleshEntity>>
    fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSaleshEntity>
    fun getRemoteAllFtSaleshByDivision(authHeader: String, fdivisionBean: Int): Single<List<FtSaleshEntity>>
    fun getRemoteAllTotalSalesByFSalesmanThisMonth(authHeader: String, fsalesmanBean: Int): Single<Map<String, Double>>

    fun createRemoteFtSalesh(fauthHeader: String, tSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity>
    fun createRemoteFtSaleshFromAndroid(fauthHeader: String, tSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity>
    fun putRemoteFtSalesh(authHeader: String, id: Long, ftSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity>
    fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSaleshEntity>

    fun getCacheAllFtSalesh(): LiveData<List<FtSaleshEntity>>
    fun getCacheAllFtSaleshLive(): LiveData<List<FtSaleshWithFDivisionAndFCustomer>>

    fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder,  limit: Int, currentOffset: Int, hideSelected: Boolean): Flow<List<FtSaleshWithFDivisionAndFCustomer>>

    fun getCacheAllFtSaleshWithItemsLive(): LiveData<List<FtSaleshWithFCustomerAndItems>>
    fun getCacheAllFtSaleshWithItems(): List<FtSaleshWithFCustomerAndItems>

    fun getCacheFtSaleshWithItemsByIdFlow(id: Long): Flow<FtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItems>
    fun getCacheFtSaleshWithItemsByIdLive(id: Long): LiveData<FtSaleshWithFDivisionAndFCustomer>

    fun getCacheFtSaleshById(id: Long): LiveData<FtSaleshEntity>
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSaleshEntity>>

    fun insertCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun insertCacheFtSaleshNoReplace(ftSaleshEntity: FtSaleshEntity)
    fun insertSingleCacheFtSalesh(ftSaleshEntity: FtSaleshEntity): Single<Long>
    fun insertSingleCacheFtSaleshNoReplace(ftSaleshEntity: FtSaleshEntity): Single<Long>
    fun addCacheListFtSalesh(list: List<FtSaleshEntity>)
    fun putCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun deleteCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun deleteAllCacheFtSalesh()
    fun deleteAllSingleCacheBeforeDate(trDate: Date)


}