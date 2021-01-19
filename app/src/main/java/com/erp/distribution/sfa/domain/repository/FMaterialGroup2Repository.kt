package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FMaterialGroup2
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialGroup2Repository {
    fun getRemoteAllFMaterialGroup2(): Single<List<FMaterialGroup2>>
    fun getRemoteFMaterialGroup2ById(id: Int): Single<FMaterialGroup2>
    fun getRemoteAllFMaterialGroup2ByParent(parentId: Int): Single<List<FMaterialGroup2>>
    fun createRemoteFMaterialGroup2(fMaterialGroup2: FMaterialGroup2): Single<FMaterialGroup2>
    fun putRemoteFMaterialGroup2(id: Int, fMaterialGroup2: FMaterialGroup2): Single<FMaterialGroup2>
    fun deleteRemoteFMaterialGroup2(id: Int): Single<FMaterialGroup2>

    fun getCacheAllFMaterialGroup2(): LiveData<List<FMaterialGroup2>>
    fun getCacheFMaterialGroup2ById(id: Int): LiveData<FMaterialGroup2>
    fun getCacheAllFMaterialGroup2ByParent(divisionId: Int): LiveData<List<FMaterialGroup2>>
    fun addCacheFMaterialGroup2(fMaterialGroup2: FMaterialGroup2)
    fun putCacheFMaterialGroup2(fMaterialGroup2: FMaterialGroup2)
    fun deleteCacheFMaterialGroup2(fMaterialGroup2: FMaterialGroup2)
    fun deleteAllCacheData()


}