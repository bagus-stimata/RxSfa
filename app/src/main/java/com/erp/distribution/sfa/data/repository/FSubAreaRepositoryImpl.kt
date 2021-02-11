package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFSubArea
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FSubAreaRepository
import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FSubAreaRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFSubArea
) : FSubAreaRepository {

    override fun getRemoteAllFSubArea(authHeader: String): Single<List<FSubAreaEntity>> {
        return retrofitService.getRemoteAllFSubArea(authHeader)
    }

    override fun getRemoteFSubAreaById(authHeader: String, id: Int): Single<FSubAreaEntity> {
        return retrofitService.getRemoteFSubAreaById(authHeader, id)
    }

    override fun getRemoteAllFSubAreaByParent(authHeader: String, parentId: Int): Single<List<FSubAreaEntity>> {
        return retrofitService.getRemoteAllFSubAreaByParent(authHeader, parentId)
    }

    override fun createRemoteFSubArea(authHeader: String, fSubAreaEntity: FSubAreaEntity): Single<FSubAreaEntity> {
        return retrofitService.createRemoteFSubArea(authHeader, fSubAreaEntity)
    }

    override fun putRemoteFSubArea(authHeader: String, id: Int, fSubAreaEntity: FSubAreaEntity): Single<FSubAreaEntity> {
        return retrofitService.putRemoteFSubArea(authHeader, id, fSubAreaEntity)
    }

    override fun deleteRemoteFSubArea(authHeader: String, id: Int): Single<FSubAreaEntity> {
        return retrofitService.deleteRemoteFSubArea(authHeader, id)
    }



    override fun getCacheAllFSubArea(): LiveData<List<FSubAreaEntity>> {
        return appDatabase.subAreaDao.getAllFSubAreaEntityLive
    }

    override fun getCacheFSubAreaById(id: Int): LiveData<FSubAreaEntity> {
        return appDatabase.subAreaDao.getAllByIdLive(id)
    }

    override fun getCacheAllFSubAreaByParent(divisionId: Int): LiveData<List<FSubAreaEntity>> {
        return appDatabase.subAreaDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFSubArea(fSubAreaEntity: FSubAreaEntity) {
        return appDatabase.subAreaDao.insert(fSubAreaEntity)
    }
    override fun addCacheListFSubArea(list: List<FSubAreaEntity>) {
        return appDatabase.subAreaDao.insertAll(list)
    }

    override fun putCacheFSubArea(fSubAreaEntity: FSubAreaEntity) {
        return appDatabase.subAreaDao.update(fSubAreaEntity)
    }

    override fun deleteCacheFSubArea(fSubAreaEntity: FSubAreaEntity) {
        return appDatabase.subAreaDao.delete(fSubAreaEntity)
    }

    override fun deleteAllCacheFSubArea() {
        return appDatabase.subAreaDao.deleteAllFSubArea()
    }


//    override fun getRemoteAllData(): Single<List<FSubArea>> {
//        return retrofitService.getRemoteAllFSubArea(authHeader)
//    }


}