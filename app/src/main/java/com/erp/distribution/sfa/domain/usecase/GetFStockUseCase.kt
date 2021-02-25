package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.erp.distribution.sfa.domain.repository.FStockRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FStockEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FStock
import com.erp.distribution.sfa.domain.model.toEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFStockUseCase @Inject constructor(private val repository: FStockRepository) : SingleUseCase<List<FStockEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FStockEntity>> {
        return repository.getRemoteAllFStock("aa")
    }

    fun getRemoteAllFStockByWarehouseOnly(authHeader: String, fwarehouseBean: Int): Single<List<FStock>>{
        return repository.getRemoteAllFStockByWarehouseOnly(authHeader, fwarehouseBean).map {
            it.map {
                it.toDomain()
            }
        }
    }


    fun getCacheAllFStock(): LiveData<List<FStock>>{
        return repository.getCacheAllFStock().map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheAllFStockLive(): LiveData<List<FStock>>{
        return repository.getCacheAllFStock().map {
            it.map {
                it.toDomain()
            }
        }
    }
    fun getCacheFStockById(id: Int): LiveData<FStock>{
        return repository.getCacheFStockById(id).map {
            it.toDomain()
        }
    }
    fun addCacheFStock(fStock: FStock){
        repository.addCacheFStock(fStock.toEntity())
    }
    fun addCacheListFStock(list: List<FStock>){
        repository.addCacheListFStock(list.map {
            it.toEntity()
        })
    }
    fun putCacheFStock(fStock: FStock){
        repository.putCacheFStock(fStock.toEntity())
    }
    fun deleteCacheFStock(fStock: FStock){
        repository.deleteCacheFStock(fStock.toEntity())
    }
    fun deleteAllCacheFStock(){
        repository.deleteAllCacheFStock()
    }
}