package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FSalesmanRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.FSalesman
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFSalesmanUseCase @Inject constructor(private val repository: FSalesmanRepository) : SingleUseCase<List<FSalesman>>() {

    override fun buildUseCaseSingle(): Single<List<FSalesman>> {
        return repository.getRemoteAllFSalesman("authHeader")
    }
    fun getRemoteAllFSalesman(authHeader: String): Single<List<FSalesman>>{
        return repository.getRemoteAllFSalesman(authHeader)
    }

    fun getRemoteFSalesmanById(authHeader: String, id: Int): Single<FSalesman>{
        return repository.getRemoteFSalesmanById(authHeader, id)
    }
    fun getRemoteAllFSalesmanByDivision(authHeader: String, divisionId: Int): Single<List<FSalesman>>{
        return repository.getRemoteAllFSalesmanByDivision(authHeader, divisionId)
    }
    fun createRemoteFSalesman(authHeader: String, fSalesman: FSalesman): Single<FSalesman>{
        return repository.createRemoteFSalesman(authHeader, fSalesman)
    }
    fun putRemoteFSalesman(authHeader: String, id: Int, fSalesman: FSalesman): Single<FSalesman>{
        return repository.putRemoteFSalesman(authHeader, id, fSalesman)
    }
    fun deleteRemoteFSalesman(authHeader: String, id: Int): Single<FSalesman>{
        return repository.deleteRemoteFSalesman(authHeader, id)
    }



    fun getCacheAllFSalesman(): LiveData<List<FSalesman>>{
        return repository.getCacheAllFSalesman()
    }
    fun getCacheFSalesmanById(id: Int): LiveData<FSalesman>{
        return repository.getCacheFSalesmanById(id)
    }
    fun getCacheAllFSalesmanByDivision(divisionId: Int): LiveData<List<FSalesman>>{
        return repository.getCacheAllFSalesmanByDivision(divisionId)
    }
    fun addCacheFSalesman(fSalesman: FSalesman){
        repository.addCacheFSalesman(fSalesman)
    }
    fun addCacheListFSalesman(list: List<FSalesman>){
        repository.addCacheListFSalesman(list)
    }
    fun putCacheFSalesman( fSalesman: FSalesman){
        repository.putCacheFSalesman(fSalesman)
    }
    fun deleteCacheFSalesman(fSalesman: FSalesman){
        repository.deleteCacheFSalesman(fSalesman)
    }
    fun deleteAllCacheFSalesman(){
        repository.deleteAllCacheFSalesman()
    }
}