package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FSubArea
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSubAreaRepository {
    fun getRemoteAllFSubArea(): Single<List<FSubArea>>
    fun getRemoteFSubAreaById(id: Int): Single<FSubArea>
    fun getRemoteAllFSubAreaByParent(parentId: Int): Single<List<FSubArea>>
    fun createRemoteFSubArea(fSubArea: FSubArea): Single<FSubArea>
    fun putRemoteFSubArea(id: Int, fSubArea: FSubArea): Single<FSubArea>
    fun deleteRemoteFSubArea(id: Int): Single<FSubArea>

    fun getCacheAllFSubArea(): LiveData<List<FSubArea>>
    fun getCacheFSubAreaById(id: Int): LiveData<FSubArea>
    fun getCacheAllFSubAreaByParent(divisionId: Int): LiveData<List<FSubArea>>
    fun addCacheFSubArea(fSubArea: FSubArea)
    fun putCacheFSubArea(fSubArea: FSubArea)
    fun deleteCacheFSubArea(fSubArea: FSubArea)
    fun deleteAllCacheData()


}