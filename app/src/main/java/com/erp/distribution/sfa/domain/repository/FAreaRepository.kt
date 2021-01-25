package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FArea
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FAreaRepository {
    fun getRemoteAllFArea(authHeader: String): Single<List<FArea>>
    fun getRemoteFAreaById(authHeader: String, id: Int): Single<FArea>
    fun getRemoteAllFAreaByDivision(authHeader: String, divisionId: Int): Single<List<FArea>>
    fun createRemoteFArea(authHeader: String, fArea: FArea): Single<FArea>
    fun putRemoteFArea(authHeader: String, id: Int, fArea: FArea): Single<FArea>
    fun deleteRemoteFArea(authHeader: String, id: Int): Single<FArea>

    fun getCacheAllFArea(): LiveData<List<FArea>>
    fun getCacheFAreaById(id: Int): LiveData<FArea>
    fun getCacheAllFAreaByDivision(divisionId: Int): LiveData<List<FArea>>
    fun addCacheFArea(fArea: FArea)
    fun addCacheListFArea(list: List<FArea>)
    fun putCacheFArea(fArea: FArea)
    fun deleteCacheFArea(fArea: FArea)
    fun deleteAllCacheFArea()


}