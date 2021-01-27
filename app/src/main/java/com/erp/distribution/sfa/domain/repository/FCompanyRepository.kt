package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FCompanyEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FCompanyRepository {
    fun getRemoteAllFCompany(authHeader: String): Single<List<FCompanyEntity>>
    fun getRemoteFCompanyById(authHeader: String, id: Int): Single<FCompanyEntity>
//    fun getRemoteAllFCompanyByDivision(divisionId: Int): Single<List<FCompany>>
    fun createRemoteFCompany(authHeader: String, fCompanyEntity: FCompanyEntity): Single<FCompanyEntity>
    fun putRemoteFCompany(authHeader: String, id: Int, fCompanyEntity: FCompanyEntity): Single<FCompanyEntity>
    fun deleteRemoteFCompany(authHeader: String, id: Int): Single<FCompanyEntity>

    fun getCacheAllFCompany(): LiveData<List<FCompanyEntity>>
    fun getCacheFCompanyById(id: Int): LiveData<FCompanyEntity>
//    fun getCacheAllFCompanyByDivision(divisionId: Int): LiveData<List<FCompany>>
    fun addCacheFCompany(fCompanyEntity: FCompanyEntity)
    fun addCacheListFCompany(list: List<FCompanyEntity>)
    fun putCacheFCompany(fCompanyEntity: FCompanyEntity)
    fun deleteCacheFCompany(fCompanyEntity: FCompanyEntity)
    fun deleteAllCacheFCompany()


}