package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceSysvar
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.SysvarRepository
import com.erp.distribution.sfa.data.source.entity.SysvarEntity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class SysvarRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceSysvar
) : SysvarRepository {

    override fun getRemoteAllSysvar(authHeader: String ): Single<List<SysvarEntity>> {
        return retrofitService.getRemoteAllSysvar(authHeader)
    }

    override fun getRemoteSysvarById(authHeader: String, id: Int): Single<SysvarEntity> {
        return retrofitService.getRemoteSysvarById(authHeader, id)
    }

    override fun getRemoteAllSysvarByDivision(authHeader: String, divisionId: Int): Single<List<SysvarEntity>> {
        return retrofitService.getRemoteAllSysvarByDivision(authHeader, divisionId)
    }

    override fun getRemoteAllSysvarByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<SysvarEntity>> {
        return retrofitService.getRemoteAllSysvarByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun createRemoteSysvar(authHeader: String, sysvarEntity: SysvarEntity): Single<SysvarEntity> {
        return retrofitService.createRemoteSysvar(authHeader, sysvarEntity)
    }

    override fun putRemoteSysvar(authHeader: String, id: Int, sysvarEntity: SysvarEntity): Single<SysvarEntity> {
        return retrofitService.putRemoteSysvar(authHeader, id, sysvarEntity)
    }

    override fun deleteRemoteSysvar(authHeader: String, id: Int): Single<SysvarEntity> {
        return retrofitService.deleteRemoteSysvar(authHeader, id)
    }



    override fun getCacheAllSysvar(): LiveData<List<SysvarEntity>> {
        return appDatabase.sysvarDao.getAllSysvarEntityLive
    }

    override fun getCacheSysvarById(id: Int): LiveData<SysvarEntity> {
        return appDatabase.sysvarDao.getAllByIdLive(id)
    }

    override fun getCacheAllSysvarByDivision(divisionId: Int): LiveData<List<SysvarEntity>> {
        return appDatabase.sysvarDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheSysvar(sysvarEntity: SysvarEntity) {
        return appDatabase.sysvarDao.insert(sysvarEntity)
    }

    override fun putCacheSysvar(sysvarEntity: SysvarEntity) {
        return appDatabase.sysvarDao.update(sysvarEntity)
    }

    override fun deleteCacheSysvar(sysvarEntity: SysvarEntity) {
        return appDatabase.sysvarDao.delete(sysvarEntity)
    }
    override fun deleteCacheSysvarBySysvarId(sysvarId: String) {
        return appDatabase.sysvarDao.deleteBySysvarId(sysvarId)
    }

    override fun deleteAllCacheSysvar() {
        return appDatabase.sysvarDao.deleteAllSysvar()
    }


}