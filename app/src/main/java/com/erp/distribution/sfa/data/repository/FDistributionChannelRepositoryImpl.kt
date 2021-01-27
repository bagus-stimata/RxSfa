package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFDistributionChannel
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FDistributionChannelRepository
import com.erp.distribution.sfa.data.source.entity.FDistributionChannelEntity
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FDistributionChannelRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFDistributionChannel
) : FDistributionChannelRepository {

    override fun getRemoteAllFDistributionChannel(authHeader: String): Single<List<FDistributionChannelEntity>> {
        return retrofitService.getRemoteAllFDistributionChannel(authHeader)
    }

    override fun getRemoteFDistributionChannelById(authHeader: String, id: Int): Single<FDistributionChannelEntity> {
        return retrofitService.getRemoteFDistributionChannelById(authHeader, id)
    }

    override fun getRemoteAllFDistributionChannelByDivision(authHeader: String, divisionId: Int): Single<List<FDistributionChannelEntity>> {
        return retrofitService.getRemoteAllFDistributionChannelByDivision(authHeader, divisionId)
    }

    override fun createRemoteFDistributionChannel(authHeader: String, fDistributionChannelEntity: FDistributionChannelEntity): Single<FDistributionChannelEntity> {
        return retrofitService.createRemoteFDistributionChannel(authHeader, fDistributionChannelEntity)
    }

    override fun putRemoteFDistributionChannel(authHeader: String, id: Int, fDistributionChannelEntity: FDistributionChannelEntity): Single<FDistributionChannelEntity> {
        return retrofitService.putRemoteFDistributionChannel(authHeader, id, fDistributionChannelEntity)
    }

    override fun deleteRemoteFDistributionChannel(authHeader: String, id: Int): Single<FDistributionChannelEntity> {
        return retrofitService.deleteRemoteFDistributionChannel(authHeader, id)
    }



    override fun getCacheAllFDistributionChannel(): LiveData<List<FDistributionChannelEntity>> {
        return appDatabase.distributionChannelDao.getAllFDistributionChannelEntityLive
    }

    override fun getCacheFDistributionChannelById(id: Int): LiveData<FDistributionChannelEntity> {
        return appDatabase.distributionChannelDao.getAllByIdLive(id)
    }

    override fun getCacheAllFDistributionChannelByDivision(divisionId: Int): LiveData<List<FDistributionChannelEntity>> {
        return appDatabase.distributionChannelDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity) {
        return appDatabase.distributionChannelDao.insert(fDistributionChannelEntity)
    }

    override fun putCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity) {
        return appDatabase.distributionChannelDao.update(fDistributionChannelEntity)
    }

    override fun deleteCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity) {
        return appDatabase.distributionChannelDao.delete(fDistributionChannelEntity)
    }

    override fun deleteAllCacheData() {
        return appDatabase.distributionChannelDao.deleteAllFDistributionChannel()
    }


//    override fun getRemoteAllData(): Single<List<FDistributionChannel>> {
//        return retrofitService.getRemoteAllFDistributionChannel(authHeader)
//    }


}