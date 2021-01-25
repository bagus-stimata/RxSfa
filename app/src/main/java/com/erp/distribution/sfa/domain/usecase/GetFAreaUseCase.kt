package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FAreaRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FArea
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFAreaUseCase @Inject constructor(private val repository: FAreaRepository) : SingleUseCase<List<FArea>>() {

    override fun buildUseCaseSingle(): Single<List<FArea>> {
        return repository.getRemoteAllFArea("aa")
    }
    fun getRemoteAllFArea(authHeader: String): Single<List<FArea>>{
        return repository.getRemoteAllFArea(authHeader)
    }

    fun getRemoteFAreaById(authHeader: String, id: Int): Single<FArea>{
        return repository.getRemoteFAreaById(authHeader, id)
    }
    fun getRemoteAllFAreaByDivision(authHeader: String, divisionId: Int): Single<List<FArea>>{
        return repository.getRemoteAllFAreaByDivision(authHeader, divisionId)
    }
    fun createRemoteFArea(authHeader: String, fArea: FArea): Single<FArea>{
        return repository.createRemoteFArea(authHeader, fArea)
    }
    fun putRemoteFArea(authHeader: String, id: Int, fArea: FArea): Single<FArea>{
        return repository.putRemoteFArea(authHeader, id, fArea)
    }
    fun deleteRemoteFArea(authHeader: String, id: Int): Single<FArea>{
        return repository.deleteRemoteFArea(authHeader, id)
    }



    fun getCacheAllFArea(): LiveData<List<FArea>>{
        return repository.getCacheAllFArea()
    }
    fun getCacheFAreaById(id: Int): LiveData<FArea>{
        return repository.getCacheFAreaById(id)
    }
    fun getCacheAllFAreaByDivision(divisionId: Int): LiveData<List<FArea>>{
        return repository.getCacheAllFAreaByDivision(divisionId)
    }
    fun addCacheFArea(fArea: FArea){
        repository.addCacheFArea(fArea)
    }
    fun addCacheListFArea(list: List<FArea>){
        repository.addCacheListFArea(list)
    }
    fun putCacheFArea(fArea: FArea){
        repository.putCacheFArea(fArea)
    }
    fun deleteCacheFArea(fArea: FArea){
        repository.deleteCacheFArea(fArea)
    }
    fun deleteAllCacheFArea(){
        repository.deleteAllCacheFArea()
    }
}