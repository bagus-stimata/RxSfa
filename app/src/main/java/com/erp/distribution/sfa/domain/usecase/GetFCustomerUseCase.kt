package com.erp.distribution.sfa.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.data.source.entity.FCustomerWithFDivision
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FCustomerGroup
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFCustomerUseCase @Inject constructor(private val repository: FCustomerRepository) : SingleUseCase<List<FCustomerEntity>>() {
    private val TAG = GetFCustomerUseCase::class.java.simpleName

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
    fun getCacheAllFCustomerWithFDivisionLive(): LiveData<List<FCustomerWithFDivision>>{
        return repository.getCacheAllFCustomerWithFDivisionLive()
    }
    fun getCacheAllFCustomerWithFDivisionDomainLive(): LiveData<List<FCustomer>>{
        return repository.getCacheAllFCustomerWithFDivisionLive().map {
            it.map {
                val fcustomerBean = it.fCustomerEntity.toDomain()
                val division = it.fDivisionEntity.toDomain()
                fcustomerBean.fdivisionBean = division
                fcustomerBean
            }
        }
    }
    fun getCacheAllFCustomerWithGroupDomainLive(): LiveData<List<FCustomer>>{
        return repository.getCacheAllFCustomerWithGroupLive().map {
            it.map { data ->
                val fcustomerBean = data.fCustomerEntity.toDomain()
                data.fCustomerGroupEntity?.let {
//                    fcustomerBean.fcustomerGroupBean = it!!.toDomain()
                    fcustomerBean.fcustomerGroupBean = it.toDomain()
                }
                fcustomerBean
            }
        }
    }
    fun getCacheAllFCustomerWithFDivisionAndGroupDomainLive(): LiveData<List<FCustomer>>{
        return repository.getCacheAllFCustomerWithFDivisionAndGroupLive().map {
            it.map {
                val fcustomerBean = it.fCustomerEntity.toDomain()
                val division = it.fDivisionEntity.toDomain()
                fcustomerBean.fdivisionBean = division
                it.fCustomerGroupEntity?.let {
                    fcustomerBean.fcustomerGroupBean = it.toDomain()
                }

                fcustomerBean
            }
        }
    }
    fun getCacheAllFCustomer(list: List<Int>): LiveData<List<FCustomerEntity>>{
        return repository.getCacheAllFCustomer(list)
    }
//    fun getCacheAllFCustomerFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FCustomerEntity>> {
//        return repository.getCacheAllFCustomerFlow(query, sortOrder, hideSelected)
//    }

    fun getCacheAllFCustomerDomainFlow(query: String, sortOrder: SortOrder,  limit: Int, currentOffset: Int, hideSelected: Boolean): Flow<List<FCustomer>> {
        return repository.getCacheAllFCustomerFlow(query, sortOrder, limit, currentOffset, hideSelected).map {
            it.map {
                val fcustomerBean = it.fCustomerEntity.toDomain()
                val division = it.fDivisionEntity.toDomain()
                fcustomerBean.fdivisionBean = division
                it.fCustomerGroupEntity?.let {
                    fcustomerBean.fcustomerGroupBean = it.toDomain()
                }
                fcustomerBean
            }
        }
    }

    fun getCacheFCustomerById(id: Int): LiveData<FCustomer>{
        return repository.getCacheFCustomerById(id).map {

            var fcustomerBean = FCustomer()

            it.fCustomerEntity?.let {
                fcustomerBean = it.toDomain()
            }
            it.fDivisionEntity?.let {
                fcustomerBean.fdivisionBean = it.toDomain()
            }
            it.fCustomerGroupEntity?.let {
                fcustomerBean.fcustomerGroupBean = it.toDomain()
            }
            fcustomerBean

        }
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