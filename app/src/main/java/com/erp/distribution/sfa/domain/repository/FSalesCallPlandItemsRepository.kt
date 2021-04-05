package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsWithHeader
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSalesCallPlandItemsRepository {
    fun getRemoteAllFSalesCallPlandItemsByParent(authHeader: String, ftPricehAlthBean: Long): Single<List<FSalesCallPlandItemsEntity>>

    fun getCacheAllFSalesCallPlandItems(): LiveData<List<FSalesCallPlandItemsWithHeader>>
    fun getCacheAllFSalesCallPlandItemsByParent(fsalesCallPlanhBean: Long): LiveData<List<FSalesCallPlandItemsWithHeader>>
    fun getCacheAllFSalesCallPlandItemsByParentAndCustomer(fsalesCallPlanhBean: Long, fcustomerBean: Int): LiveData<List<FSalesCallPlandItemsEntity>>
    fun addCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity)
    fun addCacheListFSalesCallPlandItems(list: List<FSalesCallPlandItemsEntity>)
    fun putCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity)
    fun deleteCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity)
    fun deleteAllCacheFSalesCallPlandItems()


}