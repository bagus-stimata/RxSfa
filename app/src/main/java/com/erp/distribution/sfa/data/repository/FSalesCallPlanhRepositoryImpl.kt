package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFSalesCallPlanh
import com.erp.distribution.sfa.domain.repository.FSalesCallPlanhRepository
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FSalesCallPlanhRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFSalesCallPlanh
) : FSalesCallPlanhRepository {

    override fun getRemoteAllFSalesCallPlanhByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FSalesCallPlanhEntity>> {
        return retrofitService.getAllFsalesCallPlanhByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun getRemoteAllFSalesCallPlanhBySalesman(authHeader: String, fsalesmanBean: Int): Single<List<FSalesCallPlanhEntity>> {
        return retrofitService.getAllFSalesCallPlanhBySalesman(authHeader, fsalesmanBean)
    }

    override fun getCacheAllFSalesCallPlanh(): LiveData<List<FSalesCallPlanhEntity>> {
        return appDatabase.fSalesCallPlanhDao.getAllFSalesCallPlanhLive
    }

    override fun getCacheAllFSalesCallPlanhByDivision(fdivisionBean: Int): LiveData<List<FSalesCallPlanhEntity>> {
        return appDatabase.fSalesCallPlanhDao.getAllByDivisionLive(fdivisionBean)
    }

    override fun addCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity) {
        return appDatabase.fSalesCallPlanhDao.insert(fSalesCallPlanhEntity)
    }
    override fun addCacheListFSalesCallPlanh(list: List<FSalesCallPlanhEntity>) {
        return appDatabase.fSalesCallPlanhDao.insertAll(list)
    }

    override fun putCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity) {
        return appDatabase.fSalesCallPlanhDao.update(fSalesCallPlanhEntity)
    }

    override fun deleteCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity) {
        return appDatabase.fSalesCallPlanhDao.delete(fSalesCallPlanhEntity)
    }

    override fun deleteAllCacheFSalesCallPlanh() {
        return appDatabase.fSalesCallPlanhDao.deleteAllFSalesCallPlanh()
    }


}