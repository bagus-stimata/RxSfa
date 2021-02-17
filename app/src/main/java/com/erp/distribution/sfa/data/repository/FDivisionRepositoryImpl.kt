package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFDivision
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FDivisionRepository
import com.erp.distribution.sfa.data.source.entity.FDivisionEntity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FDivisionRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFDivision
) : FDivisionRepository {

    override fun getRemoteAllFDivision(authHeader: String, ): Single<List<FDivisionEntity>> {
        return retrofitService.getRemoteAllFDivision(authHeader)
    }

    override fun getRemoteFDivisionById(authHeader: String, id: Int): Single<FDivisionEntity> {
        return retrofitService.getRemoteFDivisionById(authHeader, id)
    }

    override fun getRemoteAllFDivisionByParent(authHeader: String, parentId: Int): Single<List<FDivisionEntity>> {
        return retrofitService.getRemoteAllFDivisionByParent(authHeader, parentId)
    }
    override fun getAllFDivisionBySameCompany(authHeader: String, divisionId: Int): Single<List<FDivisionEntity>> {
        return retrofitService.getAllFDivisionBySameCompany(authHeader, divisionId)
    }

    override fun createRemoteFDivision(authHeader: String, fDivisionEntity: FDivisionEntity): Single<FDivisionEntity> {
        return retrofitService.createRemoteFDivision(authHeader, fDivisionEntity)
    }

    override fun putRemoteFDivision(authHeader: String, id: Int, fDivisionEntity: FDivisionEntity): Single<FDivisionEntity> {
        return retrofitService.putRemoteFDivision(authHeader, id, fDivisionEntity)
    }

    override fun deleteRemoteFDivision(authHeader: String, id: Int): Single<FDivisionEntity> {
        return retrofitService.deleteRemoteFDivision(authHeader, id)
    }



    override fun getCacheAllFDivision(): LiveData<List<FDivisionEntity>> {
        return appDatabase.divisionDao.getAllFDivisionEntityLive
    }

    override fun getCacheFDivisionById(id: Int): LiveData<FDivisionEntity> {
        return appDatabase.divisionDao.getAllByIdLive(id)
    }

    override fun getCacheAllFDivisionByParent(divisionId: Int): LiveData<List<FDivisionEntity>> {
        return appDatabase.divisionDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFDivision(fDivisionEntity: FDivisionEntity) {
        return appDatabase.divisionDao.insert(fDivisionEntity)
    }
    override fun addCacheListFDivision(list: List<FDivisionEntity>) {
        return appDatabase.divisionDao.insertAll(list)
    }

    override fun putCacheFDivision(fDivisionEntity: FDivisionEntity) {
        return appDatabase.divisionDao.update(fDivisionEntity)
    }

    override fun deleteCacheFDivision(fDivisionEntity: FDivisionEntity) {
        return appDatabase.divisionDao.delete(fDivisionEntity)
    }

    override fun deleteAllCacheFDivision() {
        return appDatabase.divisionDao.deleteAllFDivision()
    }


//    override fun getRemoteAllData(): Single<List<FDivision>> {
//        return retrofitService.getRemoteAllFDivision(authHeader)
//    }


}