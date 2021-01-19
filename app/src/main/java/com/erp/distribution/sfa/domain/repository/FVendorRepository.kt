package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FVendor
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FVendorRepository {
    fun getRemoteAllFVendor(): Single<List<FVendor>>
    fun getRemoteFVendorById(id: Int): Single<FVendor>
    fun getRemoteAllFVendorByDivision(divisionId: Int): Single<List<FVendor>>
    fun createRemoteFVendor(fVendor: FVendor): Single<FVendor>
    fun putRemoteFVendor(id: Int, fVendor: FVendor): Single<FVendor>
    fun deleteRemoteFVendor(id: Int): Single<FVendor>

    fun getCacheAllFVendor(): LiveData<List<FVendor>>
    fun getCacheFVendorById(id: Int): LiveData<FVendor>
    fun getCacheAllFVendorByDivision(divisionId: Int): LiveData<List<FVendor>>
    fun addCacheFVendor(fVendor: FVendor)
    fun putCacheFVendor(fVendor: FVendor)
    fun deleteCacheFVendor(fVendor: FVendor)
    fun deleteAllCacheData()


}