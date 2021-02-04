package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FMaterialGroup1Repository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup1Entity
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFMaterialGroup1Group3UseCase @Inject constructor(private val repository: FMaterialGroup1Repository) : SingleUseCase<List<FMaterialGroup1Entity>>() {

    override fun buildUseCaseSingle(): Single<List<FMaterialGroup1Entity>> {
        return repository.getRemoteAllFMaterialGroup1("authHeader")
    }
    fun getRemoteAllFMaterialGroup1(authHeader: String): Single<List<FMaterialGroup1Entity>>{
        return repository.getRemoteAllFMaterialGroup1(authHeader)
    }

    fun getRemoteFMaterialGroup1ById(authHeader: String, id: Int): Single<FMaterialGroup1Entity>{
        return repository.getRemoteFMaterialGroup1ById(authHeader, id)
    }
    fun getRemoteAllFMaterialGroup1ByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialGroup1Entity>>{
        return repository.getRemoteAllFMaterialGroup1ByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFMaterialGroup1ByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterialGroup1Entity>>{
        return repository.getRemoteAllFMaterialGroup1ByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFMaterialGroup1(authHeader: String, fMaterialGroup1Entity: FMaterialGroup1Entity): Single<FMaterialGroup1Entity>{
        return repository.createRemoteFMaterialGroup1(authHeader, fMaterialGroup1Entity)
    }
    fun putRemoteFMaterialGroup1(authHeader: String, id: Int, fMaterialGroup1Entity: FMaterialGroup1Entity): Single<FMaterialGroup1Entity>{
        return repository.putRemoteFMaterialGroup1(authHeader, id, fMaterialGroup1Entity)
    }
    fun deleteRemoteFMaterialGroup1(authHeader: String, id: Int): Single<FMaterialGroup1Entity>{
        return repository.deleteRemoteFMaterialGroup1(authHeader, id)
    }



    fun getCacheAllFMaterialGroup1(): LiveData<List<FMaterialGroup1Entity>>{
        return repository.getCacheAllFMaterialGroup1()
    }
//    fun getCacheAllFMaterialGroup1Flow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialGroup1Entity>> {
//        return repository.getCacheAllFMaterialGroup1Flow(query, sortOrder, hideSelected)
//    }
//    fun getCacheAllFMaterialGroup1DomainFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialGroup1>> {
//        return repository.getCacheAllFMaterialGroup1DomainFlow(query, sortOrder, hideSelected)
//    }
    fun getCacheFMaterialGroup1ById(id: Int): LiveData<FMaterialGroup1Entity>{
        return repository.getCacheFMaterialGroup1ById(id)
    }
    fun getCacheAllFMaterialGroup1ByDivision(divisionId: Int): LiveData<List<FMaterialGroup1Entity>>{
        return repository.getCacheAllFMaterialGroup1ByDivision(divisionId)
    }
    fun addCacheFMaterialGroup1(fMaterialGroup1Entity: FMaterialGroup1Entity){
        repository.addCacheFMaterialGroup1(fMaterialGroup1Entity)
    }
    fun putCacheFMaterialGroup1(fMaterialGroup1Entity: FMaterialGroup1Entity){
        repository.putCacheFMaterialGroup1(fMaterialGroup1Entity)
    }
    fun deleteCacheFMaterialGroup1(fMaterialGroup1Entity: FMaterialGroup1Entity){
        repository.deleteCacheFMaterialGroup1(fMaterialGroup1Entity)
    }
    fun deleteAllCacheFMaterialGroup1(){
        repository.deleteAllCacheFMaterialGroup1()
    }
}