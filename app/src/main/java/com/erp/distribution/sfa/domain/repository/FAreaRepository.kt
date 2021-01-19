package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.domain.model.DummyUser
import com.erp.distribution.sfa.model.FArea
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FAreaRepository {
    fun getRemoteAllFArea(): Single<List<FArea>>
    fun getRemoteFAreaById(id: Int): Single<FArea>
    fun getRemoteAllFAreaByDivision(divisionId: Int): Single<List<FArea>>
    fun createRemoteFArea(fArea: FArea): Single<FArea>
    fun putRemoteFArea(id: Int, fArea: FArea): Single<FArea>
    fun deleteRemoteFArea(id: Int): Single<FArea>

    fun getCacheAllFArea(): LiveData<List<FArea>>
    fun getCacheFAreaById(id: Int): LiveData<FArea>
    fun getCacheAllFAreaByDivision(divisionId: Int): LiveData<List<FArea>>
    fun addCacheFArea(fArea: FArea)
    fun putCacheFArea(fArea: FArea)
    fun deleteCacheFArea(fArea: FArea)
    fun deleteAllCacheData()


}