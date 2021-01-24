package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FSalesman
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSalesmanRepository {
    fun getRemoteAllFSalesman(authHeader: String): Single<List<FSalesman>>
    fun getRemoteFSalesmanById(authHeader: String, id: Int): Single<FSalesman>
    fun getRemoteAllFSalesmanByDivision(authHeader: String, divisionId: Int): Single<List<FSalesman>>
    fun createRemoteFSalesman(authHeader: String, fSalesman: FSalesman): Single<FSalesman>
    fun putRemoteFSalesman(authHeader: String, id: Int, fSalesman: FSalesman): Single<FSalesman>
    fun deleteRemoteFSalesman(authHeader: String, id: Int): Single<FSalesman>

    fun getCacheAllFSalesman(): LiveData<List<FSalesman>>
    fun getCacheFSalesmanById(id: Int): LiveData<FSalesman>
    fun getCacheAllFSalesmanByDivision(divisionId: Int): LiveData<List<FSalesman>>
    fun addCacheFSalesman(fSalesman: FSalesman)
    fun addCacheListFSalesman(list: List<FSalesman>)
    fun putCacheFSalesman(fSalesman: FSalesman)
    fun deleteCacheFSalesman(fSalesman: FSalesman)
    fun deleteAllCacheFSalesman()


}