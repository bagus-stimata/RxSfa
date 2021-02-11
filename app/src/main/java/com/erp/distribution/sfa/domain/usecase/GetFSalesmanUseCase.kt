package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FSalesmanEntity
import com.erp.distribution.sfa.domain.repository.FSalesmanRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFSalesmanUseCase @Inject constructor(private val repository: FSalesmanRepository) : SingleUseCase<List<FSalesmanEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FSalesmanEntity>> {
        return repository.getRemoteAllFSalesman("authHeader")
    }
    fun getRemoteAllFSalesman(authHeader: String): Single<List<FSalesmanEntity>>{
        return repository.getRemoteAllFSalesman(authHeader)
    }

    fun getRemoteFSalesmanById(authHeader: String, id: Int): Single<FSalesmanEntity>{
        return repository.getRemoteFSalesmanById(authHeader, id)
    }
    fun getRemoteAllFSalesmanByDivision(authHeader: String, divisionId: Int): Single<List<FSalesmanEntity>>{
        return repository.getRemoteAllFSalesmanByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFSalesmanByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FSalesmanEntity>>{
        return repository.getRemoteAllFSalesmanByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFSalesman(authHeader: String, fSalesmanEntity: FSalesmanEntity): Single<FSalesmanEntity>{
        return repository.createRemoteFSalesman(authHeader, fSalesmanEntity)
    }
    fun putRemoteFSalesman(authHeader: String, id: Int, fSalesmanEntity: FSalesmanEntity): Single<FSalesmanEntity>{
        return repository.putRemoteFSalesman(authHeader, id, fSalesmanEntity)
    }
    fun deleteRemoteFSalesman(authHeader: String, id: Int): Single<FSalesmanEntity>{
        return repository.deleteRemoteFSalesman(authHeader, id)
    }



    fun getCacheAllFSalesman(): LiveData<List<FSalesmanEntity>>{
        return repository.getCacheAllFSalesman()
    }
    fun getCacheFSalesmanById(id: Int): LiveData<FSalesmanEntity>{
        return repository.getCacheFSalesmanById(id)
    }
    fun getCacheAllFSalesmanByDivision(divisionId: Int): LiveData<List<FSalesmanEntity>>{
        return repository.getCacheAllFSalesmanByDivision(divisionId)
    }
    fun addCacheFSalesman(fSalesmanEntity: FSalesmanEntity){
        repository.addCacheFSalesman(fSalesmanEntity)
    }
    fun addCacheListFSalesman(list: List<FSalesmanEntity>){
        repository.addCacheListFSalesman(list)
    }
    fun putCacheFSalesman(fSalesmanEntity: FSalesmanEntity){
        repository.putCacheFSalesman(fSalesmanEntity)
    }
    fun deleteCacheFSalesman(fSalesmanEntity: FSalesmanEntity){
        repository.deleteCacheFSalesman(fSalesmanEntity)
    }
    fun deleteAllCacheFSalesman(){
        repository.deleteAllCacheFSalesman()
    }
}