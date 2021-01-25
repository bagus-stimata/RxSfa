package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup1
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialGroup1Repository {
    fun getRemoteAllFMaterialGroup1(authHeader: String, ): Single<List<FMaterialGroup1>>
    fun getRemoteFMaterialGroup1ById(authHeader: String, id: Int): Single<FMaterialGroup1>
    fun getRemoteAllFMaterialGroup1ByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialGroup1>>
    fun createRemoteFMaterialGroup1(authHeader: String, fMaterialGroup1: FMaterialGroup1): Single<FMaterialGroup1>
    fun putRemoteFMaterialGroup1(authHeader: String, id: Int, fMaterialGroup1: FMaterialGroup1): Single<FMaterialGroup1>
    fun deleteRemoteFMaterialGroup1(authHeader: String, id: Int): Single<FMaterialGroup1>

    fun getCacheAllFMaterialGroup1(): LiveData<List<FMaterialGroup1>>
    fun getCacheFMaterialGroup1ById(id: Int): LiveData<FMaterialGroup1>
    fun getCacheAllFMaterialGroup1ByDivision(divisionId: Int): LiveData<List<FMaterialGroup1>>
    fun addCacheFMaterialGroup1(fMaterialGroup1: FMaterialGroup1)
    fun putCacheFMaterialGroup1(fMaterialGroup1: FMaterialGroup1)
    fun deleteCacheFMaterialGroup1(fMaterialGroup1: FMaterialGroup1)
    fun deleteAllCacheData()


}