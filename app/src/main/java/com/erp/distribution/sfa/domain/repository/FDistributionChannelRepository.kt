package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FDistributionChannelEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FDistributionChannelRepository {
    fun getRemoteAllFDistributionChannel(authHeader: String): Single<List<FDistributionChannelEntity>>
    fun getRemoteFDistributionChannelById(authHeader: String, id: Int): Single<FDistributionChannelEntity>
    fun getRemoteAllFDistributionChannelByDivision(authHeader: String, divisionId: Int): Single<List<FDistributionChannelEntity>>
    fun createRemoteFDistributionChannel(authHeader: String, fDistributionChannelEntity: FDistributionChannelEntity): Single<FDistributionChannelEntity>
    fun putRemoteFDistributionChannel(authHeader: String, id: Int, fDistributionChannelEntity: FDistributionChannelEntity): Single<FDistributionChannelEntity>
    fun deleteRemoteFDistributionChannel(authHeader: String, id: Int): Single<FDistributionChannelEntity>

    fun getCacheAllFDistributionChannel(): LiveData<List<FDistributionChannelEntity>>
    fun getCacheFDistributionChannelById(id: Int): LiveData<FDistributionChannelEntity>
    fun getCacheAllFDistributionChannelByDivision(divisionId: Int): LiveData<List<FDistributionChannelEntity>>
    fun addCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity)
    fun putCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity)
    fun deleteCacheFDistributionChannel(fDistributionChannelEntity: FDistributionChannelEntity)
    fun deleteAllCacheData()


}