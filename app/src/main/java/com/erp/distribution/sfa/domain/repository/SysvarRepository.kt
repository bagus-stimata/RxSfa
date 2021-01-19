package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.Sysvar
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface SysvarRepository {
    fun getRemoteAllSysvar(): Single<List<Sysvar>>
    fun getRemoteSysvarById(id: Int): Single<Sysvar>
    fun getRemoteAllSysvarByDivision(divisionId: Int): Single<List<Sysvar>>
    fun createRemoteSysvar(sysvar: Sysvar): Single<Sysvar>
    fun putRemoteSysvar(id: Int, sysvar: Sysvar): Single<Sysvar>
    fun deleteRemoteSysvar(id: Int): Single<Sysvar>

    fun getCacheAllSysvar(): LiveData<List<Sysvar>>
    fun getCacheSysvarById(id: Int): LiveData<Sysvar>
    fun getCacheAllSysvarByDivision(divisionId: Int): LiveData<List<Sysvar>>
    fun addCacheSysvar(sysvar: Sysvar)
    fun putCacheSysvar(sysvar: Sysvar)
    fun deleteCacheSysvar(sysvar: Sysvar)
    fun deleteAllCacheData()


}