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

    override fun getRemoteAllFWarehouse(): Single<List<FWarehouse>> {
        return retrofitService.getRemoteAllFWarehouse(MainApplication.authHeader)
    }

    override fun getRemoteFWarehouseById(id: Int): Single<FWarehouse> {
        return retrofitService.getRemoteFWarehouseById(MainApplication.authHeader, id)
    }

    override fun getRemoteAllFWarehouseByDivision(divisionId: Int): Single<List<FWarehouse>> {
        return retrofitService.getRemoteAllFWarehouseByDivision(MainApplication.authHeader, divisionId)
    }

    override fun createRemoteFWarehouse(fWarehouse: FWarehouse): Single<FWarehouse> {
        return retrofitService.createRemoteFWarehouse(MainApplication.authHeader, fWarehouse)
    }

    override fun putRemoteFWarehouse(id: Int, fWarehouse: FWarehouse): Single<FWarehouse> {
        return retrofitService.putRemoteFWarehouse(MainApplication.authHeader, id, fWarehouse)
    }

    override fun deleteRemoteFWarehouse(id: Int): Single<FWarehouse> {
        return retrofitService.deleteRemoteFWarehouse(MainApplication.authHeader, id)
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

    override fun putCacheFWarehouse(fWarehouse: FWarehouse) {
        return appDatabase.warehouseDao.update(fWarehouse)
    }

    override fun deleteCacheFWarehouse(fWarehouse: FWarehouse) {
        return appDatabase.warehouseDao.delete(fWarehouse)
    }

    override fun deleteAllCacheData() {
        return appDatabase.warehouseDao.deleteAllFWarehouse()
    }


//    override fun getRemoteAllData(): Single<List<FWarehouse>> {
//        return retrofitService.getRemoteAllFWarehouse(MainApplication.authHeader)
//    }


}