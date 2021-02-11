package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FMaterialPicEntity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialPicRepository {
    fun getRemoteAllFMaterialPic(authHeader: String, ): Single<List<FMaterialPicEntity>>
    fun getRemoteFMaterialPicById(authHeader: String, id: Int): Single<FMaterialPicEntity>
    fun getRemoteAllFMaterialPicByParent(authHeader: String, parentId: Int): Single<List<FMaterialPicEntity>>
    fun createRemoteFMaterialPic(authHeader: String, fMaterialPicEntity: FMaterialPicEntity): Single<FMaterialPicEntity>
    fun putRemoteFMaterialPic(authHeader: String, id: Int, fMaterialPicEntity: FMaterialPicEntity): Single<FMaterialPicEntity>
    fun deleteRemoteFMaterialPic(authHeader: String, id: Int): Single<FMaterialPicEntity>

    fun getCacheAllFMaterialPic(): LiveData<List<FMaterialPicEntity>>
    fun getCacheFMaterialPicById(id: Int): LiveData<FMaterialPicEntity>
    fun getCacheAllFMaterialPicByParent(divisionId: Int): LiveData<List<FMaterialPicEntity>>
    fun addCacheFMaterialPic(fMaterialPicEntity: FMaterialPicEntity)
    fun putCacheFMaterialPic(fMaterialPicEntity: FMaterialPicEntity)
    fun deleteCacheFMaterialPic(fMaterialPicEntity: FMaterialPicEntity)
    fun deleteAllCacheData()


}