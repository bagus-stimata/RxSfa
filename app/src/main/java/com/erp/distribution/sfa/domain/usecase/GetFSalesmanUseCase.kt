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
        return repository.getRemoteAllFSalesman()
    }
    fun getRemoteAllFSalesman(): Single<List<FSalesman>>{
        return repository.getRemoteAllFSalesman()
    }

    fun getRemoteFSalesmanById(id: Int): Single<FSalesman>{
        return repository.getRemoteFSalesmanById(id)
    }
    fun getRemoteAllFSalesmanByDivision(divisionId: Int): Single<List<FSalesman>>{
        return repository.getRemoteAllFSalesmanByDivision(divisionId)
    }
    fun createRemoteFSalesman(fSalesman: FSalesman): Single<FSalesman>{
        return repository.createRemoteFSalesman(fSalesman)
    }
    fun putRemoteFSalesman(id: Int, fSalesman: FSalesman): Single<FSalesman>{
        return repository.putRemoteFSalesman(id, fSalesman)
    }
    fun deleteRemoteFSalesman(id: Int): Single<FSalesman>{
        return repository.deleteRemoteFSalesman(id)
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