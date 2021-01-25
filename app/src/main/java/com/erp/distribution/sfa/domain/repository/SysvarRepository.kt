package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.Sysvar
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface SysvarRepository {
    fun getRemoteAllSysvar(authHeader: String ): Single<List<Sysvar>>
    fun getRemoteSysvarById(authHeader: String, id: Int): Single<Sysvar>
    fun getRemoteAllSysvarByDivision(authHeader: String, divisionId: Int): Single<List<Sysvar>>
    fun createRemoteSysvar(authHeader: String, sysvar: Sysvar): Single<Sysvar>
    fun putRemoteSysvar(authHeader: String, id: Int, sysvar: Sysvar): Single<Sysvar>
    fun deleteRemoteSysvar(authHeader: String, id: Int): Single<Sysvar>

    fun getCacheAllSysvar(): LiveData<List<Sysvar>>
    fun getCacheSysvarById(id: Int): LiveData<Sysvar>
    fun getCacheAllSysvarByDivision(divisionId: Int): LiveData<List<Sysvar>>
    fun addCacheSysvar(sysvar: Sysvar)
    fun putCacheSysvar(sysvar: Sysvar)
    fun deleteCacheSysvar(sysvar: Sysvar)
    fun deleteAllCacheSysvar()


}