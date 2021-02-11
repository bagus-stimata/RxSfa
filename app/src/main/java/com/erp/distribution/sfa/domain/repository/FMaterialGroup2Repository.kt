package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup2Entity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialGroup2Repository {
    fun getRemoteAllFMaterialGroup2(authHeader: String): Single<List<FMaterialGroup2Entity>>
    fun getRemoteFMaterialGroup2ById(authHeader: String, id: Int): Single<FMaterialGroup2Entity>
    fun getRemoteAllFMaterialGroup2ByParent(authHeader: String, parentId: Int): Single<List<FMaterialGroup2Entity>>
    fun createRemoteFMaterialGroup2(authHeader: String, fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity>
    fun putRemoteFMaterialGroup2(authHeader: String, id: Int, fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity>
    fun deleteRemoteFMaterialGroup2(authHeader: String, id: Int): Single<FMaterialGroup2Entity>

    fun getCacheAllFMaterialGroup2(): LiveData<List<FMaterialGroup2Entity>>
    fun getCacheFMaterialGroup2ById(id: Int): LiveData<FMaterialGroup2Entity>
    fun getCacheAllFMaterialGroup2ByParent(divisionId: Int): LiveData<List<FMaterialGroup2Entity>>
    fun addCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity)
    fun putCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity)
    fun deleteCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity)
    fun deleteAllCacheFMaterialGroup2()


}