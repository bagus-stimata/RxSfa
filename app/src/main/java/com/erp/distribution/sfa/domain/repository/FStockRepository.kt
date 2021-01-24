package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FStock
import io.reactivex.Single
import java.util.*

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FStockRepository {
    fun getRemoteAllFStock(authHeader: String): Single<List<FStock>>
    fun getRemoteFStockById(authHeader: String, id: Int): Single<FStock>
    fun getRemoteAllFStockByMaterial(authHeader: String, materialId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>>
    fun getRemoteAllFStockByWarehouse(authHeader: String, warehouseId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>>
    fun createRemoteFStock(authHeader: String, fStock: FStock): Single<FStock>
    fun putRemoteFStock(authHeader: String, id: Int, fStock: FStock): Single<FStock>
    fun deleteRemoteFStock(authHeader: String, id: Int): Single<FStock>

    fun getCacheAllFStock(): LiveData<List<FStock>>
    fun getCacheFStockById(id: Int): LiveData<FStock>
    fun getCacheAllFStockByMaterial(materialId: Int): LiveData<List<FStock>>
    fun getCacheAllFStockByWarehouse(warehouseId: Int): LiveData<List<FStock>>
    fun getCacheAllFStockByMaterial(materialId: Int, stockDate: Date): LiveData<List<FStock>>
    fun getCacheAllFStockByWarehouse(warehouseId: Int, stockDate: Date): LiveData<List<FStock>>
    fun addCacheFStock(fStock: FStock)
    fun addCacheListFStock(list: List<FStock>)
    fun putCacheFStock(fStock: FStock)
    fun deleteCacheFStock(fStock: FStock)
    fun deleteAllCacheData()


}