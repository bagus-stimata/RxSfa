package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSubAreaRepository {
    fun getRemoteAllFSubArea(authHeader: String): Single<List<FSubAreaEntity>>
    fun getRemoteFSubAreaById(authHeader: String, id: Int): Single<FSubAreaEntity>
    fun getRemoteAllFSubAreaByParent(authHeader: String, parentId: Int): Single<List<FSubAreaEntity>>
    fun createRemoteFSubArea(authHeader: String, fSubAreaEntity: FSubAreaEntity): Single<FSubAreaEntity>
    fun putRemoteFSubArea(authHeader: String, id: Int, fSubAreaEntity: FSubAreaEntity): Single<FSubAreaEntity>
    fun deleteRemoteFSubArea(authHeader: String, id: Int): Single<FSubAreaEntity>

    fun getCacheAllFSubArea(): LiveData<List<FSubAreaEntity>>
    fun getCacheFSubAreaById(id: Int): LiveData<FSubAreaEntity>
    fun getCacheAllFSubAreaByParent(divisionId: Int): LiveData<List<FSubAreaEntity>>
    fun addCacheFSubArea(fSubAreaEntity: FSubAreaEntity)
    fun putCacheFSubArea(fSubAreaEntity: FSubAreaEntity)
    fun deleteCacheFSubArea(fSubAreaEntity: FSubAreaEntity)
    fun deleteAllCacheData()


}