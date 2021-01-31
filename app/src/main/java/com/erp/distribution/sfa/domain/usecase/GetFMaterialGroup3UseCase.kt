package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FMaterialGroup3Repository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.sfa.domain.model.FMaterialGroup3
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFMaterialGroup3Group3UseCase @Inject constructor(private val repository: FMaterialGroup3Repository) : SingleUseCase<List<FMaterialGroup3Entity>>() {

    override fun buildUseCaseSingle(): Single<List<FMaterialGroup3Entity>> {
        return repository.getRemoteAllFMaterialGroup3("authHeader")
    }
    fun getRemoteAllFMaterialGroup3(authHeader: String): Single<List<FMaterialGroup3Entity>>{
        return repository.getRemoteAllFMaterialGroup3(authHeader)
    }

    fun getRemoteFMaterialGroup3ById(authHeader: String, id: Int): Single<FMaterialGroup3Entity>{
        return repository.getRemoteFMaterialGroup3ById(authHeader, id)
    }
    fun getRemoteAllFMaterialGroup3ByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialGroup3Entity>>{
        return repository.getRemoteAllFMaterialGroup3ByParent(authHeader, divisionId)
    }
    fun createRemoteFMaterialGroup3(authHeader: String, fMaterialGroup3Entity: FMaterialGroup3Entity): Single<FMaterialGroup3Entity>{
        return repository.createRemoteFMaterialGroup3(authHeader, fMaterialGroup3Entity)
    }
    fun putRemoteFMaterialGroup3(authHeader: String, id: Int, fMaterialGroup3Entity: FMaterialGroup3Entity): Single<FMaterialGroup3Entity>{
        return repository.putRemoteFMaterialGroup3(authHeader, id, fMaterialGroup3Entity)
    }
    fun deleteRemoteFMaterialGroup3(authHeader: String, id: Int): Single<FMaterialGroup3Entity>{
        return repository.deleteRemoteFMaterialGroup3(authHeader, id)
    }



    fun getCacheAllFMaterialGroup3(): LiveData<List<FMaterialGroup3Entity>>{
        return repository.getCacheAllFMaterialGroup3()
    }
    fun getCacheAllFMaterialGroup3DomainFlow(): Flow<List<FMaterialGroup3>>{
        return repository.getCacheAllFMaterialGroup3DomainFlow()
    }
//    fun getCacheAllFMaterialGroup3Flow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialGroup3Entity>> {
//        return repository.getCacheAllFMaterialGroup3Flow(query, sortOrder, hideSelected)
//    }
//    fun getCacheAllFMaterialGroup3DomainFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialGroup3>> {
//        return repository.getCacheAllFMaterialGroup3DomainFlow(query, sortOrder, hideSelected)
//    }
    fun getCacheFMaterialGroup3ById(id: Int): LiveData<FMaterialGroup3Entity>{
        return repository.getCacheFMaterialGroup3ById(id)
    }
    fun getCacheAllFMaterialGroup3ByDivision(divisionId: Int): LiveData<List<FMaterialGroup3Entity>>{
        return repository.getCacheAllFMaterialGroup3ByParent(divisionId)
    }
    fun addCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity){
        repository.addCacheFMaterialGroup3(fMaterialGroup3Entity)
    }
    fun addCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3){
        repository.addCacheFMaterialGroup3Domain(fMaterialGroup3)
    }

    fun addCacheListFMaterialGroup3(list: List<FMaterialGroup3Entity>){
        repository.addCacheListFMaterialGroup3(list)
    }
    fun putCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity){
        repository.putCacheFMaterialGroup3(fMaterialGroup3Entity)
    }
    fun putCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3){
        repository.putCacheFMaterialGroup3Domain(fMaterialGroup3)
    }
    fun deleteCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity){
        repository.deleteCacheFMaterialGroup3(fMaterialGroup3Entity)
    }
    fun deleteCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3){
        repository.deleteCacheFMaterialGroup3Domain(fMaterialGroup3)
    }
    fun deleteAllCacheFMaterialGroup3(){
        repository.deleteAllCacheFMaterialGroup3()
    }
}