package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FAreaRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.FArea
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFAreaUseCase @Inject constructor(private val repository: FAreaRepository) : SingleUseCase<List<FArea>>() {

    override fun buildUseCaseSingle(): Single<List<FArea>> {
        return repository.getRemoteAllFArea()
    }
    fun getRemoteAllFArea(): Single<List<FArea>>{
        return repository.getRemoteAllFArea()
    }

    fun getRemoteFAreaById(id: Int): Single<FArea>{
        return repository.getRemoteFAreaById(id)
    }
    fun getRemoteAllFAreaByDivision(divisionId: Int): Single<List<FArea>>{
        return repository.getRemoteAllFAreaByDivision(divisionId)
    }
    fun createRemoteFArea(fArea: FArea): Single<FArea>{
        return repository.createRemoteFArea(fArea)
    }
    fun putRemoteFArea(id: Int, fArea: FArea): Single<FArea>{
        return repository.putRemoteFArea(id, fArea)
    }
    fun deleteRemoteFArea(id: Int): Single<FArea>{
        return repository.deleteRemoteFArea(id)
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
    fun putCacheFArea(fArea: FArea){
        repository.putCacheFArea(fArea)
    }
    fun deleteCacheFArea(fArea: FArea){
        repository.deleteCacheFArea(fArea)
    }
    fun deleteAllCacheData(){
        repository.deleteAllCacheData()
    }
}