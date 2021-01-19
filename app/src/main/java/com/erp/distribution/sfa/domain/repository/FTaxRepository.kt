package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FTax
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FTaxRepository {
    fun getRemoteAllFTax(): Single<List<FTax>>
    fun getRemoteFTaxById(id: Int): Single<FTax>
    fun getRemoteAllFTaxByDivision(divisionId: Int): Single<List<FTax>>
    fun createRemoteFTax(fTax: FTax): Single<FTax>
    fun putRemoteFTax(id: Int, fTax: FTax): Single<FTax>
    fun deleteRemoteFTax(id: Int): Single<FTax>

    fun getCacheAllFTax(): LiveData<List<FTax>>
    fun getCacheFTaxById(id: Int): LiveData<FTax>
    fun getCacheAllFTaxByDivision(divisionId: Int): LiveData<List<FTax>>
    fun addCacheFTax(fTax: FTax)
    fun putCacheFTax(fTax: FTax)
    fun deleteCacheFTax(fTax: FTax)
    fun deleteAllCacheData()


}