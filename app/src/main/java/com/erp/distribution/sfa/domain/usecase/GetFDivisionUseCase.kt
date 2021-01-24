package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FDivisionRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.FDivision
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFDivisionUseCase @Inject constructor(private val repository: FDivisionRepository) : SingleUseCase<List<FDivision>>() {

    override fun buildUseCaseSingle(): Single<List<FDivision>> {
        return repository.getRemoteAllFDivision("aa")
    }
    fun getRemoteAllFDivision(authHeader: String): Single<List<FDivision>>{
        return repository.getRemoteAllFDivision(authHeader)
    }

    fun getRemoteFDivisionById(authHeader: String, id: Int): Single<FDivision>{
        return repository.getRemoteFDivisionById(authHeader, id)
    }
    fun getRemoteAllFDivisionByDivision(authHeader: String, divisionId: Int): Single<List<FDivision>>{
        return repository.getRemoteAllFDivisionByParent(authHeader, divisionId)
    }
    fun createRemoteFDivision(authHeader: String, fDivision: FDivision): Single<FDivision>{
        return repository.createRemoteFDivision(authHeader, fDivision)
    }
    fun putRemoteFDivision(authHeader: String, id: Int, fDivision: FDivision): Single<FDivision>{
        return repository.putRemoteFDivision(authHeader, id, fDivision)
    }
    fun deleteRemoteFDivision(authHeader: String, id: Int): Single<FDivision>{
        return repository.deleteRemoteFDivision(authHeader, id)
    }



    fun getCacheAllFDivision(): LiveData<List<FDivision>>{
        return repository.getCacheAllFDivision()
    }
    fun getCacheFDivisionById(id: Int): LiveData<FDivision>{
        return repository.getCacheFDivisionById(id)
    }
    fun getCacheAllFDivisionByParent(parentId: Int): LiveData<List<FDivision>>{
        return repository.getCacheAllFDivisionByParent(parentId)
    }
    fun addCacheFDivision(fDivision: FDivision){
        repository.addCacheFDivision(fDivision)
    }
    fun addCacheListFDivision(list: List<FDivision>){
        repository.addCacheListFDivision(list)
    }
    fun putCacheFDivision(fDivision: FDivision){
        repository.putCacheFDivision(fDivision)
    }
    fun deleteCacheFDivision(fDivision: FDivision){
        repository.deleteCacheFDivision(fDivision)
    }
    fun deleteAllCacheFDivision(){
        repository.deleteAllCacheFDivision()
    }
}