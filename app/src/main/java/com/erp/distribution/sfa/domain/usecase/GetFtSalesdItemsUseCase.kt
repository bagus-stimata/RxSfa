package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FtSalesdItemsRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.toEntity
import io.reactivex.rxjava3.core.Single
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
    fun getRemoteAllFtSalesdItems(authHeader: String): Single<List<FtSalesdItems>>{
        return repository.getRemoteAllFtSalesdItems(authHeader).map {
            it.map {
                it.toDomain()
            }
        }
    }

    fun getRemoteFtSalesdItemsById(authHeader: String, id: Long): Single<FtSalesdItems>{
        return repository.getRemoteFtSalesdItemsById(authHeader, id).map {
            it.toDomain()
        }
    }
//    fun getRemoteAllFtSalesdItemsByDivision(authHeader: String, divisionId: Int): Single<List<FtSalesdItems>>{
//        return repository.getRemoteAllFtSalesdItemsByDivision(authHeader, divisionId)
//    }
    fun createRemoteFtSalesdItems(authHeader: String, ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>{
        return repository.createRemoteFtSalesdItems(authHeader, ftSalesdItems.toEntity()).map {
            it.toDomain()
        }
    }
    fun putRemoteFtSalesdItems(authHeader: String, id: Long, ftSalesdItems: FtSalesdItems): Single<FtSalesdItems>{
        return repository.putRemoteFtSalesdItems(authHeader, id, ftSalesdItems.toEntity()).map {
            it.toDomain()
        }
    }
    fun deleteRemoteFtSalesdItems(authHeader: String, id: Long): Single<FtSalesdItems>{
        return repository.deleteRemoteFtSalesdItems(authHeader, id).map {
            it.toDomain()
        }
    }



    fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItemsEntity>>{
        return repository.getCacheAllFtSalesdItems()
    }


    fun getCacheListFtSalesdItemsByFtSaleshAndFMaterialLive(ftSalesBean: Long, fmaterialBean: Int): LiveData<List<FtSalesdItems>> {
        return repository.getCacheListFtSalesdItemsByFtSaleshAndMaterialLive(ftSalesBean, fmaterialBean).map {
            it.map {
                val ftSalesdItemsBean = it.ftSalesdItemsEntity.toDomain()
                it.fMaterialEntity?.let {
                    ftSalesdItemsBean.fmaterialBean = it.toDomain()
                }
                ftSalesdItemsBean
            }
        }
    }
    fun getCacheListFtSalesdItemsByFtSaleshLive(ftSalesBean: Long): LiveData<List<FtSalesdItems>> {
        return repository.getCacheListFtSalesdItemsFtSaleshLive(ftSalesBean).map {
            it?.map {
                val ftSalesdItemsBean = it.ftSalesdItemsEntity.toDomain()
                it.fMaterialEntity?.let {
                    ftSalesdItemsBean.fmaterialBean = it.toDomain()
                }
                ftSalesdItemsBean
            }
        }
    }
    fun getCacheListFtSalesdItemsByFMaterialLive(fmaterialBean: Int): LiveData<List<FtSalesdItems>> {
        return repository.getCacheListFtSalesdItemsByFtSalesdItemsByFMaterialLive(fmaterialBean).map {
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
    fun addCacheListFtSalesdItems(list: List<FtSalesdItems>){
        repository.addCacheListFtSalesdItems(list.map {
            it.toEntity()
        })
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