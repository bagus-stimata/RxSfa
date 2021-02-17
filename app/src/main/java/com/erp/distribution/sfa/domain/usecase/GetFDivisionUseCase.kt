package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FDivisionRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FDivisionEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFDivisionUseCase @Inject constructor(private val repository: FDivisionRepository) : SingleUseCase<List<FDivisionEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FDivisionEntity>> {
        return repository.getRemoteAllFDivision("aa")
    }
    fun getRemoteAllFDivision(authHeader: String): Single<List<FDivisionEntity>>{
        return repository.getRemoteAllFDivision(authHeader)
    }

    fun getRemoteFDivisionById(authHeader: String, id: Int): Single<FDivisionEntity>{
        return repository.getRemoteFDivisionById(authHeader, id)
    }
    fun getRemoteAllFDivisionByCompany(authHeader: String, companyId: Int): Single<List<FDivisionEntity>>{
        return repository.getRemoteAllFDivisionByParent(authHeader, companyId)
    }
    fun getAllFDivisionBySameCompanyUsingDivId(authHeader: String, divisionId: Int): Single<List<FDivisionEntity>>{
        return repository.getAllFDivisionBySameCompany(authHeader, divisionId)
    }
    fun createRemoteFDivision(authHeader: String, fDivisionEntity: FDivisionEntity): Single<FDivisionEntity>{
        return repository.createRemoteFDivision(authHeader, fDivisionEntity)
    }
    fun putRemoteFDivision(authHeader: String, id: Int, fDivisionEntity: FDivisionEntity): Single<FDivisionEntity>{
        return repository.putRemoteFDivision(authHeader, id, fDivisionEntity)
    }
    fun deleteRemoteFDivision(authHeader: String, id: Int): Single<FDivisionEntity>{
        return repository.deleteRemoteFDivision(authHeader, id)
    }



    fun getCacheAllFDivision(): LiveData<List<FDivisionEntity>>{
        return repository.getCacheAllFDivision()
    }
    fun getCacheFDivisionById(id: Int): LiveData<FDivisionEntity>{
        return repository.getCacheFDivisionById(id)
    }
    fun getCacheAllFDivisionByParent(parentId: Int): LiveData<List<FDivisionEntity>>{
        return repository.getCacheAllFDivisionByParent(parentId)
    }
    fun addCacheFDivision(fDivisionEntity: FDivisionEntity){
        repository.addCacheFDivision(fDivisionEntity)
    }
    fun addCacheListFDivision(list: List<FDivisionEntity>){
        repository.addCacheListFDivision(list)
    }
    fun putCacheFDivision(fDivisionEntity: FDivisionEntity){
        repository.putCacheFDivision(fDivisionEntity)
    }
    fun deleteCacheFDivision(fDivisionEntity: FDivisionEntity){
        repository.deleteCacheFDivision(fDivisionEntity)
    }
    fun deleteAllCacheFDivision(){
        repository.deleteAllCacheFDivision()
    }
}