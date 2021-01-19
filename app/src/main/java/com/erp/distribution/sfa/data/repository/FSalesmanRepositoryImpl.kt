package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFSalesman
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FSalesmanRepository
import com.erp.distribution.sfa.model.FSalesman
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FSalesmanRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFSalesman
) : FSalesmanRepository {

    override fun getRemoteAllFSalesman(): Single<List<FSalesman>> {
        return retrofitService.getRemoteAllFSalesman(Constants.authHeader)
    }

    override fun getRemoteFSalesmanById(id: Int): Single<FSalesman> {
        return retrofitService.getRemoteFSalesmanById(Constants.authHeader, id)
    }

    override fun getRemoteAllFSalesmanByDivision(divisionId: Int): Single<List<FSalesman>> {
        return retrofitService.getRemoteAllFSalesmanByDivision(Constants.authHeader, divisionId)
    }

    override fun createRemoteFSalesman(fSalesman: FSalesman): Single<FSalesman> {
        return retrofitService.createRemoteFSalesman(Constants.authHeader, fSalesman)
    }

    override fun putRemoteFSalesman(id: Int, fSalesman: FSalesman): Single<FSalesman> {
        return retrofitService.putRemoteFSalesman(Constants.authHeader, id, fSalesman)
    }

    override fun deleteRemoteFSalesman(id: Int): Single<FSalesman> {
        return retrofitService.deleteRemoteFSalesman(Constants.authHeader, id)
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

    override fun putCacheFSalesman(fSalesman: FSalesman) {
        return appDatabase.salesmanDao.update(fSalesman)
    }

    override fun deleteCacheFSalesman(fSalesman: FSalesman) {
        return appDatabase.salesmanDao.delete(fSalesman)
    }

    override fun deleteAllCacheData() {
        return appDatabase.salesmanDao.deleteAllFSalesman()
    }


//    override fun getRemoteAllData(): Single<List<FSalesman>> {
//        return retrofitService.getRemoteAllFSalesman(Constants.authHeader)
//    }


}