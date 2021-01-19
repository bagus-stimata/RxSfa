package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FtSalesdItems
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSalesdItemsRepository {
    fun getRemoteAllFtSalesdItems(): Single<List<FtSalesdItems>>
    fun getRemoteFtSalesdItemsById(id: Int): Single<FtSalesdItems>
    fun getRemoteAllFtSalesdItemsByParentId(parentId: Int): Single<List<FtSalesdItems>>
    fun createRemoteFtSalesdItems(ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>
    fun putRemoteFtSalesdItems(id: Int, ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>
    fun deleteRemoteFtSalesdItems(id: Int): Single<FtSalesdItems>

    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItems>>
    fun getCacheFtSalesdItemsById(id: Int): LiveData<FtSalesdItems>
    fun getCacheAllFtSalesdItemsByDivision(divisionId: Int): LiveData<List<FtSalesdItems>>
    fun addCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun putCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteCacheFtSalesdItems(ftSalesdItems: FtSalesdItems)
    fun deleteAllCacheData()


}