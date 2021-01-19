package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FDistributionChannel
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FDistributionChannelRepository {
    fun getRemoteAllFDistributionChannel(): Single<List<FDistributionChannel>>
    fun getRemoteFDistributionChannelById(id: Int): Single<FDistributionChannel>
    fun getRemoteAllFDistributionChannelByDivision(divisionId: Int): Single<List<FDistributionChannel>>
    fun createRemoteFDistributionChannel(fDistributionChannel: FDistributionChannel): Single<FDistributionChannel>
    fun putRemoteFDistributionChannel(id: Int, fDistributionChannel: FDistributionChannel): Single<FDistributionChannel>
    fun deleteRemoteFDistributionChannel(id: Int): Single<FDistributionChannel>

    fun getCacheAllFDistributionChannel(): LiveData<List<FDistributionChannel>>
    fun getCacheFDistributionChannelById(id: Int): LiveData<FDistributionChannel>
    fun getCacheAllFDistributionChannelByDivision(divisionId: Int): LiveData<List<FDistributionChannel>>
    fun addCacheFDistributionChannel(fDistributionChannel: FDistributionChannel)
    fun putCacheFDistributionChannel(fDistributionChannel: FDistributionChannel)
    fun deleteCacheFDistributionChannel(fDistributionChannel: FDistributionChannel)
    fun deleteAllCacheData()


}