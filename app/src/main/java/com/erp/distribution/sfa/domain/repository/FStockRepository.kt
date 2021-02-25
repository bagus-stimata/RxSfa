package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FStockEntity
import io.reactivex.rxjava3.core.Single
import java.util.*

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FStockRepository {
    fun getRemoteAllFStock(authHeader: String): Single<List<FStockEntity>>
    fun getRemoteFStockById(authHeader: String, id: Int): Single<FStockEntity>
    fun getRemoteAllFStockByMaterial(authHeader: String, materialId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStockEntity>>

    fun getRemoteAllFStockByWarehouse(authHeader: String, fwarehouseBean: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStockEntity>>
    fun getRemoteAllFStockByWarehouseOnly(authHeader: String, fwarehouseBean: Int): Single<List<FStockEntity>>

    fun createRemoteFStock(authHeader: String, fStockEntity: FStockEntity): Single<FStockEntity>
    fun putRemoteFStock(authHeader: String, id: Int, fStockEntity: FStockEntity): Single<FStockEntity>
    fun deleteRemoteFStock(authHeader: String, id: Int): Single<FStockEntity>

    fun getCacheAllFStock(): LiveData<List<FStockEntity>>
    fun getCacheFStockById(id: Int): LiveData<FStockEntity>
    fun getCacheAllFStockByMaterial(materialId: Int): LiveData<List<FStockEntity>>
    fun getCacheAllFStockByWarehouse(warehouseId: Int): LiveData<List<FStockEntity>>
    fun getCacheAllFStockByMaterial(materialId: Int, stockDate: Date): LiveData<List<FStockEntity>>
    fun getCacheAllFStockByWarehouse(warehouseId: Int, stockDate: Date): LiveData<List<FStockEntity>>
    fun addCacheFStock(fStockEntity: FStockEntity)
    fun addCacheListFStock(list: List<FStockEntity>)
    fun putCacheFStock(fStockEntity: FStockEntity)
    fun deleteCacheFStock(fStockEntity: FStockEntity)
    fun deleteAllCacheFStock()


}