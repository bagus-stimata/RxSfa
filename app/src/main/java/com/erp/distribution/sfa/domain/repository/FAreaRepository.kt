package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FAreaRepository {
    fun getRemoteAllFArea(authHeader: String): Single<List<FAreaEntity>>
    fun getRemoteFAreaById(authHeader: String, id: Int): Single<FAreaEntity>
    fun getRemoteAllFAreaByDivision(authHeader: String, divisionId: Int): Single<List<FAreaEntity>>
    fun getRemoteAllFAreaByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FAreaEntity>>
    fun createRemoteFArea(authHeader: String, fAreaEntity: FAreaEntity): Single<FAreaEntity>
    fun putRemoteFArea(authHeader: String, id: Int, fAreaEntity: FAreaEntity): Single<FAreaEntity>
    fun deleteRemoteFArea(authHeader: String, id: Int): Single<FAreaEntity>

    fun getCacheAllFArea(): LiveData<List<FAreaEntity>>
    fun getCacheFAreaById(id: Int): LiveData<FAreaEntity>
    fun getCacheAllFAreaByDivision(divisionId: Int): LiveData<List<FAreaEntity>>
    fun addCacheFArea(fAreaEntity: FAreaEntity)
    fun addCacheListFArea(list: List<FAreaEntity>)
    fun putCacheFArea(fAreaEntity: FAreaEntity)
    fun deleteCacheFArea(fAreaEntity: FAreaEntity)
    fun deleteAllCacheFArea()


}