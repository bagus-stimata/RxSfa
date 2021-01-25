package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FDistributionChannel
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FDistributionChannelRepository {
    fun getRemoteAllFDistributionChannel(authHeader: String): Single<List<FDistributionChannel>>
    fun getRemoteFDistributionChannelById(authHeader: String, id: Int): Single<FDistributionChannel>
    fun getRemoteAllFDistributionChannelByDivision(authHeader: String, divisionId: Int): Single<List<FDistributionChannel>>
    fun createRemoteFDistributionChannel(authHeader: String, fDistributionChannel: FDistributionChannel): Single<FDistributionChannel>
    fun putRemoteFDistributionChannel(authHeader: String, id: Int, fDistributionChannel: FDistributionChannel): Single<FDistributionChannel>
    fun deleteRemoteFDistributionChannel(authHeader: String, id: Int): Single<FDistributionChannel>

    fun getCacheAllFDistributionChannel(): LiveData<List<FDistributionChannel>>
    fun getCacheFDistributionChannelById(id: Int): LiveData<FDistributionChannel>
    fun getCacheAllFDistributionChannelByDivision(divisionId: Int): LiveData<List<FDistributionChannel>>
    fun addCacheFDistributionChannel(fDistributionChannel: FDistributionChannel)
    fun putCacheFDistributionChannel(fDistributionChannel: FDistributionChannel)
    fun deleteCacheFDistributionChannel(fDistributionChannel: FDistributionChannel)
    fun deleteAllCacheData()


}