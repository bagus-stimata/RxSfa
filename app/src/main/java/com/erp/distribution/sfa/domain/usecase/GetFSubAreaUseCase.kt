package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FSubAreaRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFSubAreaUseCase @Inject constructor(private val repository: FSubAreaRepository) : SingleUseCase<List<FSubAreaEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FSubAreaEntity>> {
        return repository.getRemoteAllFSubArea("aa")
    }
    fun getRemoteAllFSubArea(authHeader: String): Single<List<FSubAreaEntity>>{
        return repository.getRemoteAllFSubArea(authHeader)
    }

    fun getRemoteFSubAreaById(authHeader: String, id: Int): Single<FSubAreaEntity>{
        return repository.getRemoteFSubAreaById(authHeader, id)
    }
    fun getRemoteAllFSubAreaByParent(authHeader: String, parenId: Int): Single<List<FSubAreaEntity>>{
        return repository.getRemoteAllFSubAreaByParent(authHeader, parenId)
    }
    fun createRemoteFSubArea(authHeader: String, fAreaEntity: FSubAreaEntity): Single<FSubAreaEntity>{
        return repository.createRemoteFSubArea(authHeader, fAreaEntity)
    }
    fun putRemoteFSubArea(authHeader: String, id: Int, fAreaEntity: FSubAreaEntity): Single<FSubAreaEntity>{
        return repository.putRemoteFSubArea(authHeader, id, fAreaEntity)
    }
    fun deleteRemoteFSubArea(authHeader: String, id: Int): Single<FSubAreaEntity>{
        return repository.deleteRemoteFSubArea(authHeader, id)
    }



    fun getCacheAllFSubArea(): LiveData<List<FSubAreaEntity>>{
        return repository.getCacheAllFSubArea()
    }
    fun getCacheFSubAreaById(id: Int): LiveData<FSubAreaEntity>{
        return repository.getCacheFSubAreaById(id)
    }
    fun getCacheAllFSubAreaByParent(divisionId: Int): LiveData<List<FSubAreaEntity>>{
        return repository.getCacheAllFSubAreaByParent(divisionId)
    }
    fun addCacheFSubArea(fAreaEntity: FSubAreaEntity){
        repository.addCacheFSubArea(fAreaEntity)
    }
    fun addCacheListFSubArea(list: List<FSubAreaEntity>){
        repository.addCacheListFSubArea(list)
    }
    fun putCacheFSubArea(fAreaEntity: FSubAreaEntity){
        repository.putCacheFSubArea(fAreaEntity)
    }
    fun deleteCacheFSubArea(fAreaEntity: FSubAreaEntity){
        repository.deleteCacheFSubArea(fAreaEntity)
    }
    fun deleteAllCacheFSubArea(){
        repository.deleteAllCacheFSubArea()
    }
}