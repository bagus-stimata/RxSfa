package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FCustomerGroupRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FCustomerGroupEntity
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFCustomerGroupUseCase @Inject constructor(private val repository: FCustomerGroupRepository) : SingleUseCase<List<FCustomerGroupEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FCustomerGroupEntity>> {
        return repository.getRemoteAllFCustomerGroup("aa")
    }
    fun getRemoteAllFCustomerGroup(authHeader: String): Single<List<FCustomerGroupEntity>>{
        return repository.getRemoteAllFCustomerGroup(authHeader)
    }

    fun getRemoteFCustomerGroupById(authHeader: String, id: Int): Single<FCustomerGroupEntity>{
        return repository.getRemoteFCustomerGroupById(authHeader, id)
    }
    fun getRemoteAllFCustomerGroupByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerGroupEntity>>{
        return repository.getRemoteAllFCustomerGroupByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFCustomerGroupByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FCustomerGroupEntity>>{
        return repository.getRemoteAllFCustomerGroupByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFCustomerGroup(authHeader: String, fCustomerGroupEntity: FCustomerGroupEntity): Single<FCustomerGroupEntity>{
        return repository.createRemoteFCustomerGroup(authHeader, fCustomerGroupEntity)
    }
    fun putRemoteFCustomerGroup(authHeader: String, id: Int, fCustomerGroupEntity: FCustomerGroupEntity): Single<FCustomerGroupEntity>{
        return repository.putRemoteFCustomerGroup(authHeader, id, fCustomerGroupEntity)
    }
    fun deleteRemoteFCustomerGroup(authHeader: String, id: Int): Single<FCustomerGroupEntity>{
        return repository.deleteRemoteFCustomerGroup(authHeader, id)
    }



    fun getCacheAllFCustomerGroup(): LiveData<List<FCustomerGroupEntity>>{
        return repository.getCacheAllFCustomerGroup()
    }
    fun getCacheFCustomerGroupById(id: Int): LiveData<FCustomerGroupEntity>{
        return repository.getCacheFCustomerGroupById(id)
    }
    fun getCacheAllFCustomerGroupByDivision(divisionId: Int): LiveData<List<FCustomerGroupEntity>>{
        return repository.getCacheAllFCustomerGroupByDivision(divisionId)
    }
    fun addCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity){
        repository.addCacheFCustomerGroup(fCustomerGroupEntity)
    }
    fun addCacheListFCustomerGroup(list: List<FCustomerGroupEntity>){
        repository.addCacheListFCustomerGroup(list)
    }
    fun putCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity){
        repository.putCacheFCustomerGroup(fCustomerGroupEntity)
    }
    fun deleteCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity){
        repository.deleteCacheFCustomerGroup(fCustomerGroupEntity)
    }
    fun deleteAllCacheFCustomerGroup(){
        repository.deleteAllCacheFCustomerGroup()
    }
}