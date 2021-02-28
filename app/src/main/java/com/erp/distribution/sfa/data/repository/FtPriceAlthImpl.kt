package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFtPriceAlth
import com.erp.distribution.sfa.domain.repository.FtPriceAlthRepository
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FtPriceAlthImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFtPriceAlth
) : FtPriceAlthRepository {

    override fun getRemoteAllFtPriceAlthByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FtPriceAlthEntity>> {
        return retrofitService.getAlltPriceAlthByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }


    override fun getCacheAllFtPriceAlth(): LiveData<List<FtPriceAlthEntity>> {
        return appDatabase.priceAlthDao.getAllFtPriceAlthLive
    }


    override fun getCacheAllFtPriceAlthByDivision(fdivisionBean: Int): LiveData<List<FtPriceAlthEntity>> {
        return appDatabase.priceAlthDao.getAllByDivisionLive(fdivisionBean)
    }

    override fun addCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity) {
        return appDatabase.priceAlthDao.insert(ftPriceAlthEntity)
    }
    override fun addCacheListFtPriceAlth(list: List<FtPriceAlthEntity>) {
        return appDatabase.priceAlthDao.insertAll(list)
    }

    override fun putCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity) {
        return appDatabase.priceAlthDao.update(ftPriceAlthEntity)
    }

    override fun deleteCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity) {
        return appDatabase.priceAlthDao.delete(ftPriceAlthEntity)
    }

    override fun deleteAllCacheFtPriceAlth() {
        return appDatabase.priceAlthDao.deleteAllFtPriceAlth()
    }


}