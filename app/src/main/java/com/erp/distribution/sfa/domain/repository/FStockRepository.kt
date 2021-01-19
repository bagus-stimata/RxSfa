package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FStock
import io.reactivex.Single
import java.util.*

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FStockRepository {
    fun getRemoteAllFStock(): Single<List<FStock>>
    fun getRemoteFStockById(id: Int): Single<FStock>
    fun getRemoteAllFStockByMaterial(materialId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>>
    fun getRemoteAllFStockByWarehouse(warehouseId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>>
    fun createRemoteFStock(fStock: FStock): Single<FStock>
    fun putRemoteFStock(id: Int, fStock: FStock): Single<FStock>
    fun deleteRemoteFStock(id: Int): Single<FStock>

    fun getCacheAllFStock(): LiveData<List<FStock>>
    fun getCacheFStockById(id: Int): LiveData<FStock>
    fun getCacheAllFStockByMaterial(materialId: Int): LiveData<List<FStock>>
    fun getCacheAllFStockByWarehouse(warehouseId: Int): LiveData<List<FStock>>
    fun getCacheAllFStockByMaterial(materialId: Int, stockDate: Date): LiveData<List<FStock>>
    fun getCacheAllFStockByWarehouse(warehouseId: Int, stockDate: Date): LiveData<List<FStock>>
    fun addCacheFStock(fStock: FStock)
    fun putCacheFStock(fStock: FStock)
    fun deleteCacheFStock(fStock: FStock)
    fun deleteAllCacheData()


}