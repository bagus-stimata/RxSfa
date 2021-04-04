package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FSalesCallPlandItemsRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FSalesCallPlandItems
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFSalesCallPlandItemsUseCase @Inject constructor(private val repository: FSalesCallPlandItemsRepository) : SingleUseCase<List<FSalesCallPlandItemsEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FSalesCallPlandItemsEntity>> {
        return repository.getRemoteAllFSalesCallPlandItemsByParent("aa", 9999)
    }
    fun getRemoteAllFSalesCallPlandItemsByParent(authHeader: String, fSalesCallPlanhBean: Long): Single<List<FSalesCallPlandItemsEntity>>{
        return repository.getRemoteAllFSalesCallPlandItemsByParent(authHeader, fSalesCallPlanhBean)
    }


    fun getCacheAllFSalesCallPlandItems(): LiveData<List<FSalesCallPlandItemsEntity>>{
        return repository.getCacheAllFSalesCallPlandItems()
    }
    fun getCacheAllFSalesCallPlandItemsDomainLive(): LiveData<List<FSalesCallPlandItems>>{
        return repository.getCacheAllFSalesCallPlandItems().map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFSalesCallPlandItemsByParent( fSalesCallPlanhBean: Long): LiveData<List<FSalesCallPlandItems>>{
        return repository.getCacheAllFSalesCallPlandItemsByParent(fSalesCallPlanhBean).map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFSalesCallPlandItemsByFtPriceAlthAndFMaterial( fSalesCallPlanhBean: Long, fMaterialBean: Int ): LiveData<List<FSalesCallPlandItems>>{
        return repository.getCacheAllFSalesCallPlandItemsByFtPriceAlthAndFMaterial(fSalesCallPlanhBean, fMaterialBean).map {
            it.map {
                it.toDomain()
            }
        }
    }

    fun addCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity){
        repository.addCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity)
    }
    fun addCacheListFSalesCallPlandItems(list: List<FSalesCallPlandItemsEntity>){
        repository.addCacheListFSalesCallPlandItems(list)
    }
    fun putCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity){
        repository.putCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity)
    }
    fun deleteCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity){
        repository.deleteCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity)
    }
    fun deleteAllCacheFSalesCallPlandItems(){
        repository.deleteAllCacheFSalesCallPlandItems()
    }
}