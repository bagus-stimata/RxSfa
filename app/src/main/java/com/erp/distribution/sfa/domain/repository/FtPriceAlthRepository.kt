package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtPriceAlthRepository {
    fun getRemoteAllFtPriceAlthByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FtPriceAlthEntity>>

    fun getCacheAllFtPriceAlth(): LiveData<List<FtPriceAlthEntity>>
    fun getCacheAllFtPriceAlthByDivision(divisionId: Int): LiveData<List<FtPriceAlthEntity>>
    fun addCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity)
    fun addCacheListFtPriceAlth(list: List<FtPriceAlthEntity>)
    fun putCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity)
    fun deleteCacheFtPriceAlth(ftPriceAlthEntity: FtPriceAlthEntity)
    fun deleteAllCacheFtPriceAlth()


}