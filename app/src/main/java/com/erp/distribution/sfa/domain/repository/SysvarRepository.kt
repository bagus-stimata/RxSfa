package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.SysvarEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface SysvarRepository {
    fun getRemoteAllSysvar(authHeader: String ): Single<List<SysvarEntity>>
    fun getRemoteSysvarById(authHeader: String, id: Int): Single<SysvarEntity>
    fun getRemoteAllSysvarByDivision(authHeader: String, divisionId: Int): Single<List<SysvarEntity>>
    fun getRemoteAllSysvarByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<SysvarEntity>>
    fun createRemoteSysvar(authHeader: String, sysvarEntity: SysvarEntity): Single<SysvarEntity>
    fun putRemoteSysvar(authHeader: String, id: Int, sysvarEntity: SysvarEntity): Single<SysvarEntity>
    fun deleteRemoteSysvar(authHeader: String, id: Int): Single<SysvarEntity>

    fun getCacheAllSysvar(): LiveData<List<SysvarEntity>>
    fun getCacheSysvarById(id: Int): LiveData<SysvarEntity>
    fun getCacheAllSysvarByDivision(divisionId: Int): LiveData<List<SysvarEntity>>
    fun addCacheSysvar(sysvarEntity: SysvarEntity)
    fun putCacheSysvar(sysvarEntity: SysvarEntity)
    fun deleteCacheSysvar(sysvarEntity: SysvarEntity)
    fun deleteAllCacheSysvar()


}