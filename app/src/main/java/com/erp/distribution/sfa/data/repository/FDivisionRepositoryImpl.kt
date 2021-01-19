package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFDivision
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FDivisionRepository
import com.erp.distribution.sfa.model.FDivision
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FDivisionRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFDivision
) : FDivisionRepository {

    override fun getRemoteAllFDivision(): Single<List<FDivision>> {
        return retrofitService.getRemoteAllFDivision(Constants.authHeader)
    }

    override fun getRemoteFDivisionById(id: Int): Single<FDivision> {
        return retrofitService.getRemoteFDivisionById(Constants.authHeader, id)
    }

    override fun getRemoteAllFDivisionByParent(parentId: Int): Single<List<FDivision>> {
        return retrofitService.getRemoteAllFDivisionByParent(Constants.authHeader, parentId)
    }

    override fun createRemoteFDivision(fDivision: FDivision): Single<FDivision> {
        return retrofitService.createRemoteFDivision(Constants.authHeader, fDivision)
    }

    override fun putRemoteFDivision(id: Int, fDivision: FDivision): Single<FDivision> {
        return retrofitService.putRemoteFDivision(Constants.authHeader, id, fDivision)
    }

    override fun deleteRemoteFDivision(id: Int): Single<FDivision> {
        return retrofitService.deleteRemoteFDivision(Constants.authHeader, id)
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

    override fun putCacheFDivision(fDivision: FDivision) {
        return appDatabase.divisionDao.update(fDivision)
    }

    override fun deleteCacheFDivision(fDivision: FDivision) {
        return appDatabase.divisionDao.delete(fDivision)
    }

    override fun deleteAllCacheData() {
        return appDatabase.divisionDao.deleteAllFDivision()
    }


//    override fun getRemoteAllData(): Single<List<FDivision>> {
//        return retrofitService.getRemoteAllFDivision(Constants.authHeader)
//    }


}