package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FCustomerGroup
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerGroupRepository {
    fun getRemoteAllFCustomerGroup(authHeader: String): Single<List<FCustomerGroup>>
    fun getRemoteFCustomerGroupById(authHeader: String, id: Int): Single<FCustomerGroup>
    fun getRemoteAllFCustomerGroupByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerGroup>>
    fun createRemoteFCustomerGroup(authHeader: String, fCustomerGroup: FCustomerGroup): Single<FCustomerGroup>
    fun putRemoteFCustomerGroup(authHeader: String, id: Int, fCustomerGroup: FCustomerGroup): Single<FCustomerGroup>
    fun deleteRemoteFCustomerGroup(authHeader: String, id: Int): Single<FCustomerGroup>

    fun getCacheAllFCustomerGroup(): LiveData<List<FCustomerGroup>>
    fun getCacheFCustomerGroupById(id: Int): LiveData<FCustomerGroup>
    fun getCacheAllFCustomerGroupByDivision(divisionId: Int): LiveData<List<FCustomerGroup>>
    fun addCacheFCustomerGroup(fCustomerGroup: FCustomerGroup)
    fun putCacheFCustomerGroup(fCustomerGroup: FCustomerGroup)
    fun deleteCacheFCustomerGroup(fCustomerGroup: FCustomerGroup)
    fun deleteAllCacheData()


}