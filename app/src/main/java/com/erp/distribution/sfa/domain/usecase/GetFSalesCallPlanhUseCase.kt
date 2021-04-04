package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FSalesCallPlanhRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FSalesCallPlanh
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFSalesCallPlanhUseCase @Inject constructor(private val repository: FSalesCallPlanhRepository) : SingleUseCase<List<FSalesCallPlanhEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FSalesCallPlanhEntity>> {
        return repository.getRemoteAllFSalesCallPlanhByDivisionAndShareToCompany("aa", 9999, 9999)
    }
    fun getRemoteAllFSalesCallPlanhBySalesman(authHeader: String, fsalesmanBean: Int): Single<List<FSalesCallPlanhEntity>>{
        return repository.getRemoteAllFSalesCallPlanhBySalesman(authHeader, fsalesmanBean)
    }
    fun getRemoteAllFSalesCallPlanhByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FSalesCallPlanhEntity>>{
        return repository.getRemoteAllFSalesCallPlanhByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }


    fun getCacheAllFSalesCallPlanh(): LiveData<List<FSalesCallPlanhEntity>>{
        return repository.getCacheAllFSalesCallPlanh()
    }
    fun getCacheAllFSalesCallPlanhDomainLive(): LiveData<List<FSalesCallPlanh>>{
        return repository.getCacheAllFSalesCallPlanh().map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFSalesCallPlanhByDivision(divisionId: Int): LiveData<List<FSalesCallPlanh>>{
        return repository.getCacheAllFSalesCallPlanhByDivision(divisionId).map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFSalesCallPlanhByDivisionDomainLIve(divisionId: Int): LiveData<List<FSalesCallPlanh>>{
        return repository.getCacheAllFSalesCallPlanhByDivision(divisionId).map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun addCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity){
        repository.addCacheFSalesCallPlanh(fSalesCallPlanhEntity)
    }
    fun addCacheListFSalesCallPlanh(list: List<FSalesCallPlanhEntity>){
        repository.addCacheListFSalesCallPlanh(list)
    }
    fun putCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity){
        repository.putCacheFSalesCallPlanh(fSalesCallPlanhEntity)
    }
    fun deleteCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity){
        repository.deleteCacheFSalesCallPlanh(fSalesCallPlanhEntity)
    }
    fun deleteAllCacheFSalesCallPlanh(){
        repository.deleteAllCacheFSalesCallPlanh()
    }
}