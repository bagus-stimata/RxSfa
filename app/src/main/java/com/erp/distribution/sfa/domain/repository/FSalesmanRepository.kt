package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FSalesmanEntity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSalesmanRepository {
    fun getRemoteAllFSalesman(authHeader: String): Single<List<FSalesmanEntity>>
    fun getRemoteFSalesmanById(authHeader: String, id: Int): Single<FSalesmanEntity>
    fun getRemoteAllFSalesmanByDivision(authHeader: String, divisionId: Int): Single<List<FSalesmanEntity>>
    fun getRemoteAllFSalesmanByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FSalesmanEntity>>
    fun createRemoteFSalesman(authHeader: String, fSalesmanEntity: FSalesmanEntity): Single<FSalesmanEntity>
    fun putRemoteFSalesman(authHeader: String, id: Int, fSalesmanEntity: FSalesmanEntity): Single<FSalesmanEntity>
    fun deleteRemoteFSalesman(authHeader: String, id: Int): Single<FSalesmanEntity>

    fun getCacheAllFSalesman(): LiveData<List<FSalesmanEntity>>
    fun getCacheFSalesmanById(id: Int): LiveData<FSalesmanEntity>
    fun getCacheAllFSalesmanByDivision(divisionId: Int): LiveData<List<FSalesmanEntity>>
    fun addCacheFSalesman(fSalesmanEntity: FSalesmanEntity)
    fun addCacheListFSalesman(list: List<FSalesmanEntity>)
    fun putCacheFSalesman(fSalesmanEntity: FSalesmanEntity)
    fun deleteCacheFSalesman(fSalesmanEntity: FSalesmanEntity)
    fun deleteAllCacheFSalesman()


}