package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerRepository {
    fun getRemoteAllFCustomer(authHeader: String): Single<List<FCustomerEntity>>
    fun getRemoteFCustomerById(authHeader: String, id: Int): Single<FCustomerEntity>
    fun getRemoteAllFCustomerByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerEntity>>
    fun createRemoteFCustomer(authHeader: String, fCustomerEntity: FCustomerEntity): Single<FCustomerEntity>
    fun putRemoteFCustomer(authHeader: String, id: Int, fCustomerEntity: FCustomerEntity): Single<FCustomerEntity>
    fun deleteRemoteFCustomer(authHeader: String,  id: Int): Single<FCustomerEntity>

    fun getCacheAllFCustomer(): LiveData<List<FCustomerEntity>>
    fun getCacheAllFCustomerFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FCustomerEntity>>
    fun getCacheFCustomerById(id: Int): LiveData<FCustomerEntity>
    fun getCacheAllFCustomerByDivision(divisionId: Int): LiveData<List<FCustomerEntity>>
    fun addCacheFCustomer(fCustomerEntity: FCustomerEntity)
    fun addCacheListFCustomer(list: List<FCustomerEntity>)
    fun putCacheFCustomer(fCustomerEntity: FCustomerEntity)
    fun deleteCacheFCustomer(fCustomerEntity: FCustomerEntity)
    fun deleteAllCacheFArea()


}