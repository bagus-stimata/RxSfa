package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup1Entity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialGroup1Repository {
    fun getRemoteAllFMaterialGroup1(authHeader: String, ): Single<List<FMaterialGroup1Entity>>
    fun getRemoteFMaterialGroup1ById(authHeader: String, id: Int): Single<FMaterialGroup1Entity>
    fun getRemoteAllFMaterialGroup1ByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialGroup1Entity>>
    fun getRemoteAllFMaterialGroup1ByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterialGroup1Entity>>
    fun createRemoteFMaterialGroup1(authHeader: String, fMaterialGroup1Entity: FMaterialGroup1Entity): Single<FMaterialGroup1Entity>
    fun putRemoteFMaterialGroup1(authHeader: String, id: Int, fMaterialGroup1Entity: FMaterialGroup1Entity): Single<FMaterialGroup1Entity>
    fun deleteRemoteFMaterialGroup1(authHeader: String, id: Int): Single<FMaterialGroup1Entity>

    fun getCacheAllFMaterialGroup1(): LiveData<List<FMaterialGroup1Entity>>
    fun getCacheFMaterialGroup1ById(id: Int): LiveData<FMaterialGroup1Entity>
    fun getCacheAllFMaterialGroup1ByDivision(divisionId: Int): LiveData<List<FMaterialGroup1Entity>>
    fun addCacheFMaterialGroup1(fMaterialGroup1Entity: FMaterialGroup1Entity)
    fun putCacheFMaterialGroup1(fMaterialGroup1Entity: FMaterialGroup1Entity)
    fun deleteCacheFMaterialGroup1(fMaterialGroup1Entity: FMaterialGroup1Entity)
    fun deleteAllCacheFMaterialGroup1()


}