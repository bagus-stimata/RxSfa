package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FCustomerPic
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerPicRepository {
    fun getRemoteAllFCustomerPic(): Single<List<FCustomerPic>>
    fun getRemoteFCustomerPicById(id: Int): Single<FCustomerPic>
    fun getRemoteAllFCustomerPicByParent(parentId: Int): Single<List<FCustomerPic>>
    fun createRemoteFCustomerPic(fCustomerPic: FCustomerPic): Single<FCustomerPic>
    fun putRemoteFCustomerPic(id: Int, fCustomerPic: FCustomerPic): Single<FCustomerPic>
    fun deleteRemoteFCustomerPic(id: Int): Single<FCustomerPic>

    fun getCacheAllFCustomerPic(): LiveData<List<FCustomerPic>>
    fun getCacheFCustomerPicById(id: Int): LiveData<FCustomerPic>
    fun getCacheAllFCustomerPicByParent(parentId: Int): LiveData<List<FCustomerPic>>
    fun addCacheFCustomerPic(fCustomerPic: FCustomerPic)
    fun putCacheFCustomerPic(fCustomerPic: FCustomerPic)
    fun deleteCacheFCustomerPic(fCustomerPic: FCustomerPic)
    fun deleteAllCacheData()


}