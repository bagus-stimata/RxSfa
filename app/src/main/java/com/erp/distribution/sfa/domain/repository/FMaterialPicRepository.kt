package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FMaterialPic
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialPicRepository {
    fun getRemoteAllFMaterialPic(): Single<List<FMaterialPic>>
    fun getRemoteFMaterialPicById(id: Int): Single<FMaterialPic>
    fun getRemoteAllFMaterialPicByParent(parentId: Int): Single<List<FMaterialPic>>
    fun createRemoteFMaterialPic(fMaterialPic: FMaterialPic): Single<FMaterialPic>
    fun putRemoteFMaterialPic(id: Int, fMaterialPic: FMaterialPic): Single<FMaterialPic>
    fun deleteRemoteFMaterialPic(id: Int): Single<FMaterialPic>

    fun getCacheAllFMaterialPic(): LiveData<List<FMaterialPic>>
    fun getCacheFMaterialPicById(id: Int): LiveData<FMaterialPic>
    fun getCacheAllFMaterialPicByParent(divisionId: Int): LiveData<List<FMaterialPic>>
    fun addCacheFMaterialPic(fMaterialPic: FMaterialPic)
    fun putCacheFMaterialPic(fMaterialPic: FMaterialPic)
    fun deleteCacheFMaterialPic(fMaterialPic: FMaterialPic)
    fun deleteAllCacheData()


}