package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FCustomerGroupRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FCustomerGroupEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FCustomerGroup
import io.reactivex.rxjava3.core.Single
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
    fun getCacheAllFCustomerGroupDomainLive(): LiveData<List<FCustomerGroup>>{
        return repository.getCacheAllFCustomerGroup().map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheFCustomerGroupById(id: Int): LiveData<FCustomerGroupEntity>{
        return repository.getCacheFCustomerGroupById(id)
    }
    fun getCacheFCustomerGroupByIdDomainLive(id: Int): LiveData<FCustomerGroup>{
        return repository.getCacheFCustomerGroupById(id).map {
            it.toDomain()
        }
    }
    fun getCacheAllFCustomerGroupByDivisionDomainLive(divisionId: Int): LiveData<List<FCustomerGroup>>{
        return repository.getCacheAllFCustomerGroupByDivision(divisionId).map {
            it.map {
                it.toDomain()
            }
        }
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