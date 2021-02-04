package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FWarehouseRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.FWarehouseEntity
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFWarehouseUseCase @Inject constructor(private val repository: FWarehouseRepository) : SingleUseCase<List<FWarehouseEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FWarehouseEntity>> {
        return repository.getRemoteAllFWarehouse("authHeader")
    }
    fun getRemoteAllFWarehouse(authHeader: String): Single<List<FWarehouseEntity>>{
        return repository.getRemoteAllFWarehouse(authHeader)
    }

    fun getRemoteFWarehouseById(authHeader: String, id: Int): Single<FWarehouseEntity>{
        return repository.getRemoteFWarehouseById(authHeader, id)
    }
    fun getRemoteAllFWarehouseByDivision(authHeader: String, divisionId: Int): Single<List<FWarehouseEntity>>{
        return repository.getRemoteAllFWarehouseByDivision(authHeader, divisionId)
    }
    fun getRemoteAllFWarehouseByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FWarehouseEntity>>{
        return repository.getRemoteAllFWarehouseByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }
    fun createRemoteFWarehouse(authHeader: String, fWarehouseEntity: FWarehouseEntity): Single<FWarehouseEntity>{
        return repository.createRemoteFWarehouse(authHeader, fWarehouseEntity)
    }
    fun putRemoteFWarehouse(authHeader: String, id: Int, fWarehouseEntity: FWarehouseEntity): Single<FWarehouseEntity>{
        return repository.putRemoteFWarehouse(authHeader, id, fWarehouseEntity)
    }
    fun deleteRemoteFWarehouse(authHeader: String, id: Int): Single<FWarehouseEntity>{
        return repository.deleteRemoteFWarehouse(authHeader, id)
    }



    fun getCacheAllFWarehouse(): LiveData<List<FWarehouseEntity>>{
        return repository.getCacheAllFWarehouse()
    }
    fun getCacheFWarehouseById(id: Int): LiveData<FWarehouseEntity>{
        return repository.getCacheFWarehouseById(id)
    }
    fun getCacheAllFWarehouseByDivision(divisionId: Int): LiveData<List<FWarehouseEntity>>{
        return repository.getCacheAllFWarehouseByDivision(divisionId)
    }
    fun addCacheFWarehouse(fWarehouseEntity: FWarehouseEntity){
        repository.addCacheFWarehouse(fWarehouseEntity)
    }
    fun addCacheListFWarehouse(list: List<FWarehouseEntity>){
        repository.addCacheListFWarehouse(list)
    }
    fun putCacheFWarehouse(fWarehouseEntity: FWarehouseEntity){
        repository.putCacheFWarehouse(fWarehouseEntity)
    }
    fun deleteCacheFWarehouse(fWarehouseEntity: FWarehouseEntity){
        repository.deleteCacheFWarehouse(fWarehouseEntity)
    }
    fun deleteAllCacheFWarehouse(){
        repository.deleteAllCacheFWarehouse()
    }
}