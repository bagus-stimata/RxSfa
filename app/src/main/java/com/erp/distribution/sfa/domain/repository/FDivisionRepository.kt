package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FDivisionEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FDivisionRepository {
    fun getRemoteAllFDivision(authHeader: String, ): Single<List<FDivisionEntity>>
    fun getRemoteFDivisionById(authHeader: String, id: Int): Single<FDivisionEntity>
    fun getRemoteAllFDivisionByParent(authHeader: String, parentId: Int): Single<List<FDivisionEntity>>
    fun createRemoteFDivision(authHeader: String, fDivisionEntity: FDivisionEntity): Single<FDivisionEntity>
    fun putRemoteFDivision(authHeader: String, id: Int, fDivisionEntity: FDivisionEntity): Single<FDivisionEntity>
    fun deleteRemoteFDivision(authHeader: String, id: Int): Single<FDivisionEntity>

    fun getCacheAllFDivision(): LiveData<List<FDivisionEntity>>
    fun getCacheFDivisionById(id: Int): LiveData<FDivisionEntity>
    fun getCacheAllFDivisionByParent(divisionId: Int): LiveData<List<FDivisionEntity>>
    fun addCacheFDivision(fDivisionEntity: FDivisionEntity)
    fun addCacheListFDivision(list: List<FDivisionEntity>)
    fun putCacheFDivision(fDivisionEntity: FDivisionEntity)
    fun deleteCacheFDivision(fDivisionEntity: FDivisionEntity)
    fun deleteAllCacheFDivision()


}