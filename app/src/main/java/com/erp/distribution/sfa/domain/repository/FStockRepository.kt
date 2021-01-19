package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FStock
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FStockRepository {
    fun getRemoteAllFStock(): Single<List<FStock>>
    fun getRemoteFStockById(id: Int): Single<FStock>
    fun getRemoteAllFStockByDivision(divisionId: Int): Single<List<FStock>>
    fun createRemoteFStock(fStock: FStock): Single<FStock>
    fun putRemoteFStock(id: Int, fStock: FStock): Single<FStock>
    fun deleteRemoteFStock(id: Int): Single<FStock>

    fun getCacheAllFStock(): LiveData<List<FStock>>
    fun getCacheFStockById(id: Int): LiveData<FStock>
    fun getCacheAllFStockByDivision(divisionId: Int): LiveData<List<FStock>>
    fun addCacheFStock(fStock: FStock)
    fun putCacheFStock(fStock: FStock)
    fun deleteCacheFStock(fStock: FStock)
    fun deleteAllCacheData()


}