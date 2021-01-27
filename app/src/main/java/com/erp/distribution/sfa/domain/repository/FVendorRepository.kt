package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FVendorEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FVendorRepository {
    fun getRemoteAllFVendor(authHeader: String ): Single<List<FVendorEntity>>
    fun getRemoteFVendorById(authHeader: String, id: Int): Single<FVendorEntity>
    fun getRemoteAllFVendorByDivision(authHeader: String, divisionId: Int): Single<List<FVendorEntity>>
    fun createRemoteFVendor(authHeader: String, fVendorEntity: FVendorEntity): Single<FVendorEntity>
    fun putRemoteFVendor(authHeader: String, id: Int, fVendorEntity: FVendorEntity): Single<FVendorEntity>
    fun deleteRemoteFVendor(authHeader: String, id: Int): Single<FVendorEntity>

    fun getCacheAllFVendor(): LiveData<List<FVendorEntity>>
    fun getCacheFVendorById(id: Int): LiveData<FVendorEntity>
    fun getCacheAllFVendorByDivision(divisionId: Int): LiveData<List<FVendorEntity>>
    fun addCacheFVendor(fVendorEntity: FVendorEntity)
    fun putCacheFVendor(fVendorEntity: FVendorEntity)
    fun deleteCacheFVendor(fVendorEntity: FVendorEntity)
    fun deleteAllCacheData()


}