package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFCustomerGroup
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerGroupRepository
import com.erp.distribution.sfa.data.source.entity.FCustomerGroupEntity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerGroupRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomerGroup
) : FCustomerGroupRepository {

    override fun getRemoteAllFCustomerGroup(authHeader: String): Single<List<FCustomerGroupEntity>> {
        return retrofitService.getRemoteAllFCustomerGroup(authHeader)
    }

    override fun getRemoteFCustomerGroupById(authHeader: String, id: Int): Single<FCustomerGroupEntity> {
        return retrofitService.getRemoteFCustomerGroupById(authHeader, id)
    }

    override fun getRemoteAllFCustomerGroupByDivision(authHeader: String, divisionId: Int): Single<List<FCustomerGroupEntity>> {
        return retrofitService.getRemoteAllFCustomerGroupByDivision(authHeader, divisionId)
    }

    override fun getRemoteAllFCustomerGroupByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FCustomerGroupEntity>> {
        return retrofitService.getRemoteAllFCustomerGroupByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun createRemoteFCustomerGroup(authHeader: String, fCustomerGroupEntity: FCustomerGroupEntity): Single<FCustomerGroupEntity> {
        return retrofitService.createRemoteFCustomerGroup(authHeader, fCustomerGroupEntity)
    }

    override fun putRemoteFCustomerGroup(authHeader: String, id: Int, fCustomerGroupEntity: FCustomerGroupEntity): Single<FCustomerGroupEntity> {
        return retrofitService.putRemoteFCustomerGroup(authHeader, id, fCustomerGroupEntity)
    }

    override fun deleteRemoteFCustomerGroup(authHeader: String, id: Int): Single<FCustomerGroupEntity> {
        return retrofitService.deleteRemoteFCustomerGroup(authHeader, id)
    }



    override fun getCacheAllFCustomerGroup(): LiveData<List<FCustomerGroupEntity>> {
        return appDatabase.customerGroupDao.getAllFCustomerGroupEntityLive
    }

    override fun getCacheFCustomerGroupById(id: Int): LiveData<FCustomerGroupEntity> {
        return appDatabase.customerGroupDao.getAllByIdLive(id)
    }

    override fun getCacheAllFCustomerGroupByDivision(divisionId: Int): LiveData<List<FCustomerGroupEntity>> {
        return appDatabase.customerGroupDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity) {
        return appDatabase.customerGroupDao.insert(fCustomerGroupEntity)
    }

    override fun addCacheListFCustomerGroup(list: List<FCustomerGroupEntity>) {
        return appDatabase.customerGroupDao.insertAll(list)
    }

    override fun putCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity) {
        return appDatabase.customerGroupDao.update(fCustomerGroupEntity)
    }

    override fun deleteCacheFCustomerGroup(fCustomerGroupEntity: FCustomerGroupEntity) {
        return appDatabase.customerGroupDao.delete(fCustomerGroupEntity)
    }

    override fun deleteAllCacheFCustomerGroup() {
        return appDatabase.customerGroupDao.deleteAllFCustomerGroup()
    }


}