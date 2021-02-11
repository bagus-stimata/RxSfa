package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FMaterialGroup2Repository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup2Entity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFMaterialGroup2UseCase @Inject constructor(private val repository: FMaterialGroup2Repository) : SingleUseCase<List<FMaterialGroup2Entity>>() {

    override fun buildUseCaseSingle(): Single<List<FMaterialGroup2Entity>> {
        return repository.getRemoteAllFMaterialGroup2("authHeader")
    }
    fun getRemoteAllFMaterialGroup2(authHeader: String): Single<List<FMaterialGroup2Entity>>{
        return repository.getRemoteAllFMaterialGroup2(authHeader)
    }

    fun getRemoteFMaterialGroup2ById(authHeader: String, id: Int): Single<FMaterialGroup2Entity>{
        return repository.getRemoteFMaterialGroup2ById(authHeader, id)
    }
    fun getRemoteAllFMaterialGroup2ByParent(authHeader: String, divisionId: Int): Single<List<FMaterialGroup2Entity>>{
        return repository.getRemoteAllFMaterialGroup2ByParent(authHeader, divisionId)
    }
    fun createRemoteFMaterialGroup2(authHeader: String, fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity>{
        return repository.createRemoteFMaterialGroup2(authHeader, fMaterialGroup2Entity)
    }
    fun putRemoteFMaterialGroup2(authHeader: String, id: Int, fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity>{
        return repository.putRemoteFMaterialGroup2(authHeader, id, fMaterialGroup2Entity)
    }
    fun deleteRemoteFMaterialGroup2(authHeader: String, id: Int): Single<FMaterialGroup2Entity>{
        return repository.deleteRemoteFMaterialGroup2(authHeader, id)
    }



    fun getCacheAllFMaterialGroup2(): LiveData<List<FMaterialGroup2Entity>>{
        return repository.getCacheAllFMaterialGroup2()
    }
//    fun getCacheAllFMaterialGroup2DomainFlow(): Flow<List<FMaterialGroup2Entity>>{
//        return repository.getCacheAllFMaterialGroup2DomainFlow()
//    }
//    fun getCacheAllFMaterialGroup2Flow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialGroup2Entity>> {
//        return repository.getCacheAllFMaterialGroup2Flow(query, sortOrder, hideSelected)
//    }
//    fun getCacheAllFMaterialGroup2DomainFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialGroup2>> {
//        return repository.getCacheAllFMaterialGroup2DomainFlow(query, sortOrder, hideSelected)
//    }
    fun getCacheFMaterialGroup2ById(id: Int): LiveData<FMaterialGroup2Entity>{
        return repository.getCacheFMaterialGroup2ById(id)
    }
    fun getCacheAllFMaterialGroup2ByDivision(divisionId: Int): LiveData<List<FMaterialGroup2Entity>>{
        return repository.getCacheAllFMaterialGroup2ByParent(divisionId)
    }
    fun addCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity){
        repository.addCacheFMaterialGroup2(fMaterialGroup2Entity)
    }
    fun putCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity){
        repository.putCacheFMaterialGroup2(fMaterialGroup2Entity)
    }
    fun deleteCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity){
        repository.deleteCacheFMaterialGroup2(fMaterialGroup2Entity)
    }
    fun deleteAllCacheFMaterialGroup2(){
        repository.deleteAllCacheFMaterialGroup2()
    }
}