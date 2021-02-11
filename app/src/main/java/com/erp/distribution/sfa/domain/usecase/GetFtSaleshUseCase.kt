package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FtSaleshRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFtSaleshUseCase @Inject constructor(
        private val repository: FtSaleshRepository,
        private val fCustomerRepository: FCustomerRepository,
    ) : SingleUseCase<List<FtSaleshEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FtSaleshEntity>> {
        return repository.getRemoteAllFtSalesh("authHeader")
    }
    fun getRemoteAllFtSalesh(authHeader: String): Single<List<FtSaleshEntity>>{
        return repository.getRemoteAllFtSalesh(authHeader)
    }

    fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSaleshEntity>{
        return repository.getRemoteFtSaleshById(authHeader, id)
    }
    fun getRemoteAllFtSaleshByDivision(authHeader: String, divisionId: Int): Single<List<FtSaleshEntity>>{
        return repository.getRemoteAllFtSaleshByDivision(authHeader, divisionId)
    }
    fun createRemoteFtSalesh(authHeader: String, ftSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity>{
        return repository.createRemoteFtSalesh(authHeader, ftSaleshEntity)
    }
    fun putRemoteFtSalesh(authHeader: String, id: Long, ftSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity>{
        return repository.putRemoteFtSalesh(authHeader, id, ftSaleshEntity)
    }
    fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSaleshEntity>{
        return repository.deleteRemoteFtSalesh(authHeader, id)
    }



    fun getCacheAllFtSalesh(): LiveData<List<FtSaleshEntity>>{
        return repository.getCacheAllFtSalesh()
    }
    fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FtSaleshEntity>> {
        return repository.getCacheAllFtSaleshFlow(query, sortOrder, hideSelected)
    }

    fun getCacheAllFtSaleshDomainFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FtSalesh>> {
        return repository.getCacheAllFtSaleshDomainFlow(query, sortOrder, hideSelected).map {
            it.map { newData ->
                //Masih Belum Berhasil Langsung
//                fCustomerRepository.getCacheFCustomerByIdFlow(newData.fcustomerBean.id).collect {
//                    newData.fcustomerBean = FCustomer()
//                }
                newData
            }
        }
    }


    fun getCacheFtSaleshById(id: Long): LiveData<FtSaleshEntity>{
        return repository.getCacheFtSaleshById(id)
    }
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSaleshEntity>>{
        return repository.getCacheAllFtSaleshByDivision(divisionId)
    }
    fun addCacheFtSalesh(ftSaleshEntity: FtSaleshEntity){
        repository.addCacheFtSalesh(ftSaleshEntity)
    }
    fun addCacheFtSaleshDomain(ftSalesh: FtSalesh){
        repository.addCacheFtSalesh(ftSalesh.toEntity())
    }
    fun addCacheListFtSalesh(list: List<FtSaleshEntity>){
        repository.addCacheListFtSalesh(list)
    }
    fun putCacheFtSalesh(ftSaleshEntity: FtSaleshEntity){
        repository.putCacheFtSalesh(ftSaleshEntity)
    }
    fun putCacheFtSaleshDomain(ftSalesh: FtSalesh){
        repository.putCacheFtSalesh(ftSalesh.toEntity())
    }
    fun deleteCacheFtSalesh(ftSaleshEntity: FtSaleshEntity){
        repository.deleteCacheFtSalesh(ftSaleshEntity)
    }
    fun deleteCacheFtSaleshDomain(ftSalesh: FtSalesh){
        repository.deleteCacheFtSalesh(ftSalesh.toEntity())
    }
    fun deleteAllCacheFtSalesh(){
        repository.deleteAllCacheFtSalesh()
    }
}