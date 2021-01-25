package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFSalesman
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FSalesmanRepository
import com.erp.distribution.sfa.data.source.entity.FSalesman
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FSalesmanRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFSalesman
) : FSalesmanRepository {

    override fun getRemoteAllFSalesman(authHeader: String ): Single<List<FSalesman>> {
        return retrofitService.getRemoteAllFSalesman(authHeader)
    }

    override fun getRemoteFSalesmanById(authHeader: String, id: Int): Single<FSalesman> {
        return retrofitService.getRemoteFSalesmanById(authHeader, id)
    }

    override fun getRemoteAllFSalesmanByDivision(authHeader: String, divisionId: Int): Single<List<FSalesman>> {
        return retrofitService.getRemoteAllFSalesmanByDivision(authHeader, divisionId)
    }

    override fun createRemoteFSalesman(authHeader: String, fSalesman: FSalesman): Single<FSalesman> {
        return retrofitService.createRemoteFSalesman(authHeader, fSalesman)
    }

    override fun putRemoteFSalesman(authHeader: String, id: Int, fSalesman: FSalesman): Single<FSalesman> {
        return retrofitService.putRemoteFSalesman(authHeader, id, fSalesman)
    }

    override fun deleteRemoteFSalesman(authHeader: String, id: Int): Single<FSalesman> {
        return retrofitService.deleteRemoteFSalesman(authHeader, id)
    }



    override fun getCacheAllFSalesman(): LiveData<List<FSalesman>> {
        return appDatabase.salesmanDao.getAllFSalesmanLive
    }

    override fun getCacheFSalesmanById(id: Int): LiveData<FSalesman> {
        return appDatabase.salesmanDao.getAllByIdLive(id)
    }

    override fun getCacheAllFSalesmanByDivision(divisionId: Int): LiveData<List<FSalesman>> {
        return appDatabase.salesmanDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFSalesman(fSalesman: FSalesman) {
        return appDatabase.salesmanDao.insert(fSalesman)
    }
    override fun addCacheListFSalesman(list: List<FSalesman>) {
        return appDatabase.salesmanDao.insertAll(list)
    }

    override fun putCacheFSalesman(fSalesman: FSalesman) {
        return appDatabase.salesmanDao.update(fSalesman)
    }

    override fun deleteCacheFSalesman(fSalesman: FSalesman) {
        return appDatabase.salesmanDao.delete(fSalesman)
    }

    override fun deleteAllCacheFSalesman() {
        return appDatabase.salesmanDao.deleteAllFSalesman()
    }


//    override fun getRemoteAllData(): Single<List<FSalesman>> {
//        return retrofitService.getRemoteAllFSalesman(authHeader)
//    }


}