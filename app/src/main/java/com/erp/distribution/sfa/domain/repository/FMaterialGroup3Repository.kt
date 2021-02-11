package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.sfa.domain.model.FMaterialGroup3
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialGroup3Repository {
    fun getRemoteAllFMaterialGroup3(authHeader: String, ): Single<List<FMaterialGroup3Entity>>
    fun getRemoteFMaterialGroup3ById(authHeader: String, id: Int): Single<FMaterialGroup3Entity>
    fun getRemoteAllFMaterialGroup3ByParent(authHeader: String, parentId: Int): Single<List<FMaterialGroup3Entity>>
    fun createRemoteFMaterialGroup3(authHeader: String, fMaterialGroup3Entity: FMaterialGroup3Entity): Single<FMaterialGroup3Entity>
    fun putRemoteFMaterialGroup3(authHeader: String, id: Int, fMaterialGroup3Entity: FMaterialGroup3Entity): Single<FMaterialGroup3Entity>
    fun deleteRemoteFMaterialGroup3(authHeader: String, id: Int): Single<FMaterialGroup3Entity>

    fun getCacheAllFMaterialGroup3(): LiveData<List<FMaterialGroup3Entity>>
    fun getCacheAllFMaterialGroup3DomainFlow(): Flow<List<FMaterialGroup3>>
    fun getCacheFMaterialGroup3ById(id: Int): LiveData<FMaterialGroup3Entity>
    fun getCacheFMaterialGroup3ByIdDomainFlow(id: Int): Flow<FMaterialGroup3>
    fun getCacheAllFMaterialGroup3ByParent(divisionId: Int): LiveData<List<FMaterialGroup3Entity>>
    fun getCacheAllFMaterialGroup3ByParentDomainFlow(parentId: Int): Flow<List<FMaterialGroup3>>

    fun addCacheListFMaterialGroup3(list: List<FMaterialGroup3Entity>)

    fun addCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity)
    fun addCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3)
    fun putCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity)
    fun putCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3)
    fun deleteCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity)
    fun deleteCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3)
    fun deleteAllCacheFMaterialGroup3()


}