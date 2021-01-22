package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFtSalesh
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FtSaleshRepository
import com.erp.distribution.sfa.model.FtSalesh
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FtSaleshRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFtSalesh
) : FtSaleshRepository {

    override fun getRemoteAllFtSalesh(): Single<List<FtSalesh>> {
        return retrofitService.getRemoteAllFtSalesh(MainApplication.authHeader)
    }

    override fun getRemoteFtSaleshById(id: Long): Single<FtSalesh> {
        return retrofitService.getRemoteFtSaleshById(MainApplication.authHeader, id)
    }

    override fun getRemoteAllFtSaleshByDivision(divisionId: Int): Single<List<FtSalesh>> {
        return retrofitService.getRemoteAllFtSaleshByDivision(MainApplication.authHeader, divisionId)
    }

    override fun createRemoteFtSalesh(ftSalesh: FtSalesh): Single<FtSalesh> {
        return retrofitService.createRemoteFtSalesh(MainApplication.authHeader, ftSalesh)
    }

    override fun putRemoteFtSalesh(id: Long, ftSalesh: FtSalesh): Single<FtSalesh> {
        return retrofitService.putRemoteFtSalesh(MainApplication.authHeader, id, ftSalesh)
    }

    override fun deleteRemoteFtSalesh(id: Long): Single<FtSalesh> {
        return retrofitService.deleteRemoteFtSalesh(MainApplication.authHeader, id)
    }



    override fun getCacheAllFtSalesh(): LiveData<List<FtSalesh>> {
        return appDatabase.saleshDao.getAllFtSaleshLive
    }

    override fun getCacheFtSaleshById(id: Long): LiveData<FtSalesh> {
        return appDatabase.saleshDao.getAllByIdLive(id)
    }

    override fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSalesh>> {
        return appDatabase.saleshDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFtSalesh(ftSalesh: FtSalesh) {
        return appDatabase.saleshDao.insert(ftSalesh)
    }

    override fun putCacheFtSalesh(ftSalesh: FtSalesh) {
        return appDatabase.saleshDao.update(ftSalesh)
    }

    override fun deleteCacheFtSalesh(ftSalesh: FtSalesh) {
        return appDatabase.saleshDao.delete(ftSalesh)
    }

    override fun deleteAllCacheData() {
        return appDatabase.saleshDao.deleteAllFtSalesh()
    }


//    override fun getRemoteAllData(): Single<List<FtSalesh>> {
//        return retrofitService.getRemoteAllFtSalesh(MainApplication.authHeader)
//    }


}