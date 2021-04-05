package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsWithHeader
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFSalesCallPlandItems
import com.erp.distribution.sfa.domain.repository.FSalesCallPlandItemsRepository
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FSalesCallPlandItemsRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFSalesCallPlandItems
) : FSalesCallPlandItemsRepository {

    override fun getRemoteAllFSalesCallPlandItemsByParent(authHeader: String, fsalesCallPlanhBean: Long): Single<List<FSalesCallPlandItemsEntity>> {
        return retrofitService.getAllFSalesCallPlandItemsByParent(authHeader, fsalesCallPlanhBean)
    }


    override fun getCacheAllFSalesCallPlandItems(): List<FSalesCallPlandItemsWithHeader> {
        return appDatabase.fSalesCallPlandItemsDao.getAllFSalesCallPlandItems()
    }

    override fun getCacheAllFSalesCallPlandItemsByParent(parentBean: Long): List<FSalesCallPlandItemsWithHeader> {
        return appDatabase.fSalesCallPlandItemsDao.getAllByParent(parentBean)
    }
    override fun getCacheAllFSalesCallPlandItemsByParentAndCustomer(parentBean: Long, fcustomerBean: Int): LiveData<List<FSalesCallPlandItemsEntity>> {
        return appDatabase.fSalesCallPlandItemsDao.getCacheAllFSalesCallPlandItemsByFtPriceAlthAndFMaterial(parentBean, fcustomerBean)
    }

    override fun addCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity) {
        return appDatabase.fSalesCallPlandItemsDao.insert(fSalesCallPlandItemsEntity)
    }
    override fun addCacheListFSalesCallPlandItems(list: List<FSalesCallPlandItemsEntity>) {
        return appDatabase.fSalesCallPlandItemsDao.insertAll(list)
    }

    override fun putCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity) {
        return appDatabase.fSalesCallPlandItemsDao.update(fSalesCallPlandItemsEntity)
    }

    override fun deleteCacheFSalesCallPlandItems(fSalesCallPlandItemsEntity: FSalesCallPlandItemsEntity) {
        return appDatabase.fSalesCallPlandItemsDao.delete(fSalesCallPlandItemsEntity)
    }

    override fun deleteAllCacheFSalesCallPlandItems() {
        return appDatabase.fSalesCallPlandItemsDao.deleteAllFSalesCallPlandItems()
    }


}