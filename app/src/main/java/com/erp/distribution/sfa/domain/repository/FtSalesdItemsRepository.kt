package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FtSalesdItems
import io.reactivex.Single

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
    fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItems>
    fun getCacheAllFtSalesdItemsByParent(parentId: Long): LiveData<List<FtSalesdItems>>
    fun addCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun putCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteAllCacheData()


}