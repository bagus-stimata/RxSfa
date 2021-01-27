package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FCustomerPicEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerPicRepository {
    fun getRemoteAllFCustomerPic(authHeader: String): Single<List<FCustomerPicEntity>>
    fun getRemoteFCustomerPicById(authHeader: String, id: Int): Single<FCustomerPicEntity>
    fun getRemoteAllFCustomerPicByParent(authHeader: String, parentId: Int): Single<List<FCustomerPicEntity>>
    fun createRemoteFCustomerPic(authHeader: String, fCustomerPicEntity: FCustomerPicEntity): Single<FCustomerPicEntity>
    fun putRemoteFCustomerPic(authHeader: String, id: Int, fCustomerPicEntity: FCustomerPicEntity): Single<FCustomerPicEntity>
    fun deleteRemoteFCustomerPic(authHeader: String, id: Int): Single<FCustomerPicEntity>

    fun getCacheAllFCustomerPic(): LiveData<List<FCustomerPicEntity>>
    fun getCacheFCustomerPicById(id: Int): LiveData<FCustomerPicEntity>
    fun getCacheAllFCustomerPicByParent(parentId: Int): LiveData<List<FCustomerPicEntity>>
    fun addCacheFCustomerPic(fCustomerPicEntity: FCustomerPicEntity)
    fun putCacheFCustomerPic(fCustomerPicEntity: FCustomerPicEntity)
    fun deleteCacheFCustomerPic(fCustomerPicEntity: FCustomerPicEntity)
    fun deleteAllCacheData()


}