package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFtPriceAltdItems
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFtPriceAlth
import com.erp.distribution.sfa.domain.repository.FtPriceAltdItemsRepository
import com.erp.distribution.sfa.domain.repository.FtPriceAlthRepository
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FtPriceAltdItemsImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFtPriceAltdItems
) : FtPriceAltdItemsRepository {

//    override fun getRemoteAllFtPriceAltdItems(authHeader: String): Single<List<FtPriceAltdItemsEntity>> {
//        return retrofitService.getRemoteAllFtPriceAltdItems(authHeader)
//    }
//
//    override fun getRemoteFtPriceAltdItemsById(authHeader: String, id: Int): Single<FtPriceAltdItemsEntity> {
//        return retrofitService.getRemoteFtPriceAltdItemsById(authHeader, id)
//    }
//
//    override fun getRemoteAllFtPriceAltdItemsByDivision(authHeader: String, divisionId: Int): Single<List<FtPriceAltdItemsEntity>> {
//        return retrofitService.getRemoteAllFtPriceAltdItemsByDivision(authHeader, divisionId)
//    }
    override fun getRemoteAllFtPriceAltdItemsByFtPriceAlth(authHeader: String, ftPricehAlthBean: Int): Single<List<FtPriceAltdItemsEntity>> {
        return retrofitService.getRemoteAllFtPriceAltdItemsByFtPriceAlth(authHeader, ftPricehAlthBean)
    }
//
//    override fun createRemoteFtPriceAltdItems(authHeader: String, ftPriceAltdItemsEntity: FtPriceAltdItemsEntity): Single<FtPriceAltdItemsEntity> {
//        return retrofitService.createRemoteFtPriceAltdItems(authHeader, ftPriceAltdItemsEntity)
//    }
//
//    override fun putRemoteFtPriceAltdItems(authHeader: String, id: Int, ftPriceAltdItemsEntity: FtPriceAltdItemsEntity): Single<FtPriceAltdItemsEntity> {
//        return retrofitService.putRemoteFtPriceAltdItems(authHeader, id, ftPriceAltdItemsEntity)
//    }
//
//    override fun deleteRemoteFtPriceAltdItems(authHeader: String, id: Int): Single<FtPriceAltdItemsEntity> {
//        return retrofitService.deleteRemoteFtPriceAltdItems(authHeader, id)
//    }
//


    override fun getCacheAllFtPriceAltdItems(): LiveData<List<FtPriceAltdItemsEntity>> {
        return appDatabase.priceAltdItemsDao.getAllFtPriceAltdItemsLive
    }

    override fun getCacheAllFtPriceAltdItemsByParent(ftPriceAlthBean: Int): LiveData<List<FtPriceAltdItemsEntity>> {
        return appDatabase.priceAltdItemsDao.getAllByParentLive(ftPriceAlthBean)
    }
    override fun getCacheAllFtPriceAltdItemsByFtPriceAlthAndFMaterial(ftPriceAlthBean: Int, fMaterialBean: Int): LiveData<List<FtPriceAltdItemsEntity>> {
        return appDatabase.priceAltdItemsDao.getCacheAllFtPriceAltdItemsByFtPriceAlthAndFMaterial(ftPriceAlthBean, fMaterialBean)
    }

    override fun addCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity) {
        return appDatabase.priceAltdItemsDao.insert(ftPriceAltdItemsEntity)
    }
    override fun addCacheListFtPriceAltdItems(list: List<FtPriceAltdItemsEntity>) {
        return appDatabase.priceAltdItemsDao.insertAll(list)
    }

    override fun putCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity) {
        return appDatabase.priceAltdItemsDao.update(ftPriceAltdItemsEntity)
    }

    override fun deleteCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity) {
        return appDatabase.priceAltdItemsDao.delete(ftPriceAltdItemsEntity)
    }

    override fun deleteAllCacheFtPriceAltdItems() {
        return appDatabase.priceAltdItemsDao.deleteAllFtPriceAltdItems()
    }


}