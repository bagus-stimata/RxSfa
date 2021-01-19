package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FtSalesh
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtSaleshRepository {
    fun getRemoteAllFtSalesh(): Single<List<FtSalesh>>
    fun getRemoteFtSaleshById(id: Long): Single<FtSalesh>
    fun getRemoteAllFtSaleshByDivision(divisionId: Int): Single<List<FtSalesh>>
    fun createRemoteFtSalesh(ftSalesh: FtSalesh): Single<FtSalesh>
    fun putRemoteFtSalesh(id: Long, ftSalesh: FtSalesh): Single<FtSalesh>
    fun deleteRemoteFtSalesh(id: Long): Single<FtSalesh>

    fun getCacheAllFtSalesh(): LiveData<List<FtSalesh>>
    fun getCacheFtSaleshById(id: Long): LiveData<FtSalesh>
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSalesh>>
    fun addCacheFtSalesh(ftSalesh: FtSalesh)
    fun putCacheFtSalesh(ftSalesh: FtSalesh)
    fun deleteCacheFtSalesh(ftSalesh: FtSalesh)
    fun deleteAllCacheData()


}