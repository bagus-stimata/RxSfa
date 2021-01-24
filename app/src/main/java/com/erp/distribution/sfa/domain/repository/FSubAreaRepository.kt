package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FSubArea
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSubAreaRepository {
    fun getRemoteAllFSubArea(authHeader: String): Single<List<FSubArea>>
    fun getRemoteFSubAreaById(authHeader: String, id: Int): Single<FSubArea>
    fun getRemoteAllFSubAreaByParent(authHeader: String, parentId: Int): Single<List<FSubArea>>
    fun createRemoteFSubArea(authHeader: String, fSubArea: FSubArea): Single<FSubArea>
    fun putRemoteFSubArea(authHeader: String, id: Int, fSubArea: FSubArea): Single<FSubArea>
    fun deleteRemoteFSubArea(authHeader: String, id: Int): Single<FSubArea>

    fun getCacheAllFSubArea(): LiveData<List<FSubArea>>
    fun getCacheFSubAreaById(id: Int): LiveData<FSubArea>
    fun getCacheAllFSubAreaByParent(divisionId: Int): LiveData<List<FSubArea>>
    fun addCacheFSubArea(fSubArea: FSubArea)
    fun putCacheFSubArea(fSubArea: FSubArea)
    fun deleteCacheFSubArea(fSubArea: FSubArea)
    fun deleteAllCacheData()


}