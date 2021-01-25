package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FCustomerPic
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerPicRepository {
    fun getRemoteAllFCustomerPic(authHeader: String): Single<List<FCustomerPic>>
    fun getRemoteFCustomerPicById(authHeader: String, id: Int): Single<FCustomerPic>
    fun getRemoteAllFCustomerPicByParent(authHeader: String, parentId: Int): Single<List<FCustomerPic>>
    fun createRemoteFCustomerPic(authHeader: String, fCustomerPic: FCustomerPic): Single<FCustomerPic>
    fun putRemoteFCustomerPic(authHeader: String, id: Int, fCustomerPic: FCustomerPic): Single<FCustomerPic>
    fun deleteRemoteFCustomerPic(authHeader: String, id: Int): Single<FCustomerPic>

    fun getCacheAllFCustomerPic(): LiveData<List<FCustomerPic>>
    fun getCacheFCustomerPicById(id: Int): LiveData<FCustomerPic>
    fun getCacheAllFCustomerPicByParent(parentId: Int): LiveData<List<FCustomerPic>>
    fun addCacheFCustomerPic(fCustomerPic: FCustomerPic)
    fun putCacheFCustomerPic(fCustomerPic: FCustomerPic)
    fun deleteCacheFCustomerPic(fCustomerPic: FCustomerPic)
    fun deleteAllCacheData()


}