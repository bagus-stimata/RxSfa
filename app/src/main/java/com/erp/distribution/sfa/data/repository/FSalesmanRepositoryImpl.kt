package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFSalesman
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FSalesmanRepository
import com.erp.distribution.sfa.data.source.entity.FSalesmanEntity
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FSalesmanRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFSalesman
) : FSalesmanRepository {

    override fun getRemoteAllFSalesman(authHeader: String ): Single<List<FSalesmanEntity>> {
        return retrofitService.getRemoteAllFSalesman(authHeader)
    }

    override fun getRemoteFSalesmanById(authHeader: String, id: Int): Single<FSalesmanEntity> {
        return retrofitService.getRemoteFSalesmanById(authHeader, id)
    }

    override fun getRemoteAllFSalesmanByDivision(authHeader: String, divisionId: Int): Single<List<FSalesmanEntity>> {
        return retrofitService.getRemoteAllFSalesmanByDivision(authHeader, divisionId)
    }

    override fun getRemoteAllFSalesmanByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FSalesmanEntity>> {
        return retrofitService.getRemoteAllFSalesmanByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun createRemoteFSalesman(authHeader: String, fSalesmanEntity: FSalesmanEntity): Single<FSalesmanEntity> {
        return retrofitService.createRemoteFSalesman(authHeader, fSalesmanEntity)
    }

    override fun putRemoteFSalesman(authHeader: String, id: Int, fSalesmanEntity: FSalesmanEntity): Single<FSalesmanEntity> {
        return retrofitService.putRemoteFSalesman(authHeader, id, fSalesmanEntity)
    }

    override fun deleteRemoteFSalesman(authHeader: String, id: Int): Single<FSalesmanEntity> {
        return retrofitService.deleteRemoteFSalesman(authHeader, id)
    }



    override fun getCacheAllFSalesman(): LiveData<List<FSalesmanEntity>> {
        return appDatabase.salesmanDao.getAllFSalesmanEnittyLive
    }

    override fun getCacheFSalesmanById(id: Int): LiveData<FSalesmanEntity> {
        return appDatabase.salesmanDao.getAllByIdLive(id)
    }

    override fun getCacheAllFSalesmanByDivision(divisionId: Int): LiveData<List<FSalesmanEntity>> {
        return appDatabase.salesmanDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFSalesman(fSalesmanEntity: FSalesmanEntity) {
        return appDatabase.salesmanDao.insert(fSalesmanEntity)
    }
    override fun addCacheListFSalesman(list: List<FSalesmanEntity>) {
        return appDatabase.salesmanDao.insertAll(list)
    }

    override fun putCacheFSalesman(fSalesmanEntity: FSalesmanEntity) {
        return appDatabase.salesmanDao.update(fSalesmanEntity)
    }

    override fun deleteCacheFSalesman(fSalesmanEntity: FSalesmanEntity) {
        return appDatabase.salesmanDao.delete(fSalesmanEntity)
    }

    override fun deleteAllCacheFSalesman() {
        return appDatabase.salesmanDao.deleteAllFSalesman()
    }



}