package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FtSalesdItemsRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFtSalesdItemsUseCase @Inject constructor(private val repository: FtSalesdItemsRepository) : SingleUseCase<List<FtSalesdItemsEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FtSalesdItemsEntity>> {
        return repository.getRemoteAllFtSalesdItems("authHeader")
    }
    fun getRemoteAllFtSalesdItems(authHeader: String): Single<List<FtSalesdItemsEntity>>{
        return repository.getRemoteAllFtSalesdItems(authHeader)
    }

    fun getRemoteFtSalesdItemsById(authHeader: String, id: Long): Single<FtSalesdItemsEntity>{
        return repository.getRemoteFtSalesdItemsById(authHeader, id)
    }
//    fun getRemoteAllFtSalesdItemsByDivision(authHeader: String, divisionId: Int): Single<List<FtSalesdItems>>{
//        return repository.getRemoteAllFtSalesdItemsByDivision(authHeader, divisionId)
//    }
    fun createRemoteFtSalesdItems(authHeader: String, ftSaleshEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>{
        return repository.createRemoteFtSalesdItems(authHeader, ftSaleshEntity)
    }
    fun putRemoteFtSalesdItems(authHeader: String, id: Long, ftSaleshEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>{
        return repository.putRemoteFtSalesdItems(authHeader, id, ftSaleshEntity)
    }
    fun deleteRemoteFtSalesdItems(authHeader: String, id: Long): Single<FtSalesdItemsEntity>{
        return repository.deleteRemoteFtSalesdItems(authHeader, id)
    }



    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItemsEntity>>{
        return repository.getCacheAllFtSalesdItems()
    }
    fun getAllByFtSaleshAndMaterialFlow(ftSalesBean: Long, fmaterialBean: Int): Flow<List<FtSalesdItemsEntity>> {
        return repository.getAllByFtSalesdItemsByParentAndMaterialFlow(ftSalesBean, fmaterialBean)
    }
    fun getCacheListFtSalesdItemsByFtSaleshBean(ftSalesBean: Long): LiveData<List<FtSalesdItemsEntity>>{
        return repository.getCacheAllFtSalesdItemsByParent(ftSalesBean)
    }

    fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItemsEntity>{
        return repository.getCacheFtSalesdItemsById(id)
    }
    fun addCacheFtSalesdItems(ftSaleshEntity: FtSalesdItemsEntity){
        repository.addCacheFtSalesdItems(ftSaleshEntity)
    }
    fun addCacheListFtSalesdItems(list: List<FtSalesdItemsEntity>){
        repository.addCacheListFtSalesdItems(list)
    }
    fun putCacheFtSalesdItems(ftSaleshEntity: FtSalesdItemsEntity){
        repository.putCacheFtSalesdItems(ftSaleshEntity)
    }
    fun deleteCacheFtSalesdItems(ftSaleshEntity: FtSalesdItemsEntity){
        repository.deleteCacheFtSalesdItems(ftSaleshEntity)
    }
    fun deleteAllCacheFtSalesdItemsByFtSalesh(ftSaleshEntity: FtSaleshEntity){
        repository.deleteAllCacheFtSalesdItemsByFtSalesh(ftSaleshEntity.refno)
    }


}