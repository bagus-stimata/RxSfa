package com.erp.distribution.sfa.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.repository.FtSalesdItemsRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtSalesdItems
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFtSalesdItemsUseCase @Inject constructor(private val repository: FtSalesdItemsRepository) : SingleUseCase<List<FtSalesdItems>>() {

    override fun buildUseCaseSingle(): Single<List<FtSalesdItems>> {
        return repository.getRemoteAllFtSalesdItems("authHeader")
    }
    fun getRemoteAllFtSalesdItems(authHeader: String): Single<List<FtSalesdItems>>{
        return repository.getRemoteAllFtSalesdItems(authHeader)
    }

    fun getRemoteFtSalesdItemsById(authHeader: String, id: Long): Single<FtSalesdItems>{
        return repository.getRemoteFtSalesdItemsById(authHeader, id)
    }
//    fun getRemoteAllFtSalesdItemsByDivision(authHeader: String, divisionId: Int): Single<List<FtSalesdItems>>{
//        return repository.getRemoteAllFtSalesdItemsByDivision(authHeader, divisionId)
//    }
    fun createRemoteFtSalesdItems(authHeader: String, ftSalesh: FtSalesdItems): Single<FtSalesdItems>{
        return repository.createRemoteFtSalesdItems(authHeader, ftSalesh)
    }
    fun putRemoteFtSalesdItems(authHeader: String, id: Long, ftSalesh: FtSalesdItems): Single<FtSalesdItems>{
        return repository.putRemoteFtSalesdItems(authHeader, id, ftSalesh)
    }
    fun deleteRemoteFtSalesdItems(authHeader: String, id: Long): Single<FtSalesdItems>{
        return repository.deleteRemoteFtSalesdItems(authHeader, id)
    }



    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItems>>{
        return repository.getCacheAllFtSalesdItems()
    }
    fun getAllByFtSaleshAndMaterialFlow(ftSalesBean: Long, fmaterialBean: Int): Flow<List<FtSalesdItems>> {
        return repository.getAllByFtSaleshAndMaterialFlow(ftSalesBean, fmaterialBean)
    }
    fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItems>{
        return repository.getCacheFtSalesdItemsById(id)
    }
//    fun getCacheAllFtSalesdItemsByDivision(divisionId: Int): LiveData<List<FtSalesdItems>>{
//        return repository.getCacheAllFtSalesdItemsByDivision(divisionId)
//    }
    fun addCacheFtSalesdItems(ftSalesh: FtSalesdItems){
        repository.addCacheFtSalesdItems(ftSalesh)
    }
    fun addCacheListFtSalesdItems(list: List<FtSalesdItems>){
        repository.addCacheListFtSalesdItems(list)
    }
    fun putCacheFtSalesdItems(ftSalesh: FtSalesdItems){
        repository.putCacheFtSalesdItems(ftSalesh)
    }
    fun deleteCacheFtSalesdItems(ftSalesh: FtSalesdItems){
        repository.deleteCacheFtSalesdItems(ftSalesh)
    }
}