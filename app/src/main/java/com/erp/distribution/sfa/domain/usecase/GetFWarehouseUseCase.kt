package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FWarehouseRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.FWarehouse
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFWarehouseUseCase @Inject constructor(private val repository: FWarehouseRepository) : SingleUseCase<List<FWarehouse>>() {

    override fun buildUseCaseSingle(): Single<List<FWarehouse>> {
        return repository.getRemoteAllFWarehouse()
    }
    fun getRemoteAllFWarehouse(): Single<List<FWarehouse>>{
        return repository.getRemoteAllFWarehouse()
    }

    fun getRemoteFWarehouseById(id: Int): Single<FWarehouse>{
        return repository.getRemoteFWarehouseById(id)
    }
    fun getRemoteAllFWarehouseByDivision(divisionId: Int): Single<List<FWarehouse>>{
        return repository.getRemoteAllFWarehouseByDivision(divisionId)
    }
    fun createRemoteFWarehouse(fWarehouse: FWarehouse): Single<FWarehouse>{
        return repository.createRemoteFWarehouse(fWarehouse)
    }
    fun putRemoteFWarehouse(id: Int, fWarehouse: FWarehouse): Single<FWarehouse>{
        return repository.putRemoteFWarehouse(id, fWarehouse)
    }
    fun deleteRemoteFWarehouse(id: Int): Single<FWarehouse>{
        return repository.deleteRemoteFWarehouse(id)
    }



    fun getCacheAllFWarehouse(): LiveData<List<FWarehouse>>{
        return repository.getCacheAllFWarehouse()
    }
    fun getCacheFWarehouseById(id: Int): LiveData<FWarehouse>{
        return repository.getCacheFWarehouseById(id)
    }
    fun getCacheAllFWarehouseByDivision(divisionId: Int): LiveData<List<FWarehouse>>{
        return repository.getCacheAllFWarehouseByDivision(divisionId)
    }
    fun addCacheFWarehouse(fWarehouse: FWarehouse){
        repository.addCacheFWarehouse(fWarehouse)
    }
    fun putCacheFWarehouse(fWarehouse: FWarehouse){
        repository.putCacheFWarehouse(fWarehouse)
    }
    fun deleteCacheFWarehouse(fWarehouse: FWarehouse){
        repository.deleteCacheFWarehouse(fWarehouse)
    }
    fun deleteAllCacheFWarehouse(){
        repository.deleteAllCacheFWarehouse()
    }
}