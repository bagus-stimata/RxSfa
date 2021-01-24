package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFSubArea
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FSubAreaRepository
import com.erp.distribution.sfa.model.FSubArea
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FSubAreaRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFSubArea
) : FSubAreaRepository {

    override fun getRemoteAllFSubArea(authHeader: String): Single<List<FSubArea>> {
        return retrofitService.getRemoteAllFSubArea(authHeader)
    }

    override fun getRemoteFSubAreaById(authHeader: String, id: Int): Single<FSubArea> {
        return retrofitService.getRemoteFSubAreaById(authHeader, id)
    }

    override fun getRemoteAllFSubAreaByParent(authHeader: String, parentId: Int): Single<List<FSubArea>> {
        return retrofitService.getRemoteAllFSubAreaByParent(authHeader, parentId)
    }

    override fun createRemoteFSubArea(authHeader: String, fSubArea: FSubArea): Single<FSubArea> {
        return retrofitService.createRemoteFSubArea(authHeader, fSubArea)
    }

    override fun putRemoteFSubArea(authHeader: String, id: Int, fSubArea: FSubArea): Single<FSubArea> {
        return retrofitService.putRemoteFSubArea(authHeader, id, fSubArea)
    }

    override fun deleteRemoteFSubArea(authHeader: String, id: Int): Single<FSubArea> {
        return retrofitService.deleteRemoteFSubArea(authHeader, id)
    }



    override fun getCacheAllFSubArea(): LiveData<List<FSubArea>> {
        return appDatabase.subAreaDao.getAllFSubAreaLive
    }

    override fun getCacheFSubAreaById(id: Int): LiveData<FSubArea> {
        return appDatabase.subAreaDao.getAllByIdLive(id)
    }

    override fun getCacheAllFSubAreaByParent(divisionId: Int): LiveData<List<FSubArea>> {
        return appDatabase.subAreaDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFSubArea(fSubArea: FSubArea) {
        return appDatabase.subAreaDao.insert(fSubArea)
    }

    override fun putCacheFSubArea(fSubArea: FSubArea) {
        return appDatabase.subAreaDao.update(fSubArea)
    }

    override fun deleteCacheFSubArea(fSubArea: FSubArea) {
        return appDatabase.subAreaDao.delete(fSubArea)
    }

    override fun deleteAllCacheData() {
        return appDatabase.subAreaDao.deleteAllFSubArea()
    }


//    override fun getRemoteAllData(): Single<List<FSubArea>> {
//        return retrofitService.getRemoteAllFSubArea(authHeader)
//    }


}