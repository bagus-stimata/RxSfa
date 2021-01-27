package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFCustomer
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.data.source.entity.FCustomer
import com.erp.distribution.sfa.data.source.entity.FMaterial
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomer
) : FCustomerRepository {

    override fun getRemoteAllFCustomer(authHeader: String): Single<List<FCustomer>> {
        return retrofitService.getRemoteAllFCustomer(authHeader)
    }

    override fun getRemoteFCustomerById(authHeader: String, id: Int): Single<FCustomer> {
        return retrofitService.getRemoteFCustomerById(authHeader, id)
    }

    override fun getRemoteAllFCustomerByDivision(authHeader: String, divisionId: Int): Single<List<FCustomer>> {
        return retrofitService.getRemoteAllFCustomerByDivision(authHeader, divisionId)
    }

    override fun createRemoteFCustomer(authHeader: String, fCustomer: FCustomer): Single<FCustomer> {
        return retrofitService.createRemoteFCustomer(authHeader, fCustomer)
    }

    override fun putRemoteFCustomer(authHeader: String, id: Int, fCustomer: FCustomer): Single<FCustomer> {
        return retrofitService.putRemoteFCustomer(authHeader, id, fCustomer)
    }

    override fun deleteRemoteFCustomer(authHeader: String, id: Int): Single<FCustomer> {
        return retrofitService.deleteRemoteFCustomer(authHeader, id)
    }



    override fun getCacheAllFCustomer(): LiveData<List<FCustomer>> {
        return appDatabase.customerDao.getAllFCustomerLive
    }
    override fun getCacheAllFCustomerFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FCustomer>> {
        return appDatabase.customerDao.getAllFCustomerFlow(query, sortOrder, hideSelected)
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


    override fun addCacheListFCustomer(list: List<FCustomer>) {
        return appDatabase.customerDao.insertAll(list)
    }

    override fun putCacheFCustomer(fCustomer: FCustomer) {
        return appDatabase.customerDao.update(fCustomer)
    }

    override fun deleteCacheFCustomer(fCustomer: FCustomer) {
        return appDatabase.customerDao.delete(fCustomer)
    }

    override fun deleteAllCacheFArea() {
        return appDatabase.customerDao.deleteAllFCustomer()
    }


}