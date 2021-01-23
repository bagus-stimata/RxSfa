package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.FCustomer
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFCustomerUseCase @Inject constructor(private val repository: FCustomerRepository) : SingleUseCase<List<FCustomer>>() {

    override fun buildUseCaseSingle(): Single<List<FCustomer>> {
        return repository.getRemoteAllFCustomer()
    }
    fun getRemoteAllFCustomer(): Single<List<FCustomer>>{
        return repository.getRemoteAllFCustomer()
    }

    fun getRemoteFCustomerById(id: Int): Single<FCustomer>{
        return repository.getRemoteFCustomerById(id)
    }
    fun getRemoteAllFCustomerByDivision(divisionId: Int): Single<List<FCustomer>>{
        return repository.getRemoteAllFCustomerByDivision(divisionId)
    }
    fun createRemoteFCustomer(fCustomer: FCustomer): Single<FCustomer>{
        return repository.createRemoteFCustomer(fCustomer)
    }
    fun putRemoteFCustomer(id: Int, fCustomer: FCustomer): Single<FCustomer>{
        return repository.putRemoteFCustomer(id, fCustomer)
    }
    fun deleteRemoteFCustomer(id: Int): Single<FCustomer>{
        return repository.deleteRemoteFCustomer(id)
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