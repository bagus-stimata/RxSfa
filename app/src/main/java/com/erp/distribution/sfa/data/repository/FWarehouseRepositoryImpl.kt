package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFWarehouse
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FWarehouseRepository
import com.erp.distribution.sfa.data.source.entity.FWarehouseEntity
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FWarehouseRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFWarehouse
) : FWarehouseRepository {

    override fun getRemoteAllFWarehouse(authHeader: String ): Single<List<FWarehouseEntity>> {
        return retrofitService.getRemoteAllFWarehouse(authHeader)
    }

    override fun getRemoteFWarehouseById(authHeader: String, id: Int): Single<FWarehouseEntity> {
        return retrofitService.getRemoteFWarehouseById(authHeader, id)
    }

    override fun getRemoteAllFWarehouseByDivision(authHeader: String, divisionId: Int): Single<List<FWarehouseEntity>> {
        return retrofitService.getRemoteAllFWarehouseByDivision(authHeader, divisionId)
    }

    override fun createRemoteFWarehouse(authHeader: String, fWarehouseEntity: FWarehouseEntity): Single<FWarehouseEntity> {
        return retrofitService.createRemoteFWarehouse(authHeader, fWarehouseEntity)
    }

    override fun putRemoteFWarehouse(authHeader: String, id: Int, fWarehouseEntity: FWarehouseEntity): Single<FWarehouseEntity> {
        return retrofitService.putRemoteFWarehouse(authHeader, id, fWarehouseEntity)
    }

    override fun deleteRemoteFWarehouse(authHeader: String, id: Int): Single<FWarehouseEntity> {
        return retrofitService.deleteRemoteFWarehouse(authHeader, id)
    }



    override fun getCacheAllFWarehouse(): LiveData<List<FWarehouseEntity>> {
        return appDatabase.warehouseDao.getAllFWarehouseEntityLive
    }

    override fun getCacheFWarehouseById(id: Int): LiveData<FWarehouseEntity> {
        return appDatabase.warehouseDao.getAllByIdLive(id)
    }

    override fun getCacheAllFWarehouseByDivision(divisionId: Int): LiveData<List<FWarehouseEntity>> {
        return appDatabase.warehouseDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFWarehouse(fWarehouseEntity: FWarehouseEntity) {
        return appDatabase.warehouseDao.insert(fWarehouseEntity)
    }
    override fun addCacheListFWarehouse(list: List<FWarehouseEntity>) {
        return appDatabase.warehouseDao.insertAll(list)
    }

    override fun putCacheFWarehouse(fWarehouseEntity: FWarehouseEntity) {
        return appDatabase.warehouseDao.update(fWarehouseEntity)
    }

    override fun deleteCacheFWarehouse(fWarehouseEntity: FWarehouseEntity) {
        return appDatabase.warehouseDao.delete(fWarehouseEntity)
    }

    override fun deleteAllCacheFWarehouse() {
        return appDatabase.warehouseDao.deleteAllFWarehouse()
    }


//    override fun getRemoteAllData(): Single<List<FWarehouse>> {
//        return retrofitService.getRemoteAllFWarehouse(authHeader)
//    }


}