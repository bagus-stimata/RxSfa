package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FCompanyRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.FCompany
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFCompanyUseCase @Inject constructor(private val repository: FCompanyRepository) : SingleUseCase<List<FCompany>>() {

    override fun buildUseCaseSingle(): Single<List<FCompany>> {
        return repository.getRemoteAllFCompany("aa")
    }
    fun getRemoteAllFCompany(authHeader: String): Single<List<FCompany>>{
        return repository.getRemoteAllFCompany(authHeader)
    }

    fun getRemoteFCompanyById(authHeader: String, id: Int): Single<FCompany>{
        return repository.getRemoteFCompanyById(authHeader, id)
    }
//    fun getRemoteAllFCompanyByDivision(authHeader: String, divisionId: Int): Single<List<FCompany>>{
//        return repository.getRemoteAllFCompanyByDivision(authHeader, divisionId)
//    }
    fun createRemoteFCompany(authHeader: String, fCompany: FCompany): Single<FCompany>{
        return repository.createRemoteFCompany(authHeader, fCompany)
    }
    fun putRemoteFCompany(authHeader: String, id: Int, fCompany: FCompany): Single<FCompany>{
        return repository.putRemoteFCompany(authHeader, id, fCompany)
    }
    fun deleteRemoteFCompany(authHeader: String, id: Int): Single<FCompany>{
        return repository.deleteRemoteFCompany(authHeader, id)
    }



    fun getCacheAllFCompany(): LiveData<List<FCompany>>{
        return repository.getCacheAllFCompany()
    }
    fun getCacheFCompanyById(id: Int): LiveData<FCompany>{
        return repository.getCacheFCompanyById(id)
    }
//    fun getCacheAllFCompanyByDivision(divisionId: Int): LiveData<List<FCompany>>{
//        return repository.getCacheAllFCompanyByDivision(divisionId)
//    }
    fun addCacheFCompany(fCompany: FCompany){
        repository.addCacheFCompany(fCompany)
    }
    fun addCacheListFCompany(list: List<FCompany>){
        repository.addCacheListFCompany(list)
    }
    fun putCacheFCompany(fCompany: FCompany){
        repository.putCacheFCompany(fCompany)
    }
    fun deleteCacheFCompany(fCompany: FCompany){
        repository.deleteCacheFCompany(fCompany)
    }
    fun deleteAllCacheFCompany(){
        repository.deleteAllCacheFCompany()
    }
}