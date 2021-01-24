package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFDistributionChannel
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FDistributionChannelRepository
import com.erp.distribution.sfa.model.FDistributionChannel
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FDistributionChannelRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFDistributionChannel
) : FDistributionChannelRepository {

    override fun getRemoteAllFDistributionChannel(authHeader: String): Single<List<FDistributionChannel>> {
        return retrofitService.getRemoteAllFDistributionChannel(authHeader)
    }

    override fun getRemoteFDistributionChannelById(authHeader: String, id: Int): Single<FDistributionChannel> {
        return retrofitService.getRemoteFDistributionChannelById(authHeader, id)
    }

    override fun getRemoteAllFDistributionChannelByDivision(authHeader: String, divisionId: Int): Single<List<FDistributionChannel>> {
        return retrofitService.getRemoteAllFDistributionChannelByDivision(authHeader, divisionId)
    }

    override fun createRemoteFDistributionChannel(authHeader: String, fDistributionChannel: FDistributionChannel): Single<FDistributionChannel> {
        return retrofitService.createRemoteFDistributionChannel(authHeader, fDistributionChannel)
    }

    override fun putRemoteFDistributionChannel(authHeader: String, id: Int, fDistributionChannel: FDistributionChannel): Single<FDistributionChannel> {
        return retrofitService.putRemoteFDistributionChannel(authHeader, id, fDistributionChannel)
    }

    override fun deleteRemoteFDistributionChannel(authHeader: String, id: Int): Single<FDistributionChannel> {
        return retrofitService.deleteRemoteFDistributionChannel(authHeader, id)
    }



    override fun getCacheAllFDistributionChannel(): LiveData<List<FDistributionChannel>> {
        return appDatabase.distributionChannelDao.getAllFDistributionChannelLive
    }

    override fun getCacheFDistributionChannelById(id: Int): LiveData<FDistributionChannel> {
        return appDatabase.distributionChannelDao.getAllByIdLive(id)
    }

    override fun getCacheAllFDistributionChannelByDivision(divisionId: Int): LiveData<List<FDistributionChannel>> {
        return appDatabase.distributionChannelDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFDistributionChannel(fDistributionChannel: FDistributionChannel) {
        return appDatabase.distributionChannelDao.insert(fDistributionChannel)
    }

    override fun putCacheFDistributionChannel(fDistributionChannel: FDistributionChannel) {
        return appDatabase.distributionChannelDao.update(fDistributionChannel)
    }

    override fun deleteCacheFDistributionChannel(fDistributionChannel: FDistributionChannel) {
        return appDatabase.distributionChannelDao.delete(fDistributionChannel)
    }

    override fun deleteAllCacheData() {
        return appDatabase.distributionChannelDao.deleteAllFDistributionChannel()
    }


//    override fun getRemoteAllData(): Single<List<FDistributionChannel>> {
//        return retrofitService.getRemoteAllFDistributionChannel(authHeader)
//    }


}