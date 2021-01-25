package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FCustomer
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerRepository {
    fun getRemoteAllFCustomer(authHeader: String): Single<List<FCustomer>>
    fun getRemoteFCustomerById(authHeader: String, id: Int): Single<FCustomer>
    fun getRemoteAllFCustomerByDivision(authHeader: String, divisionId: Int): Single<List<FCustomer>>
    fun createRemoteFCustomer(authHeader: String, fCustomer: FCustomer): Single<FCustomer>
    fun putRemoteFCustomer(authHeader: String, id: Int, fCustomer: FCustomer): Single<FCustomer>
    fun deleteRemoteFCustomer(authHeader: String,  id: Int): Single<FCustomer>

    fun getCacheAllFCustomer(): LiveData<List<FCustomer>>
    fun getCacheFCustomerById(id: Int): LiveData<FCustomer>
    fun getCacheAllFCustomerByDivision(divisionId: Int): LiveData<List<FCustomer>>
    fun addCacheFCustomer(fCustomer: FCustomer)
    fun addCacheListFCustomer(list: List<FCustomer>)
    fun putCacheFCustomer(fCustomer: FCustomer)
    fun deleteCacheFCustomer(fCustomer: FCustomer)
    fun deleteAllCacheFArea()


}