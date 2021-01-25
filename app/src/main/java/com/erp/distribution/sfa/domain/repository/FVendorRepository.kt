package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FVendor
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FVendorRepository {
    fun getRemoteAllFVendor(authHeader: String ): Single<List<FVendor>>
    fun getRemoteFVendorById(authHeader: String, id: Int): Single<FVendor>
    fun getRemoteAllFVendorByDivision(authHeader: String, divisionId: Int): Single<List<FVendor>>
    fun createRemoteFVendor(authHeader: String, fVendor: FVendor): Single<FVendor>
    fun putRemoteFVendor(authHeader: String, id: Int, fVendor: FVendor): Single<FVendor>
    fun deleteRemoteFVendor(authHeader: String, id: Int): Single<FVendor>

    fun getCacheAllFVendor(): LiveData<List<FVendor>>
    fun getCacheFVendorById(id: Int): LiveData<FVendor>
    fun getCacheAllFVendorByDivision(divisionId: Int): LiveData<List<FVendor>>
    fun addCacheFVendor(fVendor: FVendor)
    fun putCacheFVendor(fVendor: FVendor)
    fun deleteCacheFVendor(fVendor: FVendor)
    fun deleteAllCacheData()


}