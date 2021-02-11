package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FWarehouseEntity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FWarehouseRepository {
    fun getRemoteAllFWarehouse(authHeader: String ): Single<List<FWarehouseEntity>>
    fun getRemoteFWarehouseById(authHeader: String, id: Int): Single<FWarehouseEntity>
    fun getRemoteAllFWarehouseByDivision(authHeader: String, divisionId: Int): Single<List<FWarehouseEntity>>
    fun getRemoteAllFWarehouseByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FWarehouseEntity>>
    fun createRemoteFWarehouse(authHeader: String, fWarehouseEntity: FWarehouseEntity): Single<FWarehouseEntity>
    fun putRemoteFWarehouse(authHeader: String, id: Int, fWarehouseEntity: FWarehouseEntity): Single<FWarehouseEntity>
    fun deleteRemoteFWarehouse(authHeader: String, id: Int): Single<FWarehouseEntity>

    fun getCacheAllFWarehouse(): LiveData<List<FWarehouseEntity>>
    fun getCacheFWarehouseById(id: Int): LiveData<FWarehouseEntity>
    fun getCacheAllFWarehouseByDivision(divisionId: Int): LiveData<List<FWarehouseEntity>>
    fun addCacheFWarehouse(fWarehouseEntity: FWarehouseEntity)
    fun addCacheListFWarehouse(list: List<FWarehouseEntity>)
    fun putCacheFWarehouse(fWarehouseEntity: FWarehouseEntity)
    fun deleteCacheFWarehouse(fWarehouseEntity: FWarehouseEntity)
    fun deleteAllCacheFWarehouse()


}