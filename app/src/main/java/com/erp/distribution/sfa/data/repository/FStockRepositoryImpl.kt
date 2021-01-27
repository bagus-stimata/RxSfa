package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFStock
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FStockRepository
import com.erp.distribution.sfa.data.source.entity.FStockEntity
import io.reactivex.Single
import java.util.*


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FStockRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFStock
) : FStockRepository {

    override fun getRemoteAllFStock(authHeader: String, ): Single<List<FStockEntity>> {
        return retrofitService.getRemoteAllFStock(authHeader)
    }

    override fun getRemoteFStockById(authHeader: String, id: Int): Single<FStockEntity> {
        return retrofitService.getRemoteFStockById(authHeader, id)
    }

    override fun getRemoteAllFStockByMaterial(authHeader: String, materialId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStockEntity>> {
        return retrofitService.getRemoteAllFStockByFMaterial(authHeader, materialId, stockDateFrom, stockDateTo)
    }
    override fun getRemoteAllFStockByWarehouse(authHeader: String, warehouseId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStockEntity>> {
        return retrofitService.getRemoteAllFStockByFWarehouse(authHeader, warehouseId, stockDateFrom, stockDateTo)
    }

    override fun createRemoteFStock(authHeader: String, fStockEntity: FStockEntity): Single<FStockEntity> {
        return retrofitService.createRemoteFStock(authHeader, fStockEntity)
    }

    override fun putRemoteFStock(authHeader: String, id: Int, fStockEntity: FStockEntity): Single<FStockEntity> {
        return retrofitService.putRemoteFStock(authHeader, id, fStockEntity)
    }

    override fun deleteRemoteFStock(authHeader: String, id: Int): Single<FStockEntity> {
        return retrofitService.deleteRemoteFStock(authHeader, id)
    }



    override fun getCacheAllFStock(): LiveData<List<FStockEntity>> {
        return appDatabase.stockDao.getAllFStockEntityLive
    }

    override fun getCacheFStockById(id: Int): LiveData<FStockEntity> {
        return appDatabase.stockDao.getAllByIdLive(id)
    }

    override fun getCacheAllFStockByMaterial(materialId: Int): LiveData<List<FStockEntity>> {
        return appDatabase.stockDao.getAllByMaterialLive(materialId)
    }
    override fun getCacheAllFStockByWarehouse(warehouseId: Int): LiveData<List<FStockEntity>> {
        return appDatabase.stockDao.getAllByWarehouseLive(warehouseId)
    }
    override fun getCacheAllFStockByMaterial(materialId: Int, stockDate: Date): LiveData<List<FStockEntity>> {
        return appDatabase.stockDao.getAllByMaterialLive(materialId, stockDate)
    }
    override fun getCacheAllFStockByWarehouse(warehouseId: Int, stockDate: Date): LiveData<List<FStockEntity>> {
        return appDatabase.stockDao.getAllByWarehouseLive(warehouseId, stockDate)
    }

    override fun addCacheFStock(fStockEntity: FStockEntity) {
        return appDatabase.stockDao.insert(fStockEntity)
    }
    override fun addCacheListFStock(list: List<FStockEntity>) {
        return appDatabase.stockDao.insertAll(list)
    }

    override fun putCacheFStock(fStockEntity: FStockEntity) {
        return appDatabase.stockDao.update(fStockEntity)
    }

    override fun deleteCacheFStock(fStockEntity: FStockEntity) {
        return appDatabase.stockDao.delete(fStockEntity)
    }

    override fun deleteAllCacheData() {
        return appDatabase.stockDao.deleteAllFStock()
    }


//    override fun getRemoteAllData(): Single<List<FStock>> {
//        return retrofitService.getRemoteAllFStock(authHeader)
//    }


}