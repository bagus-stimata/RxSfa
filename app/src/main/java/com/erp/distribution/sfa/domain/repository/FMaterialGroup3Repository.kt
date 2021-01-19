package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FMaterialGroup3
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialGroup3Repository {
    fun getRemoteAllFMaterialGroup3(): Single<List<FMaterialGroup3>>
    fun getRemoteFMaterialGroup3ById(id: Int): Single<FMaterialGroup3>
    fun getRemoteAllFMaterialGroup3ByParent(parentId: Int): Single<List<FMaterialGroup3>>
    fun createRemoteFMaterialGroup3(fMaterialGroup3: FMaterialGroup3): Single<FMaterialGroup3>
    fun putRemoteFMaterialGroup3(id: Int, fMaterialGroup3: FMaterialGroup3): Single<FMaterialGroup3>
    fun deleteRemoteFMaterialGroup3(id: Int): Single<FMaterialGroup3>

    fun getCacheAllFMaterialGroup3(): LiveData<List<FMaterialGroup3>>
    fun getCacheFMaterialGroup3ById(id: Int): LiveData<FMaterialGroup3>
    fun getCacheAllFMaterialGroup3ByParent(divisionId: Int): LiveData<List<FMaterialGroup3>>
    fun addCacheFMaterialGroup3(fMaterialGroup3: FMaterialGroup3)
    fun putCacheFMaterialGroup3(fMaterialGroup3: FMaterialGroup3)
    fun deleteCacheFMaterialGroup3(fMaterialGroup3: FMaterialGroup3)
    fun deleteAllCacheData()


}