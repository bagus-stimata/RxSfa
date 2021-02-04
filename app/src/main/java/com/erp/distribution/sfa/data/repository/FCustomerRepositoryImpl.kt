package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFCustomer
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerRepository
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
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

    override fun getRemoteAllFCustomer(authHeader: String): Single<List<FCustomerEntity>> {
        return retrofitService.getRemoteAllFCustomer(authHeader)
    }

    override fun getRemoteFCustomerById(authHeader: String, id: Int): Single<FCustomerEntity> {
        return retrofitService.getRemoteFCustomerById(authHeader, id)
    }

    override fun getRemoteAllFCustomerByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerEntity>> {
        return retrofitService.getRemoteAllFCustomerByDivision(authHeader, divisionId)
    }
    override fun getRemoteAllFCustomerByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FCustomerEntity>> {
        return retrofitService.getRemoteAllFCustomerByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun createRemoteFCustomer(authHeader: String, fCustomerEntity: FCustomerEntity): Single<FCustomerEntity> {
        return retrofitService.createRemoteFCustomer(authHeader, fCustomerEntity)
    }

    override fun putRemoteFCustomer(authHeader: String, id: Int, fCustomerEntity: FCustomerEntity): Single<FCustomerEntity> {
        return retrofitService.putRemoteFCustomer(authHeader, id, fCustomerEntity)
    }

    override fun deleteRemoteFCustomer(authHeader: String, id: Int): Single<FCustomerEntity> {
        return retrofitService.deleteRemoteFCustomer(authHeader, id)
    }



    override fun getCacheAllFCustomer(): LiveData<List<FCustomerEntity>> {
        return appDatabase.customerDao.getAllFCustomerEntityLive
    }
    override fun getCacheAllFCustomerFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FCustomerEntity>> {
        return appDatabase.customerDao.getAllFCustomerFlow(query, sortOrder, hideSelected)
    }
    override fun getCacheFCustomerById(id: Int): LiveData<FCustomerEntity> {
        return appDatabase.customerDao.getAllByIdLive(id)
    }

    override fun getCacheAllFCustomerByDivision(divisionId: Int): LiveData<List<FCustomerEntity>> {
        return appDatabase.customerDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFCustomer(fCustomerEntity: FCustomerEntity) {
        return appDatabase.customerDao.insert(fCustomerEntity)
    }


    override fun addCacheListFCustomer(list: List<FCustomerEntity>) {
        return appDatabase.customerDao.insertAll(list)
    }

    override fun putCacheFCustomer(fCustomerEntity: FCustomerEntity) {
        return appDatabase.customerDao.update(fCustomerEntity)
    }

    override fun deleteCacheFCustomer(fCustomerEntity: FCustomerEntity) {
        return appDatabase.customerDao.delete(fCustomerEntity)
    }

    override fun deleteAllCacheFArea() {
        return appDatabase.customerDao.deleteAllFCustomer()
    }


}