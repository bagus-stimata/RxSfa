package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
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

    override fun getRemoteAllFStock(): Single<List<FStock>> {
        return retrofitService.getRemoteAllFStock(Constants.authHeader)
    }

    override fun getRemoteFStockById(id: Int): Single<FStock> {
        return retrofitService.getRemoteFStockById(Constants.authHeader, id)
    }

    override fun getRemoteAllFStockByMaterial(materialId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>> {
        return retrofitService.getRemoteAllFStockByFMaterial(Constants.authHeader, materialId, stockDateFrom, stockDateTo)
    }
    override fun getRemoteAllFStockByWarehouse(warehouseId: Int, stockDateFrom: Date, stockDateTo: Date): Single<List<FStock>> {
        return retrofitService.getRemoteAllFStockByFWarehouse(Constants.authHeader, warehouseId, stockDateFrom, stockDateTo)
    }

    override fun createRemoteFStock(fStock: FStock): Single<FStock> {
        return retrofitService.createRemoteFStock(Constants.authHeader, fStock)
    }

    override fun putRemoteFStock(id: Int, fStock: FStock): Single<FStock> {
        return retrofitService.putRemoteFStock(Constants.authHeader, id, fStock)
    }

    override fun deleteRemoteFStock(id: Int): Single<FStock> {
        return retrofitService.deleteRemoteFStock(Constants.authHeader, id)
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
//        return retrofitService.getRemoteAllFStock(Constants.authHeader)
//    }


}