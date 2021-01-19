package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceSysvar
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.SysvarRepository
import com.erp.distribution.sfa.model.Sysvar
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class SysvarRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceSysvar
) : SysvarRepository {

    override fun getRemoteAllSysvar(): Single<List<Sysvar>> {
        return retrofitService.getRemoteAllSysvar(Constants.authHeader)
    }

    override fun getRemoteSysvarById(id: Int): Single<Sysvar> {
        return retrofitService.getRemoteSysvarById(Constants.authHeader, id)
    }

    override fun getRemoteAllSysvarByDivision(divisionId: Int): Single<List<Sysvar>> {
        return retrofitService.getRemoteAllSysvarByDivision(Constants.authHeader, divisionId)
    }

    override fun createRemoteSysvar(sysvar: Sysvar): Single<Sysvar> {
        return retrofitService.createRemoteSysvar(Constants.authHeader, sysvar)
    }

    override fun putRemoteSysvar(id: Int, sysvar: Sysvar): Single<Sysvar> {
        return retrofitService.putRemoteSysvar(Constants.authHeader, id, sysvar)
    }

    override fun deleteRemoteSysvar(id: Int): Single<Sysvar> {
        return retrofitService.deleteRemoteSysvar(Constants.authHeader, id)
    }



    override fun getCacheAllSysvar(): LiveData<List<Sysvar>> {
        return appDatabase.sysvarDao.getAllSysvarLive
    }

    override fun getCacheSysvarById(id: Int): LiveData<Sysvar> {
        return appDatabase.sysvarDao.getAllByIdLive(id)
    }

    override fun getCacheAllSysvarByDivision(divisionId: Int): LiveData<List<Sysvar>> {
        return appDatabase.sysvarDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheSysvar(sysvar: Sysvar) {
        return appDatabase.sysvarDao.insert(sysvar)
    }

    override fun putCacheSysvar(sysvar: Sysvar) {
        return appDatabase.sysvarDao.update(sysvar)
    }

    override fun deleteCacheSysvar(sysvar: Sysvar) {
        return appDatabase.sysvarDao.delete(sysvar)
    }

    override fun deleteAllCacheData() {
        return appDatabase.sysvarDao.deleteAllSysvar()
    }


//    override fun getRemoteAllData(): Single<List<Sysvar>> {
//        return retrofitService.getRemoteAllSysvar(Constants.authHeader)
//    }


}