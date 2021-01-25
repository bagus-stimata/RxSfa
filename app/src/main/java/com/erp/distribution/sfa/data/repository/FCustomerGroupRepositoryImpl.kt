package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFCustomerGroup
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerGroupRepository
import com.erp.distribution.sfa.data.source.entity.FCustomerGroup
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerGroupRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomerGroup
) : FCustomerGroupRepository {

    override fun getRemoteAllFCustomerGroup(authHeader: String): Single<List<FCustomerGroup>> {
        return retrofitService.getRemoteAllFCustomerGroup(authHeader)
    }

    override fun getRemoteFCustomerGroupById(authHeader: String, id: Int): Single<FCustomerGroup> {
        return retrofitService.getRemoteFCustomerGroupById(authHeader, id)
    }

    override fun getRemoteAllFCustomerGroupByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerGroup>> {
        return retrofitService.getRemoteAllFCustomerGroupByDivision(authHeader, divisionId)
    }

    override fun createRemoteFCustomerGroup(authHeader: String, fCustomerGroup: FCustomerGroup): Single<FCustomerGroup> {
        return retrofitService.createRemoteFCustomerGroup(authHeader, fCustomerGroup)
    }

    override fun putRemoteFCustomerGroup(authHeader: String, id: Int, fCustomerGroup: FCustomerGroup): Single<FCustomerGroup> {
        return retrofitService.putRemoteFCustomerGroup(authHeader, id, fCustomerGroup)
    }

    override fun deleteRemoteFCustomerGroup(authHeader: String, id: Int): Single<FCustomerGroup> {
        return retrofitService.deleteRemoteFCustomerGroup(authHeader, id)
    }



    override fun getCacheAllFCustomerGroup(): LiveData<List<FCustomerGroup>> {
        return appDatabase.customerGroupDao.getAllFCustomerGroupLive
    }

    override fun getCacheFCustomerGroupById(id: Int): LiveData<FCustomerGroup> {
        return appDatabase.customerGroupDao.getAllByIdLive(id)
    }

    override fun getCacheAllFCustomerGroupByDivision(divisionId: Int): LiveData<List<FCustomerGroup>> {
        return appDatabase.customerGroupDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFCustomerGroup(fCustomerGroup: FCustomerGroup) {
        return appDatabase.customerGroupDao.insert(fCustomerGroup)
    }

    override fun putCacheFCustomerGroup(fCustomerGroup: FCustomerGroup) {
        return appDatabase.customerGroupDao.update(fCustomerGroup)
    }

    override fun deleteCacheFCustomerGroup(fCustomerGroup: FCustomerGroup) {
        return appDatabase.customerGroupDao.delete(fCustomerGroup)
    }

    override fun deleteAllCacheData() {
        return appDatabase.customerGroupDao.deleteAllFCustomerGroup()
    }


//    override fun getRemoteAllData(): Single<List<FCustomerGroup>> {
//        return retrofitService.getRemoteAllFCustomerGroup(authHeader)
//    }


}