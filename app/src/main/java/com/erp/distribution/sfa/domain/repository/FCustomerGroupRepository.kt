package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FCustomerGroupEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerGroupRepository {
    fun getRemoteAllFCustomerGroup(authHeader: String): Single<List<FCustomerGroupEntity>>
    fun getRemoteFCustomerGroupById(authHeader: String, id: Int): Single<FCustomerGroupEntity>
    fun getRemoteAllFCustomerGroupByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerGroupEntity>>
    fun createRemoteFCustomerGroup(authHeader: String, fCustomerGroupEntity: FCustomerGroupEntity): Single<FCustomerGroupEntity>
    fun putRemoteFCustomerGroup(authHeader: String, id: Int, fCustomerGroupEntity: FCustomerGroupEntity): Single<FCustomerGroupEntity>
    fun deleteRemoteFCustomerGroup(authHeader: String, id: Int): Single<FCustomerGroupEntity>

    fun getCacheAllFCustomerGroup(): LiveData<List<FCustomerGroupEntity>>
    fun getCacheFCustomerGroupById(id: Int): LiveData<FCustomerGroupEntity>
    fun getCacheAllFCustomerGroupByDivision(divisionId: Int): LiveData<List<FCustomerGroupEntity>>
    fun addCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity)
    fun putCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity)
    fun deleteCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity)
    fun deleteAllCacheData()


}