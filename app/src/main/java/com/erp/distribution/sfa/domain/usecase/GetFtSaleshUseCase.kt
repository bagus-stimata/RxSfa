package com.erp.distribution.sfa.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FtSaleshRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtSalesh
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFtSaleshUseCase @Inject constructor(private val repository: FtSaleshRepository) : SingleUseCase<List<FtSalesh>>() {

    override fun buildUseCaseSingle(): Single<List<FtSalesh>> {
        return repository.getRemoteAllFtSalesh("authHeader")
    }
    fun getRemoteAllFtSalesh(authHeader: String): Single<List<FtSalesh>>{
        return repository.getRemoteAllFtSalesh(authHeader)
    }

    fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSalesh>{
        return repository.getRemoteFtSaleshById(authHeader, id)
    }
    fun getRemoteAllFtSaleshByDivision(authHeader: String, divisionId: Int): Single<List<FtSalesh>>{
        return repository.getRemoteAllFtSaleshByDivision(authHeader, divisionId)
    }
    fun createRemoteFtSalesh(authHeader: String, ftSalesh: FtSalesh): Single<FtSalesh>{
        return repository.createRemoteFtSalesh(authHeader, ftSalesh)
    }
    fun putRemoteFtSalesh(authHeader: String, id: Long, ftSalesh: FtSalesh): Single<FtSalesh>{
        return repository.putRemoteFtSalesh(authHeader, id, ftSalesh)
    }
    fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSalesh>{
        return repository.deleteRemoteFtSalesh(authHeader, id)
    }



    fun getCacheAllFtSalesh(): LiveData<List<FtSalesh>>{
        return repository.getCacheAllFtSalesh()
    }
    fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FtSalesh>> {
        return repository.getCacheAllFtSaleshFlow(query, sortOrder, hideSelected)
    }
    fun getCacheFtSaleshById(id: Long): LiveData<FtSalesh>{
        return repository.getCacheFtSaleshById(id)
    }
    fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSalesh>>{
        return repository.getCacheAllFtSaleshByDivision(divisionId)
    }
    fun addCacheFtSalesh(ftSalesh: FtSalesh){
        repository.addCacheFtSalesh(ftSalesh)
    }
    fun addCacheListFtSalesh(list: List<FtSalesh>){
        repository.addCacheListFtSalesh(list)
    }
    fun putCacheFtSalesh(ftSalesh: FtSalesh){
        repository.putCacheFtSalesh(ftSalesh)
    }
    fun deleteCacheFtSalesh(ftSalesh: FtSalesh){
        repository.deleteCacheFtSalesh(ftSalesh)
    }
    fun deleteAllCacheFtSalesh(){
        repository.deleteAllCacheFtSalesh()
    }
}