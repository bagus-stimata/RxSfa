package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FCompany
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCompanyRepository {
    fun getRemoteAllFCompany(authHeader: String): Single<List<FCompany>>
    fun getRemoteFCompanyById(authHeader: String, id: Int): Single<FCompany>
//    fun getRemoteAllFCompanyByDivision(divisionId: Int): Single<List<FCompany>>
    fun createRemoteFCompany(authHeader: String, fCompany: FCompany): Single<FCompany>
    fun putRemoteFCompany(authHeader: String, id: Int, fCompany: FCompany): Single<FCompany>
    fun deleteRemoteFCompany(authHeader: String, id: Int): Single<FCompany>

    fun getCacheAllFCompany(): LiveData<List<FCompany>>
    fun getCacheFCompanyById(id: Int): LiveData<FCompany>
//    fun getCacheAllFCompanyByDivision(divisionId: Int): LiveData<List<FCompany>>
    fun addCacheFCompany(fCompany: FCompany)
    fun addCacheListFCompany(list: List<FCompany>)
    fun putCacheFCompany(fCompany: FCompany)
    fun deleteCacheFCompany(fCompany: FCompany)
    fun deleteAllCacheFCompany()


}