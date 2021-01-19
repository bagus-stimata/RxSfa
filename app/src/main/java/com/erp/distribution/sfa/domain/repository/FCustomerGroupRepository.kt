package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FCustomerGroup
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerGroupRepository {
    fun getRemoteAllFCustomerGroup(): Single<List<FCustomerGroup>>
    fun getRemoteFCustomerGroupById(id: Int): Single<FCustomerGroup>
    fun getRemoteAllFCustomerGroupByDivision(divisionId: Int): Single<List<FCustomerGroup>>
    fun createRemoteFCustomerGroup(fCustomerGroup: FCustomerGroup): Single<FCustomerGroup>
    fun putRemoteFCustomerGroup(id: Int, fCustomerGroup: FCustomerGroup): Single<FCustomerGroup>
    fun deleteRemoteFCustomerGroup(id: Int): Single<FCustomerGroup>

    fun getCacheAllFCustomerGroup(): LiveData<List<FCustomerGroup>>
    fun getCacheFCustomerGroupById(id: Int): LiveData<FCustomerGroup>
    fun getCacheAllFCustomerGroupByDivision(divisionId: Int): LiveData<List<FCustomerGroup>>
    fun addCacheFCustomerGroup(fCustomerGroup: FCustomerGroup)
    fun putCacheFCustomerGroup(fCustomerGroup: FCustomerGroup)
    fun deleteCacheFCustomerGroup(fCustomerGroup: FCustomerGroup)
    fun deleteAllCacheData()


}