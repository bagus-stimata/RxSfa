package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFCustomerGroup
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerGroupRepository
import com.erp.distribution.sfa.model.FCustomerGroup
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerGroupRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomerGroup
) : FCustomerGroupRepository {

    override fun getRemoteAllFCustomerGroup(): Single<List<FCustomerGroup>> {
        return retrofitService.getRemoteAllFCustomerGroup(Constants.authHeader)
    }

    override fun getRemoteFCustomerGroupById(id: Int): Single<FCustomerGroup> {
        return retrofitService.getRemoteFCustomerGroupById(Constants.authHeader, id)
    }

    override fun getRemoteAllFCustomerGroupByDivision(divisionId: Int): Single<List<FCustomerGroup>> {
        return retrofitService.getRemoteAllFCustomerGroupByDivision(Constants.authHeader, divisionId)
    }

    override fun createRemoteFCustomerGroup(fCustomerGroup: FCustomerGroup): Single<FCustomerGroup> {
        return retrofitService.createRemoteFCustomerGroup(Constants.authHeader, fCustomerGroup)
    }

    override fun putRemoteFCustomerGroup(id: Int, fCustomerGroup: FCustomerGroup): Single<FCustomerGroup> {
        return retrofitService.putRemoteFCustomerGroup(Constants.authHeader, id, fCustomerGroup)
    }

    override fun deleteRemoteFCustomerGroup(id: Int): Single<FCustomerGroup> {
        return retrofitService.deleteRemoteFCustomerGroup(Constants.authHeader, id)
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
//        return retrofitService.getRemoteAllFCustomerGroup(Constants.authHeader)
//    }


}