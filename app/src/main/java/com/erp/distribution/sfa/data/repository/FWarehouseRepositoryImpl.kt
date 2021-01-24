package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFWarehouse
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FWarehouseRepository
import com.erp.distribution.sfa.model.FWarehouse
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FWarehouseRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFWarehouse
) : FWarehouseRepository {

    override fun getRemoteAllFWarehouse(authHeader: String ): Single<List<FWarehouse>> {
        return retrofitService.getRemoteAllFWarehouse(authHeader)
    }

    override fun getRemoteFWarehouseById(authHeader: String, id: Int): Single<FWarehouse> {
        return retrofitService.getRemoteFWarehouseById(authHeader, id)
    }

    override fun getRemoteAllFWarehouseByDivision(authHeader: String, divisionId: Int): Single<List<FWarehouse>> {
        return retrofitService.getRemoteAllFWarehouseByDivision(authHeader, divisionId)
    }

    override fun createRemoteFWarehouse(authHeader: String, fWarehouse: FWarehouse): Single<FWarehouse> {
        return retrofitService.createRemoteFWarehouse(authHeader, fWarehouse)
    }

    override fun putRemoteFWarehouse(authHeader: String, id: Int, fWarehouse: FWarehouse): Single<FWarehouse> {
        return retrofitService.putRemoteFWarehouse(authHeader, id, fWarehouse)
    }

    override fun deleteRemoteFWarehouse(authHeader: String, id: Int): Single<FWarehouse> {
        return retrofitService.deleteRemoteFWarehouse(authHeader, id)
    }



    override fun getCacheAllFWarehouse(): LiveData<List<FWarehouse>> {
        return appDatabase.warehouseDao.getAllFWarehouseLive
    }

    override fun getCacheFWarehouseById(id: Int): LiveData<FWarehouse> {
        return appDatabase.warehouseDao.getAllByIdLive(id)
    }

    override fun getCacheAllFWarehouseByDivision(divisionId: Int): LiveData<List<FWarehouse>> {
        return appDatabase.warehouseDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFWarehouse(fWarehouse: FWarehouse) {
        return appDatabase.warehouseDao.insert(fWarehouse)
    }
    override fun addCacheListFWarehouse(list: List<FWarehouse>) {
        return appDatabase.warehouseDao.insertAll(list)
    }

    override fun putCacheFWarehouse(fWarehouse: FWarehouse) {
        return appDatabase.warehouseDao.update(fWarehouse)
    }

    override fun deleteCacheFWarehouse(fWarehouse: FWarehouse) {
        return appDatabase.warehouseDao.delete(fWarehouse)
    }

    override fun deleteAllCacheFWarehouse() {
        return appDatabase.warehouseDao.deleteAllFWarehouse()
    }


//    override fun getRemoteAllData(): Single<List<FWarehouse>> {
//        return retrofitService.getRemoteAllFWarehouse(authHeader)
//    }


}