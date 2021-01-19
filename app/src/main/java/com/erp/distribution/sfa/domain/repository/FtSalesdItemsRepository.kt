package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FtSalesdItems
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSalesdItemsRepository {
    fun getRemoteAllFtSalesdItems(): Single<List<FtSalesdItems>>
    fun getRemoteFtSalesdItemsById(id: Long): Single<FtSalesdItems>
    fun getRemoteAllFtSalesdItemsByParent(parentId: Long): Single<List<FtSalesdItems>>
    fun createRemoteFtSalesdItems(ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>
    fun putRemoteFtSalesdItems(id: Long, ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>
    fun deleteRemoteFtSalesdItems(id: Long): Single<FtSalesdItems>

    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItems>>
    fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItems>
    fun getCacheAllFtSalesdItemsByParent(parentId: Long): LiveData<List<FtSalesdItems>>
    fun addCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun putCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteAllCacheData()


}