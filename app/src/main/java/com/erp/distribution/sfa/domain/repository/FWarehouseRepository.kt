package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FWarehouse
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FWarehouseRepository {
    fun getRemoteAllFWarehouse(): Single<List<FWarehouse>>
    fun getRemoteFWarehouseById(id: Int): Single<FWarehouse>
    fun getRemoteAllFWarehouseByDivision(divisionId: Int): Single<List<FWarehouse>>
    fun createRemoteFWarehouse(fWarehouse: FWarehouse): Single<FWarehouse>
    fun putRemoteFWarehouse(id: Int, fWarehouse: FWarehouse): Single<FWarehouse>
    fun deleteRemoteFWarehouse(id: Int): Single<FWarehouse>

    fun getCacheAllFWarehouse(): LiveData<List<FWarehouse>>
    fun getCacheFWarehouseById(id: Int): LiveData<FWarehouse>
    fun getCacheAllFWarehouseByDivision(divisionId: Int): LiveData<List<FWarehouse>>
    fun addCacheFWarehouse(fWarehouse: FWarehouse)
    fun putCacheFWarehouse(fWarehouse: FWarehouse)
    fun deleteCacheFWarehouse(fWarehouse: FWarehouse)
    fun deleteAllCacheFWarehouse()


}