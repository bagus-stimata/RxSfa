package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.domain.model.FCustomer
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFCustomerUseCase @Inject constructor(private val repository: FCustomerRepository) : SingleUseCase<List<FCustomerEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FCustomerEntity>> {
        return repository.getRemoteAllFCustomer("authHeader")
    }
    fun getRemoteAllFCustomer(authHeader: String): Single<List<FCustomerEntity>>{
        return repository.getRemoteAllFCustomer(authHeader)
    }

    fun getRemoteFCustomerById(authHeader: String, id: Int): Single<FCustomerEntity>{
        return repository.getRemoteFCustomerById(authHeader, id)
    }
    fun getRemoteAllFCustomerByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerEntity>>{
        return repository.getRemoteAllFCustomerByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFCustomerByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FCustomerEntity>>{
        return repository.getRemoteAllFCustomerByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFCustomer(authHeader: String, fCustomerEntity: FCustomerEntity): Single<FCustomerEntity>{
        return repository.createRemoteFCustomer(authHeader, fCustomerEntity)
    }
    fun putRemoteFCustomer(authHeader: String, id: Int, fCustomerEntity: FCustomerEntity): Single<FCustomerEntity>{
        return repository.putRemoteFCustomer(authHeader, id, fCustomerEntity)
    }
    fun deleteRemoteFCustomer(authHeader: String, id: Int): Single<FCustomerEntity>{
        return repository.deleteRemoteFCustomer(authHeader, id)
    }



    fun getCacheAllFCustomer(): LiveData<List<FCustomerEntity>>{
        return repository.getCacheAllFCustomer()
    }
    fun getCacheAllFCustomer(list: List<Int>): LiveData<List<FCustomerEntity>>{
        return repository.getCacheAllFCustomer(list)
    }
    fun getCacheAllFCustomerFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FCustomerEntity>> {
        return repository.getCacheAllFCustomerFlow(query, sortOrder, hideSelected)
    }

    fun getCacheFCustomerById(id: Int): LiveData<FCustomerEntity>{
        return repository.getCacheFCustomerById(id)
    }
    fun getCacheFCustomerDomainById(id: Int): LiveData<FCustomer>{
        return repository.getCacheFCustomerDomainById(id)
    }
    fun getCacheAllFCustomerByDivision(divisionId: Int): LiveData<List<FCustomerEntity>>{
        return repository.getCacheAllFCustomerByDivision(divisionId)
    }
    fun addCacheFCustomer(fCustomerEntity: FCustomerEntity){
        repository.addCacheFCustomer(fCustomerEntity)
    }
    fun addCacheListFCustomer(list: List<FCustomerEntity>){
        repository.addCacheListFCustomer(list)
    }
    fun putCacheFCustomer(fCustomerEntity: FCustomerEntity){
        repository.putCacheFCustomer(fCustomerEntity)
    }
    fun deleteCacheFCustomer(fCustomerEntity: FCustomerEntity){
        repository.deleteCacheFCustomer(fCustomerEntity)
    }
    fun deleteAllCacheFCustomer(){
        repository.deleteAllCacheFArea()
    }

}