package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtSalesdWithFMaterial
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSalesdItemsRepository {
    fun getRemoteAllFtSalesdItems(authHeader: String): Single<List<FtSalesdItemsEntity>>
    fun getRemoteFtSalesdItemsById(authHeader: String, id: Long): Single<FtSalesdItemsEntity>
    fun getRemoteAllFtSalesdItemsByFtSalesh(authHeader: String, parentId: Long): Single<List<FtSalesdItemsEntity>>

    fun createRemoteFtSalesdItems(authHeader: String, ftSalesdItemsEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>
    fun putRemoteFtSalesdItems(authHeader: String, id: Long, ftSalesdItemsEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>
    fun deleteRemoteFtSalesdItems(authHeader: String, id: Long): Single<FtSalesdItemsEntity>

    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItemsEntity>>

    fun getCacheListFtSalesdItemsByFtSaleshAndMaterialLive(ftSalesBean: Long, fmaterialBean: Int): LiveData<List<FtSalesdWithFMaterial>>
    fun getCacheListFtSalesdItemsFtSaleshLive(ftSalesBean: Long): LiveData<List<FtSalesdWithFMaterial>>
    fun getCacheListFtSalesdItemsByFtSalesdItemsByFMaterialLive(fmaterialBean: Int): LiveData<List<FtSalesdWithFMaterial>>

    fun getCacheAllFtSalesdItemsByParent(parentId: Long): LiveData<List<FtSalesdItemsEntity>>
    fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItemsEntity>
    fun addCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun addCacheListFtSalesdItems(list: List<FtSalesdItemsEntity>)

    fun putCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun deleteCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity)
    fun deleteAllCacheFtSalesdItemsByFtSalesh(ftSaleshBean: Long)
    fun deleteAllCacheFtsalesdItems()


}