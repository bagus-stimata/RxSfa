package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FCompanyRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FCompanyEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFCompanyUseCase @Inject constructor(private val repository: FCompanyRepository) : SingleUseCase<List<FCompanyEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FCompanyEntity>> {
        return repository.getRemoteAllFCompany("aa")
    }
    fun getRemoteAllFCompany(authHeader: String): Single<List<FCompanyEntity>>{
        return repository.getRemoteAllFCompany(authHeader)
    }

    fun getRemoteFCompanyById(authHeader: String, id: Int): Single<FCompanyEntity>{
        return repository.getRemoteFCompanyById(authHeader, id)
    }
//    fun getRemoteAllFCompanyByDivision(authHeader: String, divisionId: Int): Single<List<FCompany>>{
//        return repository.getRemoteAllFCompanyByDivision(authHeader, divisionId)
//    }
    fun createRemoteFCompany(authHeader: String, fCompanyEntity: FCompanyEntity): Single<FCompanyEntity>{
        return repository.createRemoteFCompany(authHeader, fCompanyEntity)
    }
    fun putRemoteFCompany(authHeader: String, id: Int, fCompanyEntity: FCompanyEntity): Single<FCompanyEntity>{
        return repository.putRemoteFCompany(authHeader, id, fCompanyEntity)
    }
    fun deleteRemoteFCompany(authHeader: String, id: Int): Single<FCompanyEntity>{
        return repository.deleteRemoteFCompany(authHeader, id)
    }



    fun getCacheAllFCompany(): LiveData<List<FCompanyEntity>>{
        return repository.getCacheAllFCompany()
    }
    fun getCacheFCompanyById(id: Int): LiveData<FCompanyEntity>{
        return repository.getCacheFCompanyById(id)
    }
//    fun getCacheAllFCompanyByDivision(divisionId: Int): LiveData<List<FCompany>>{
//        return repository.getCacheAllFCompanyByDivision(divisionId)
//    }
    fun addCacheFCompany(fCompanyEntity: FCompanyEntity){
        repository.addCacheFCompany(fCompanyEntity)
    }
    fun addCacheListFCompany(list: List<FCompanyEntity>){
        repository.addCacheListFCompany(list)
    }
    fun putCacheFCompany(fCompanyEntity: FCompanyEntity){
        repository.putCacheFCompany(fCompanyEntity)
    }
    fun deleteCacheFCompany(fCompanyEntity: FCompanyEntity){
        repository.deleteCacheFCompany(fCompanyEntity)
    }
    fun deleteAllCacheFCompany(){
        repository.deleteAllCacheFCompany()
    }
}