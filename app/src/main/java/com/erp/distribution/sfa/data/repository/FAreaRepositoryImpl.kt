package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFArea
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceSecurity
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FAreaRepository
import com.erp.distribution.sfa.domain.repository.UserRepository
import com.erp.distribution.sfa.model.FArea
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FAreaRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFArea
) : FAreaRepository {

    override fun getRemoteAllFArea(): Single<List<FArea>> {
        return retrofitService.getRemoteAllFArea(Constants.authHeader)
    }

    override fun getRemoteFAreaById(id: Int): Single<FArea> {
        return retrofitService.getRemoteFAreaById(Constants.authHeader, id)
    }

    override fun getRemoteAllFAreaByDivision(divisionId: Int): Single<List<FArea>> {
        return retrofitService.getRemoteAllFAreaByDivision(Constants.authHeader, divisionId)
    }

    override fun createRemoteFArea(fArea: FArea): Single<FArea> {
        return retrofitService.createRemoteFArea(Constants.authHeader, fArea)
    }

    override fun putRemoteFArea(id: Int, fArea: FArea): Single<FArea> {
        return retrofitService.putRemoteFArea(Constants.authHeader, id, fArea)
    }

    override fun deleteRemoteFArea(id: Int): Single<FArea> {
        return retrofitService.deleteRemoteFArea(Constants.authHeader, id)
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

    override fun putCacheFArea(fArea: FArea) {
        return appDatabase.areaDao.update(fArea)
    }

    override fun deleteCacheFArea(fArea: FArea) {
        return appDatabase.areaDao.delete(fArea)
    }

    override fun deleteAllCacheData() {
        return appDatabase.areaDao.deleteAllFArea()
    }


//    override fun getRemoteAllData(): Single<List<FArea>> {
//        return retrofitService.getRemoteAllFArea(Constants.authHeader)
//    }


}