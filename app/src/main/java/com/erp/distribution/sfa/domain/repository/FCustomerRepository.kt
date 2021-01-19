package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FCustomer
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCustomerRepository {
    fun getRemoteAllFCustomer(): Single<List<FCustomer>>
    fun getRemoteFCustomerById(id: Int): Single<FCustomer>
    fun getRemoteAllFCustomerByDivision(divisionId: Int): Single<List<FCustomer>>
    fun createRemoteFCustomer(fCustomer: FCustomer): Single<FCustomer>
    fun putRemoteFCustomer(id: Int, fCustomer: FCustomer): Single<FCustomer>
    fun deleteRemoteFCustomer(id: Int): Single<FCustomer>

    fun getCacheAllFCustomer(): LiveData<List<FCustomer>>
    fun getCacheFCustomerById(id: Int): LiveData<FCustomer>
    fun getCacheAllFCustomerByDivision(divisionId: Int): LiveData<List<FCustomer>>
    fun addCacheFCustomer(fCustomer: FCustomer)
    fun putCacheFCustomer(fCustomer: FCustomer)
    fun deleteCacheFCustomer(fCustomer: FCustomer)
    fun deleteAllCacheData()


}