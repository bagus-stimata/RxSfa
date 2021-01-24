package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FWarehouse
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FWarehouseRepository {
    fun getRemoteAllFWarehouse(authHeader: String ): Single<List<FWarehouse>>
    fun getRemoteFWarehouseById(authHeader: String, id: Int): Single<FWarehouse>
    fun getRemoteAllFWarehouseByDivision(authHeader: String, divisionId: Int): Single<List<FWarehouse>>
    fun createRemoteFWarehouse(authHeader: String, fWarehouse: FWarehouse): Single<FWarehouse>
    fun putRemoteFWarehouse(authHeader: String, id: Int, fWarehouse: FWarehouse): Single<FWarehouse>
    fun deleteRemoteFWarehouse(authHeader: String, id: Int): Single<FWarehouse>

    fun getCacheAllFWarehouse(): LiveData<List<FWarehouse>>
    fun getCacheFWarehouseById(id: Int): LiveData<FWarehouse>
    fun getCacheAllFWarehouseByDivision(divisionId: Int): LiveData<List<FWarehouse>>
    fun addCacheFWarehouse(fWarehouse: FWarehouse)
    fun putCacheFWarehouse(fWarehouse: FWarehouse)
    fun deleteCacheFWarehouse(fWarehouse: FWarehouse)
    fun deleteAllCacheFWarehouse()


}