package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFArea
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FAreaRepository
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FAreaRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFArea
) : FAreaRepository {

    override fun getRemoteAllFArea(authHeader: String): Single<List<FAreaEntity>> {
        return retrofitService.getRemoteAllFArea(authHeader)
    }

    override fun getRemoteFAreaById(authHeader: String, id: Int): Single<FAreaEntity> {
        return retrofitService.getRemoteFAreaById(authHeader, id)
    }

    override fun getRemoteAllFAreaByDivision(authHeader: String, divisionId: Int): Single<List<FAreaEntity>> {
        return retrofitService.getRemoteAllFAreaByDivision(authHeader, divisionId)
    }

    override fun createRemoteFArea(authHeader: String, fAreaEntity: FAreaEntity): Single<FAreaEntity> {
        return retrofitService.createRemoteFArea(authHeader, fAreaEntity)
    }

    override fun putRemoteFArea(authHeader: String, id: Int, fAreaEntity: FAreaEntity): Single<FAreaEntity> {
        return retrofitService.putRemoteFArea(authHeader, id, fAreaEntity)
    }

    override fun deleteRemoteFArea(authHeader: String, id: Int): Single<FAreaEntity> {
        return retrofitService.deleteRemoteFArea(authHeader, id)
    }



    override fun getCacheAllFArea(): LiveData<List<FAreaEntity>> {
        return appDatabase.areaDao.getAllFAreaEntityLive
    }

    override fun getCacheFAreaById(id: Int): LiveData<FAreaEntity> {
        return appDatabase.areaDao.getAllByIdLive(id)
    }

    override fun getCacheAllFAreaByDivision(divisionId: Int): LiveData<List<FAreaEntity>> {
        return appDatabase.areaDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFArea(fAreaEntity: FAreaEntity) {
        return appDatabase.areaDao.insert(fAreaEntity)
    }
    override fun addCacheListFArea(list: List<FAreaEntity>) {
        return appDatabase.areaDao.insertAll(list)
    }

    override fun putCacheFArea(fAreaEntity: FAreaEntity) {
        return appDatabase.areaDao.update(fAreaEntity)
    }

    override fun deleteCacheFArea(fAreaEntity: FAreaEntity) {
        return appDatabase.areaDao.delete(fAreaEntity)
    }

    override fun deleteAllCacheFArea() {
        return appDatabase.areaDao.deleteAllFArea()
    }


}