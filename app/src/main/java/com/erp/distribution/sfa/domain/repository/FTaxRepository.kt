package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FTaxEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FTaxRepository {
    fun getRemoteAllFTax(authHeader: String): Single<List<FTaxEntity>>
    fun getRemoteFTaxById(authHeader: String, id: Int): Single<FTaxEntity>
    fun getRemoteAllFTaxByDivision(authHeader: String, divisionId: Int): Single<List<FTaxEntity>>
    fun createRemoteFTax(authHeader: String, fTaxEntity: FTaxEntity): Single<FTaxEntity>
    fun putRemoteFTax(authHeader: String, id: Int, fTaxEntity: FTaxEntity): Single<FTaxEntity>
    fun deleteRemoteFTax(authHeader: String, id: Int): Single<FTaxEntity>

    fun getCacheAllFTax(): LiveData<List<FTaxEntity>>
    fun getCacheFTaxById(id: Int): LiveData<FTaxEntity>
    fun getCacheAllFTaxByDivision(divisionId: Int): LiveData<List<FTaxEntity>>
    fun addCacheFTax(fTaxEntity: FTaxEntity)
    fun putCacheFTax(fTaxEntity: FTaxEntity)
    fun deleteCacheFTax(fTaxEntity: FTaxEntity)
    fun deleteAllCacheData()


}