package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FtSalesdItems
import com.erp.distribution.sfa.data.source.entity.FtSalesh
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSalesdItemsRepository {
    fun getRemoteAllFtSalesdItems(authHeader: String): Single<List<FtSalesdItems>>
    fun getRemoteFtSalesdItemsById(authHeader: String, id: Long): Single<FtSalesdItems>
    fun getRemoteAllFtSalesdItemsByParent(authHeader: String, parentId: Long): Single<List<FtSalesdItems>>
    fun createRemoteFtSalesdItems(authHeader: String, ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>
    fun putRemoteFtSalesdItems(authHeader: String, id: Long, ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>
    fun deleteRemoteFtSalesdItems(authHeader: String, id: Long): Single<FtSalesdItems>

    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItems>>
    fun getAllByFtSaleshAndMaterialFlow(ftSalesBean: Long, fmaterialBean: Int): Flow<List<FtSalesdItems>>
    fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItems>
    fun getCacheAllFtSalesdItemsByParent(parentId: Long): LiveData<List<FtSalesdItems>>

    fun addCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun addCacheListFtSalesdItems(list: List<FtSalesdItems>)

    fun putCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteAllCacheFtsalesdItems()


}