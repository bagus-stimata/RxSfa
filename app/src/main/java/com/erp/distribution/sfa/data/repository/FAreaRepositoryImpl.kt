package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFArea
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FAreaRepository
import com.erp.distribution.sfa.data.source.entity.FArea
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FAreaRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFArea
) : FAreaRepository {

    override fun getRemoteAllFArea(authHeader: String): Single<List<FArea>> {
        return retrofitService.getRemoteAllFArea(authHeader)
    }

    override fun getRemoteFAreaById(authHeader: String, id: Int): Single<FArea> {
        return retrofitService.getRemoteFAreaById(authHeader, id)
    }

    override fun getRemoteAllFAreaByDivision(authHeader: String, divisionId: Int): Single<List<FArea>> {
        return retrofitService.getRemoteAllFAreaByDivision(authHeader, divisionId)
    }

    override fun createRemoteFArea(authHeader: String, fArea: FArea): Single<FArea> {
        return retrofitService.createRemoteFArea(authHeader, fArea)
    }

    override fun putRemoteFArea(authHeader: String, id: Int, fArea: FArea): Single<FArea> {
        return retrofitService.putRemoteFArea(authHeader, id, fArea)
    }

    override fun deleteRemoteFArea(authHeader: String, id: Int): Single<FArea> {
        return retrofitService.deleteRemoteFArea(authHeader, id)
    }



    override fun getCacheAllFArea(): LiveData<List<FArea>> {
        return appDatabase.areaDao.getAllFAreaLive
    }

    override fun getCacheFAreaById(id: Int): LiveData<FArea> {
        return appDatabase.areaDao.getAllByIdLive(id)
    }

    override fun getCacheAllFAreaByDivision(divisionId: Int): LiveData<List<FArea>> {
        return appDatabase.areaDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFArea(fArea: FArea) {
        return appDatabase.areaDao.insert(fArea)
    }
    override fun addCacheListFArea(list: List<FArea>) {
        return appDatabase.areaDao.insertAll(list)
    }

    override fun putCacheFArea(fArea: FArea) {
        return appDatabase.areaDao.update(fArea)
    }

    override fun deleteCacheFArea(fArea: FArea) {
        return appDatabase.areaDao.delete(fArea)
    }

    override fun deleteAllCacheFArea() {
        return appDatabase.areaDao.deleteAllFArea()
    }


}