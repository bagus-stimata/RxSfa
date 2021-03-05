package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FtPriceAltdItemsRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FtPriceAltdItems
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFtPriceAltdItemsUseCase @Inject constructor(private val repository: FtPriceAltdItemsRepository) : SingleUseCase<List<FtPriceAltdItemsEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FtPriceAltdItemsEntity>> {
        return repository.getRemoteAllFtPriceAltdItemsByFtPriceAlth("aa", 9999)
    }
    fun getRemoteAllFtPriceAltdItemsByFtPriceAlth(authHeader: String, ftPriceAlthBean: Int): Single<List<FtPriceAltdItemsEntity>>{
        return repository.getRemoteAllFtPriceAltdItemsByFtPriceAlth(authHeader, ftPriceAlthBean)
    }


    fun getCacheAllFtPriceAltdItems(): LiveData<List<FtPriceAltdItemsEntity>>{
        return repository.getCacheAllFtPriceAltdItems()
    }
    fun getCacheAllFtPriceAltdItemsDomainLive(): LiveData<List<FtPriceAltdItems>>{
        return repository.getCacheAllFtPriceAltdItems().map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFtPriceAltdItemsByParent( ftPriceAlthBean: Int): LiveData<List<FtPriceAltdItems>>{
        return repository.getCacheAllFtPriceAltdItemsByParent(ftPriceAlthBean).map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFtPriceAltdItemsByFtPriceAlthAndFMaterial( ftPriceAlthBean: Int, fMaterialBean: Int ): LiveData<List<FtPriceAltdItems>>{
        return repository.getCacheAllFtPriceAltdItemsByFtPriceAlthAndFMaterial(ftPriceAlthBean, fMaterialBean).map {
            it.map {
                it.toDomain()
            }
        }
    }

    fun addCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity){
        repository.addCacheFtPriceAltdItems(ftPriceAltdItemsEntity)
    }
    fun addCacheListFtPriceAltdItems(list: List<FtPriceAltdItemsEntity>){
        repository.addCacheListFtPriceAltdItems(list)
    }
    fun putCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity){
        repository.putCacheFtPriceAltdItems(ftPriceAltdItemsEntity)
    }
    fun deleteCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity){
        repository.deleteCacheFtPriceAltdItems(ftPriceAltdItemsEntity)
    }
    fun deleteAllCacheFtPriceAltdItems(){
        repository.deleteAllCacheFtPriceAltdItems()
    }
}