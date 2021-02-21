package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.data.source.entity.FtSaleshWithFDivisionAndFCustomer
import com.erp.distribution.sfa.data.source.entity.FtSaleshWithFDivisionAndFSalesmanAndFCustomer
import com.erp.distribution.sfa.data.source.entity.FtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSaleshRepository {
    fun getRemoteAllFtSalesh(authHeader: String ): Single<List<FtSaleshEntity>>
    fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSaleshEntity>
    fun getRemoteAllFtSaleshByDivision(authHeader: String, divisionId: Int): Single<List<FtSaleshEntity>>
    fun createRemoteFtSalesh(fauthHeader: String, tSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity>
    fun putRemoteFtSalesh(authHeader: String, id: Long, ftSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity>
    fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSaleshEntity>

    fun getCacheAllFtSalesh(): LiveData<List<FtSaleshEntity>>
    fun getCacheAllFtSaleshLive(): LiveData<List<FtSaleshWithFDivisionAndFCustomer>>

    fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder,  limit: Int, currentOffset: Int, hideSelected: Boolean): Flow<List<FtSaleshWithFDivisionAndFCustomer>>

    fun getCacheFtSaleshWithItemsByIdFlow(id: Long): Flow<FtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItems>
    fun getCacheFtSaleshWithItemsByIdLive(id: Long): LiveData<FtSaleshWithFDivisionAndFCustomer>

    fun getCacheFtSaleshById(id: Long): LiveData<FtSaleshEntity>
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSaleshEntity>>
    fun addCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun addCacheListFtSalesh(list: List<FtSaleshEntity>)
    fun putCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun deleteCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun deleteAllCacheFtSalesh()


}