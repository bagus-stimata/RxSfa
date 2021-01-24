package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FDivision
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FDivisionRepository {
    fun getRemoteAllFDivision(authHeader: String, ): Single<List<FDivision>>
    fun getRemoteFDivisionById(authHeader: String, id: Int): Single<FDivision>
    fun getRemoteAllFDivisionByParent(authHeader: String, parentId: Int): Single<List<FDivision>>
    fun createRemoteFDivision(authHeader: String, fDivision: FDivision): Single<FDivision>
    fun putRemoteFDivision(authHeader: String, id: Int, fDivision: FDivision): Single<FDivision>
    fun deleteRemoteFDivision(authHeader: String, id: Int): Single<FDivision>

    fun getCacheAllFDivision(): LiveData<List<FDivision>>
    fun getCacheFDivisionById(id: Int): LiveData<FDivision>
    fun getCacheAllFDivisionByParent(divisionId: Int): LiveData<List<FDivision>>
    fun addCacheFDivision(fDivision: FDivision)
    fun putCacheFDivision(fDivision: FDivision)
    fun deleteCacheFDivision(fDivision: FDivision)
    fun deleteAllCacheData()


}