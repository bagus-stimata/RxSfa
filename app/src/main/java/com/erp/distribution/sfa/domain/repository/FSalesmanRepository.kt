package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FSalesman
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FSalesmanRepository {
    fun getRemoteAllFSalesman(): Single<List<FSalesman>>
    fun getRemoteFSalesmanById(id: Int): Single<FSalesman>
    fun getRemoteAllFSalesmanByDivision(divisionId: Int): Single<List<FSalesman>>
    fun createRemoteFSalesman(fSalesman: FSalesman): Single<FSalesman>
    fun putRemoteFSalesman(id: Int, fSalesman: FSalesman): Single<FSalesman>
    fun deleteRemoteFSalesman(id: Int): Single<FSalesman>

    fun getCacheAllFSalesman(): LiveData<List<FSalesman>>
    fun getCacheFSalesmanById(id: Int): LiveData<FSalesman>
    fun getCacheAllFSalesmanByDivision(divisionId: Int): LiveData<List<FSalesman>>
    fun addCacheFSalesman(fSalesman: FSalesman)
    fun putCacheFSalesman(fSalesman: FSalesman)
    fun deleteCacheFSalesman(fSalesman: FSalesman)
    fun deleteAllCacheFSalesman()


}