package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFCustomer
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.model.FCustomer
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomer
) : FCustomerRepository {

    override fun getRemoteAllFCustomer(): Single<List<FCustomer>> {
        return retrofitService.getRemoteAllFCustomer(Constants.authHeader)
    }

    override fun getRemoteFCustomerById(id: Int): Single<FCustomer> {
        return retrofitService.getRemoteFCustomerById(Constants.authHeader, id)
    }

    override fun getRemoteAllFCustomerByDivision(divisionId: Int): Single<List<FCustomer>> {
        return retrofitService.getRemoteAllFCustomerByDivision(Constants.authHeader, divisionId)
    }

    override fun createRemoteFCustomer(fCustomer: FCustomer): Single<FCustomer> {
        return retrofitService.createRemoteFCustomer(Constants.authHeader, fCustomer)
    }

    override fun putRemoteFCustomer(id: Int, fCustomer: FCustomer): Single<FCustomer> {
        return retrofitService.putRemoteFCustomer(Constants.authHeader, id, fCustomer)
    }

    override fun deleteRemoteFCustomer(id: Int): Single<FCustomer> {
        return retrofitService.deleteRemoteFCustomer(Constants.authHeader, id)
    }



    override fun getCacheAllFCustomer(): LiveData<List<FCustomer>> {
        return appDatabase.customerDao.getAllFCustomerLive
    }

    override fun getCacheFCustomerById(id: Int): LiveData<FCustomer> {
        return appDatabase.customerDao.getAllByIdLive(id)
    }

    override fun getCacheAllFCustomerByDivision(divisionId: Int): LiveData<List<FCustomer>> {
        return appDatabase.customerDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFCustomer(fCustomer: FCustomer) {
        return appDatabase.customerDao.insert(fCustomer)
    }

    override fun putCacheFCustomer(fCustomer: FCustomer) {
        return appDatabase.customerDao.update(fCustomer)
    }

    override fun deleteCacheFCustomer(fCustomer: FCustomer) {
        return appDatabase.customerDao.delete(fCustomer)
    }

    override fun deleteAllCacheData() {
        return appDatabase.customerDao.deleteAllFCustomer()
    }


//    override fun getRemoteAllData(): Single<List<FCustomer>> {
//        return retrofitService.getRemoteAllFCustomer(Constants.authHeader)
//    }


}