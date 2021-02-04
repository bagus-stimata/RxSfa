package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FAreaRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFAreaUseCase @Inject constructor(private val repository: FAreaRepository) : SingleUseCase<List<FAreaEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FAreaEntity>> {
        return repository.getRemoteAllFArea("aa")
    }
    fun getRemoteAllFArea(authHeader: String): Single<List<FAreaEntity>>{
        return repository.getRemoteAllFArea(authHeader)
    }

    fun getRemoteFAreaById(authHeader: String, id: Int): Single<FAreaEntity>{
        return repository.getRemoteFAreaById(authHeader, id)
    }
    fun getRemoteAllFAreaByDivision(authHeader: String, divisionId: Int): Single<List<FAreaEntity>>{
        return repository.getRemoteAllFAreaByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFAreaByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FAreaEntity>>{
        return repository.getRemoteAllFAreaByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFArea(authHeader: String, fAreaEntity: FAreaEntity): Single<FAreaEntity>{
        return repository.createRemoteFArea(authHeader, fAreaEntity)
    }
    fun putRemoteFArea(authHeader: String, id: Int, fAreaEntity: FAreaEntity): Single<FAreaEntity>{
        return repository.putRemoteFArea(authHeader, id, fAreaEntity)
    }
    fun deleteRemoteFArea(authHeader: String, id: Int): Single<FAreaEntity>{
        return repository.deleteRemoteFArea(authHeader, id)
    }



    fun getCacheAllFArea(): LiveData<List<FAreaEntity>>{
        return repository.getCacheAllFArea()
    }
    fun getCacheFAreaById(id: Int): LiveData<FAreaEntity>{
        return repository.getCacheFAreaById(id)
    }
    fun getCacheAllFAreaByDivision(divisionId: Int): LiveData<List<FAreaEntity>>{
        return repository.getCacheAllFAreaByDivision(divisionId)
    }
    fun addCacheFArea(fAreaEntity: FAreaEntity){
        repository.addCacheFArea(fAreaEntity)
    }
    fun addCacheListFArea(list: List<FAreaEntity>){
        repository.addCacheListFArea(list)
    }
    fun putCacheFArea(fAreaEntity: FAreaEntity){
        repository.putCacheFArea(fAreaEntity)
    }
    fun deleteCacheFArea(fAreaEntity: FAreaEntity){
        repository.deleteCacheFArea(fAreaEntity)
    }
    fun deleteAllCacheFArea(){
        repository.deleteAllCacheFArea()
    }
}