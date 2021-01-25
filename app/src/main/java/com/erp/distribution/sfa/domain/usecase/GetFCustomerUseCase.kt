package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FCustomer
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFCustomerUseCase @Inject constructor(private val repository: FCustomerRepository) : SingleUseCase<List<FCustomer>>() {

    override fun buildUseCaseSingle(): Single<List<FCustomer>> {
        return repository.getRemoteAllFCustomer("authHeader")
    }
    fun getRemoteAllFCustomer(authHeader: String): Single<List<FCustomer>>{
        return repository.getRemoteAllFCustomer(authHeader)
    }

    fun getRemoteFCustomerById(authHeader: String, id: Int): Single<FCustomer>{
        return repository.getRemoteFCustomerById(authHeader, id)
    }
    fun getRemoteAllFCustomerByDivision(authHeader: String, divisionId: Int): Single<List<FCustomer>>{
        return repository.getRemoteAllFCustomerByDivision(authHeader, divisionId)
    }
    fun createRemoteFCustomer(authHeader: String, fCustomer: FCustomer): Single<FCustomer>{
        return repository.createRemoteFCustomer(authHeader, fCustomer)
    }
    fun putRemoteFCustomer(authHeader: String, id: Int, fCustomer: FCustomer): Single<FCustomer>{
        return repository.putRemoteFCustomer(authHeader, id, fCustomer)
    }
    fun deleteRemoteFCustomer(authHeader: String, id: Int): Single<FCustomer>{
        return repository.deleteRemoteFCustomer(authHeader, id)
    }



    fun getCacheAllFCustomer(): LiveData<List<FCustomer>>{
        return repository.getCacheAllFCustomer()
    }
    fun getCacheFCustomerById(id: Int): LiveData<FCustomer>{
        return repository.getCacheFCustomerById(id)
    }
    fun getCacheAllFCustomerByDivision(divisionId: Int): LiveData<List<FCustomer>>{
        return repository.getCacheAllFCustomerByDivision(divisionId)
    }
    fun addCacheFCustomer(fCustomer: FCustomer){
        repository.addCacheFCustomer(fCustomer)
    }
    fun addCacheListFCustomer(list: List<FCustomer>){
        repository.addCacheListFCustomer(list)
    }
    fun putCacheFCustomer( fCustomer: FCustomer){
        repository.putCacheFCustomer(fCustomer)
    }
    fun deleteCacheFCustomer(fCustomer: FCustomer){
        repository.deleteCacheFCustomer(fCustomer)
    }
    fun deleteAllCacheFCustomer(){
        repository.deleteAllCacheFArea()
    }

}