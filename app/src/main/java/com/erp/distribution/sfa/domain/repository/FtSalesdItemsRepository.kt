package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSalesdItemsRepository {
    fun getRemoteAllFtSalesdItems(authHeader: String): Single<List<FtSalesdItemsEntity>>
    fun getRemoteFtSalesdItemsById(authHeader: String, id: Long): Single<FtSalesdItemsEntity>
    fun getRemoteAllFtSalesdItemsByParent(authHeader: String, parentId: Long): Single<List<FtSalesdItemsEntity>>
    fun createRemoteFtSalesdItems(authHeader: String, ftSalesdItemsEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>
    fun putRemoteFtSalesdItems(authHeader: String, id: Long, ftSalesdItemsEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>
    fun deleteRemoteFtSalesdItems(authHeader: String, id: Long): Single<FtSalesdItemsEntity>

    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItemsEntity>>
    fun getAllByFtSaleshAndMaterialFlow(ftSalesBean: Long, fmaterialBean: Int): Flow<List<FtSalesdItemsEntity>>
    fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItemsEntity>
    fun getCacheAllFtSalesdItemsByParent(parentId: Long): LiveData<List<FtSalesdItemsEntity>>

    fun addCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun addCacheListFtSalesdItems(list: List<FtSalesdItemsEntity>)

    fun putCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun deleteCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun deleteAllCacheFtsalesdItems()


}