package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FtSalesdItemsRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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


    fun getCacheListFtSalesdItemsByFtSaleshAndFMaterialFlow(ftSalesBean: Long, fmaterialBean: Int): LiveData<List<FtSalesdItems>> {
        return repository.getCacheListFtSalesdItemsByFtSaleshAndMaterialFlow(ftSalesBean, fmaterialBean).map {
            it.map {
                val ftSalesdItemsBean = it.ftSalesdItemsEntity.toDomain()
                it.fMaterialEntity?.let {
                    ftSalesdItemsBean.fmaterialBean = it.toDomain()
                }
                ftSalesdItemsBean
            }
        }
    }
    fun getCacheListFtSalesdItemsByFtSaleshFlow(ftSalesBean: Long): LiveData<List<FtSalesdItems>> {
        return repository.getCacheListFtSalesdItemsFtSaleshFlow(ftSalesBean).map {
            it.map {
                val ftSalesdItemsBean = it.ftSalesdItemsEntity.toDomain()
                it.fMaterialEntity?.let {
                    ftSalesdItemsBean.fmaterialBean = it.toDomain()
                }
                ftSalesdItemsBean
            }
        }
    }
    fun getCacheListFtSalesdItemsByFMaterialFlow(fmaterialBean: Int): LiveData<List<FtSalesdItems>> {
        return repository.getCacheListFtSalesdItemsByFtSalesdItemsByFMaterialFlow(fmaterialBean).map {
            it.map {
                val ftSalesdItemsBean = it.ftSalesdItemsEntity.toDomain()
                it.fMaterialEntity?.let {
                    ftSalesdItemsBean.fmaterialBean = it.toDomain()
                }
                ftSalesdItemsBean
            }
        }
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