package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FMaterialRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.domain.model.FMaterial
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFMaterialUseCase @Inject constructor(private val repository: FMaterialRepository) : SingleUseCase<List<FMaterialEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FMaterialEntity>> {
        return repository.getRemoteAllFMaterial("authHeader")
    }
    fun getRemoteAllFMaterial(authHeader: String): Single<List<FMaterialEntity>>{
        return repository.getRemoteAllFMaterial(authHeader)
    }

    fun getRemoteFMaterialById(authHeader: String, id: Int): Single<FMaterialEntity>{
        return repository.getRemoteFMaterialById(authHeader, id)
    }
    fun getRemoteAllFMaterialByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialEntity>>{
        return repository.getRemoteAllFMaterialByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterialEntity>>{
        return repository.getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFMaterial(authHeader: String, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity>{
        return repository.createRemoteFMaterial(authHeader, fMaterialEntity)
    }
    fun putRemoteFMaterial(authHeader: String, id: Int, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity>{
        return repository.putRemoteFMaterial(authHeader, id, fMaterialEntity)
    }
    fun deleteRemoteFMaterial(authHeader: String, id: Int): Single<FMaterialEntity>{
        return repository.deleteRemoteFMaterial(authHeader, id)
    }



    fun getCacheAllFMaterial(): LiveData<List<FMaterialEntity>>{
        return repository.getCacheAllFMaterial()
    }
    fun getCacheAllFMaterialFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialEntity>> {
        return repository.getCacheAllFMaterialFlow(query, sortOrder, hideSelected)
    }
    fun getCacheAllFMaterialDomainFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterial>> {
        return repository.getCacheAllFMaterialDomainFlow(query, sortOrder, hideSelected)
    }
    fun getCacheFMaterialById(id: Int): LiveData<FMaterialEntity>{
        return repository.getCacheFMaterialById(id)
    }
    fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterialEntity>>{
        return repository.getCacheAllFMaterialByDivision(divisionId)
    }
    fun addCacheFMaterial(fMaterialEntity: FMaterialEntity){
        repository.addCacheFMaterial(fMaterialEntity)
    }
    fun addCacheFMaterialDomain(fMaterial: FMaterial){
        repository.addCacheFMaterialDomain(fMaterial)
    }
    fun addCacheListFMaterial(list: List<FMaterialEntity>){
        repository.addCacheListFMaterial(list)
    }
    fun putCacheFMaterial(fMaterialEntity: FMaterialEntity){
        repository.putCacheFMaterial(fMaterialEntity)
    }
    fun putCacheFMaterialDomain(fMaterial: FMaterial){
        repository.putCacheFMaterialDomain(fMaterial)
    }
    fun deleteCacheFMaterial(fMaterialEntity: FMaterialEntity){
        repository.deleteCacheFMaterial(fMaterialEntity)
    }
    fun deleteCacheFMaterialDomain(fMaterial: FMaterial){
        repository.deleteCacheFMaterialDomain(fMaterial)
    }
    fun deleteAllCacheFMaterial(){
        repository.deleteAllCacheFMaterial()
    }
}