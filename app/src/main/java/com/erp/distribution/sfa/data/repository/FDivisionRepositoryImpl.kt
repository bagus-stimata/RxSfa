package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFDivision
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FDivisionRepository
import com.erp.distribution.sfa.data.source.entity.FDivision
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FDivisionRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFDivision
) : FDivisionRepository {

    override fun getRemoteAllFDivision(authHeader: String, ): Single<List<FDivision>> {
        return retrofitService.getRemoteAllFDivision(authHeader)
    }

    override fun getRemoteFDivisionById(authHeader: String, id: Int): Single<FDivision> {
        return retrofitService.getRemoteFDivisionById(authHeader, id)
    }

    override fun getRemoteAllFDivisionByParent(authHeader: String, parentId: Int): Single<List<FDivision>> {
        return retrofitService.getRemoteAllFDivisionByParent(authHeader, parentId)
    }

    override fun createRemoteFDivision(authHeader: String, fDivision: FDivision): Single<FDivision> {
        return retrofitService.createRemoteFDivision(authHeader, fDivision)
    }

    override fun putRemoteFDivision(authHeader: String, id: Int, fDivision: FDivision): Single<FDivision> {
        return retrofitService.putRemoteFDivision(authHeader, id, fDivision)
    }

    override fun deleteRemoteFDivision(authHeader: String, id: Int): Single<FDivision> {
        return retrofitService.deleteRemoteFDivision(authHeader, id)
    }



    override fun getCacheAllFDivision(): LiveData<List<FDivision>> {
        return appDatabase.divisionDao.getAllFDivisionLive
    }

    override fun getCacheFDivisionById(id: Int): LiveData<FDivision> {
        return appDatabase.divisionDao.getAllByIdLive(id)
    }

    override fun getCacheAllFDivisionByParent(divisionId: Int): LiveData<List<FDivision>> {
        return appDatabase.divisionDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFDivision(fDivision: FDivision) {
        return appDatabase.divisionDao.insert(fDivision)
    }
    override fun addCacheListFDivision(list: List<FDivision>) {
        return appDatabase.divisionDao.insertAll(list)
    }

    override fun putCacheFDivision(fDivision: FDivision) {
        return appDatabase.divisionDao.update(fDivision)
    }

    override fun deleteCacheFDivision(fDivision: FDivision) {
        return appDatabase.divisionDao.delete(fDivision)
    }

    override fun deleteAllCacheFDivision() {
        return appDatabase.divisionDao.deleteAllFDivision()
    }


//    override fun getRemoteAllData(): Single<List<FDivision>> {
//        return retrofitService.getRemoteAllFDivision(authHeader)
//    }


}