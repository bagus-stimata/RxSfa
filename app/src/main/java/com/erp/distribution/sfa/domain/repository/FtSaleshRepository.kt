package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterial
import com.erp.distribution.sfa.data.source.entity.FtSalesh
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSaleshRepository {
    fun getRemoteAllFtSalesh(authHeader: String ): Single<List<FtSalesh>>
    fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSalesh>
    fun getRemoteAllFtSaleshByDivision(authHeader: String, divisionId: Int): Single<List<FtSalesh>>
    fun createRemoteFtSalesh(fauthHeader: String, tSalesh: FtSalesh): Single<FtSalesh>
    fun putRemoteFtSalesh(authHeader: String, id: Long, ftSalesh: FtSalesh): Single<FtSalesh>
    fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSalesh>

    fun getCacheAllFtSalesh(): LiveData<List<FtSalesh>>
    fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FtSalesh>>
    fun getCacheFtSaleshById(id: Long): LiveData<FtSalesh>
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSalesh>>
    fun addCacheFtSalesh(ftSalesh: FtSalesh)
    fun addCacheListFtSalesh(list: List<FtSalesh>)
    fun putCacheFtSalesh(ftSalesh: FtSalesh)
    fun deleteCacheFtSalesh(ftSalesh: FtSalesh)
    fun deleteAllCacheFtSalesh()


}