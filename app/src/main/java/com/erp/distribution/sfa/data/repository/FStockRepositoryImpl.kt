package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFStock
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FStockRepository
import com.erp.distribution.sfa.model.FStock
import com.erp.distribution.sfa.utils.Constants
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

    override fun getRemoteAllFStock(authHeader: String, ): Single<List<FStock>> {
        return retrofitService.getRemoteAllFStock(authHeader)
    }

    override fun getRemoteFStockById(authHeader: String, id: Int): Single<FStock> {
        return retrofitService.getRemoteFStockById(authHeader, id)
    }

    override fun getRemoteAllFStockByMaterial(authHeader: String, materialId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>> {
        return retrofitService.getRemoteAllFStockByFMaterial(authHeader, materialId, stockDateFrom, stockDateTo)
    }
    override fun getRemoteAllFStockByWarehouse(authHeader: String, warehouseId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>> {
        return retrofitService.getRemoteAllFStockByFWarehouse(authHeader, warehouseId, stockDateFrom, stockDateTo)
    }

    override fun createRemoteFStock(authHeader: String, fStock: FStock): Single<FStock> {
        return retrofitService.createRemoteFStock(authHeader, fStock)
    }

    override fun putRemoteFStock(authHeader: String, id: Int, fStock: FStock): Single<FStock> {
        return retrofitService.putRemoteFStock(authHeader, id, fStock)
    }

    override fun deleteRemoteFStock(authHeader: String, id: Int): Single<FStock> {
        return retrofitService.deleteRemoteFStock(authHeader, id)
    }



    override fun getCacheAllFStock(): LiveData<List<FStock>> {
        return appDatabase.stockDao.getAllFStockLive
    }

    override fun getCacheFStockById(id: Int): LiveData<FStock> {
        return appDatabase.stockDao.getAllByIdLive(id)
    }

    override fun getCacheAllFStockByMaterial(materialId: Int): LiveData<List<FStock>> {
        return appDatabase.stockDao.getAllByMaterialLive(materialId)
    }
    override fun getCacheAllFStockByWarehouse(warehouseId: Int): LiveData<List<FStock>> {
        return appDatabase.stockDao.getAllByWarehouseLive(warehouseId)
    }
    override fun getCacheAllFStockByMaterial(materialId: Int, stockDate: Date): LiveData<List<FStock>> {
        return appDatabase.stockDao.getAllByMaterialLive(materialId, stockDate)
    }
    override fun getCacheAllFStockByWarehouse(warehouseId: Int, stockDate: Date): LiveData<List<FStock>> {
        return appDatabase.stockDao.getAllByWarehouseLive(warehouseId, stockDate)
    }

    override fun addCacheFStock(fStock: FStock) {
        return appDatabase.stockDao.insert(fStock)
    }
    override fun addCacheListFStock(list: List<FStock>) {
        return appDatabase.stockDao.insertAll(list)
    }

    override fun putCacheFStock(fStock: FStock) {
        return appDatabase.stockDao.update(fStock)
    }

    override fun deleteCacheFStock(fStock: FStock) {
        return appDatabase.stockDao.delete(fStock)
    }

    override fun deleteAllCacheData() {
        return appDatabase.stockDao.deleteAllFStock()
    }


//    override fun getRemoteAllData(): Single<List<FStock>> {
//        return retrofitService.getRemoteAllFStock(authHeader)
//    }


}