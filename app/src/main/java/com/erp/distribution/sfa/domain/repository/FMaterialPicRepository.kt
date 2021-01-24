package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FMaterialPic
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialPicRepository {
    fun getRemoteAllFMaterialPic(authHeader: String, ): Single<List<FMaterialPic>>
    fun getRemoteFMaterialPicById(authHeader: String, id: Int): Single<FMaterialPic>
    fun getRemoteAllFMaterialPicByParent(authHeader: String, parentId: Int): Single<List<FMaterialPic>>
    fun createRemoteFMaterialPic(authHeader: String, fMaterialPic: FMaterialPic): Single<FMaterialPic>
    fun putRemoteFMaterialPic(authHeader: String, id: Int, fMaterialPic: FMaterialPic): Single<FMaterialPic>
    fun deleteRemoteFMaterialPic(authHeader: String, id: Int): Single<FMaterialPic>

    fun getCacheAllFMaterialPic(): LiveData<List<FMaterialPic>>
    fun getCacheFMaterialPicById(id: Int): LiveData<FMaterialPic>
    fun getCacheAllFMaterialPicByParent(divisionId: Int): LiveData<List<FMaterialPic>>
    fun addCacheFMaterialPic(fMaterialPic: FMaterialPic)
    fun putCacheFMaterialPic(fMaterialPic: FMaterialPic)
    fun deleteCacheFMaterialPic(fMaterialPic: FMaterialPic)
    fun deleteAllCacheData()


}