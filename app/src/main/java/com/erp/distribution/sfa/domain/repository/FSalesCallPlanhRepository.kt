package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSalesCallPlanhRepository {
    fun getRemoteAllFSalesCallPlanhByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FSalesCallPlanhEntity>>
    fun getRemoteAllFSalesCallPlanhBySalesman(authHeader: String, fsalesmanBean: Int): Single<List<FSalesCallPlanhEntity>>

    fun getCacheAllFSalesCallPlanh(): LiveData<List<FSalesCallPlanhEntity>>
    fun getCacheAllFSalesCallPlanhByDivision(divisionId: Int): LiveData<List<FSalesCallPlanhEntity>>
    fun addCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity)
    fun addCacheListFSalesCallPlanh(list: List<FSalesCallPlanhEntity>)
    fun putCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity)
    fun deleteCacheFSalesCallPlanh(fSalesCallPlanhEntity: FSalesCallPlanhEntity)
    fun deleteAllCacheFSalesCallPlanh()


}