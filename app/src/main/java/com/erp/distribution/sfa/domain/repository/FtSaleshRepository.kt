package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.domain.model.FtSalesh
import io.reactivex.Single
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
    fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FtSaleshEntity>>
    fun getCacheAllFtSaleshDomainFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FtSalesh>>
    fun getCacheFtSaleshById(id: Long): LiveData<FtSaleshEntity>
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSaleshEntity>>
    fun addCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun addCacheListFtSalesh(list: List<FtSaleshEntity>)
    fun putCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun deleteCacheFtSalesh(ftSaleshEntity: FtSaleshEntity)
    fun deleteAllCacheFtSalesh()


}