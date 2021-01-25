package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceSysvar
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.SysvarRepository
import com.erp.distribution.sfa.data.source.entity.Sysvar
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class SysvarRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceSysvar
) : SysvarRepository {

    override fun getRemoteAllSysvar(authHeader: String ): Single<List<Sysvar>> {
        return retrofitService.getRemoteAllSysvar(authHeader)
    }

    override fun getRemoteSysvarById(authHeader: String, id: Int): Single<Sysvar> {
        return retrofitService.getRemoteSysvarById(authHeader, id)
    }

    override fun getRemoteAllSysvarByDivision(authHeader: String, divisionId: Int): Single<List<Sysvar>> {
        return retrofitService.getRemoteAllSysvarByDivision(authHeader, divisionId)
    }

    override fun createRemoteSysvar(authHeader: String, sysvar: Sysvar): Single<Sysvar> {
        return retrofitService.createRemoteSysvar(authHeader, sysvar)
    }

    override fun putRemoteSysvar(authHeader: String, id: Int, sysvar: Sysvar): Single<Sysvar> {
        return retrofitService.putRemoteSysvar(authHeader, id, sysvar)
    }

    override fun deleteRemoteSysvar(authHeader: String, id: Int): Single<Sysvar> {
        return retrofitService.deleteRemoteSysvar(authHeader, id)
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

    override fun deleteAllCacheSysvar() {
        return appDatabase.sysvarDao.deleteAllSysvar()
    }


//    override fun getRemoteAllData(): Single<List<Sysvar>> {
//        return retrofitService.getRemoteAllSysvar(authHeader)
//    }


}