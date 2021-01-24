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

    override fun getRemoteAllFtSalesh(authHeader: String ): Single<List<FtSalesh>> {
        return retrofitService.getRemoteAllFtSalesh(authHeader)
    }

    override fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSalesh> {
        return retrofitService.getRemoteFtSaleshById(authHeader, id)
    }

    override fun getRemoteAllFtSaleshByDivision(authHeader: String, divisionId: Int): Single<List<FtSalesh>> {
        return retrofitService.getRemoteAllFtSaleshByDivision(authHeader, divisionId)
    }

    override fun createRemoteFtSalesh(authHeader: String, ftSalesh: FtSalesh): Single<FtSalesh> {
        return retrofitService.createRemoteFtSalesh(authHeader, ftSalesh)
    }

    override fun putRemoteFtSalesh(authHeader: String, id: Long, ftSalesh: FtSalesh): Single<FtSalesh> {
        return retrofitService.putRemoteFtSalesh(authHeader, id, ftSalesh)
    }

    override fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSalesh> {
        return retrofitService.deleteRemoteFtSalesh(authHeader, id)
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
//        return retrofitService.getRemoteAllFtSalesh(authHeader)
//    }


}