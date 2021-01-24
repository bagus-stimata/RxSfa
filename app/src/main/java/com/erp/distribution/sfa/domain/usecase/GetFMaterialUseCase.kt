package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FMaterialRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.FMaterial
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFMaterialUseCase @Inject constructor(private val repository: FMaterialRepository) : SingleUseCase<List<FMaterial>>() {

    override fun buildUseCaseSingle(): Single<List<FMaterial>> {
        return repository.getRemoteAllFMaterial("authHeader")
    }
    fun getRemoteAllFMaterial(authHeader: String): Single<List<FMaterial>>{
        return repository.getRemoteAllFMaterial(authHeader)
    }

    fun getRemoteFMaterialById(authHeader: String, id: Int): Single<FMaterial>{
        return repository.getRemoteFMaterialById(authHeader, id)
    }
    fun getRemoteAllFMaterialByDivision(authHeader: String, divisionId: Int): Single<List<FMaterial>>{
        return repository.getRemoteAllFMaterialByDivision(authHeader, divisionId)
    }
    fun createRemoteFMaterial(authHeader: String, fMaterial: FMaterial): Single<FMaterial>{
        return repository.createRemoteFMaterial(authHeader, fMaterial)
    }
    fun putRemoteFMaterial(authHeader: String, id: Int, fMaterial: FMaterial): Single<FMaterial>{
        return repository.putRemoteFMaterial(authHeader, id, fMaterial)
    }
    fun deleteRemoteFMaterial(authHeader: String, id: Int): Single<FMaterial>{
        return repository.deleteRemoteFMaterial(authHeader, id)
    }



    fun getCacheAllFMaterial(): LiveData<List<FMaterial>>{
        return repository.getCacheAllFMaterial()
    }
    fun getCacheFMaterialById(id: Int): LiveData<FMaterial>{
        return repository.getCacheFMaterialById(id)
    }
    fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterial>>{
        return repository.getCacheAllFMaterialByDivision(divisionId)
    }
    fun addCacheFMaterial(fMaterial: FMaterial){
        repository.addCacheFMaterial(fMaterial)
    }
    fun addCacheListFMaterial(list: List<FMaterial>){
        repository.addCacheListFMaterial(list)
    }
    fun putCacheFMaterial(fMaterial: FMaterial){
        repository.putCacheFMaterial(fMaterial)
    }
    fun deleteCacheFMaterial(fMaterial: FMaterial){
        repository.deleteCacheFMaterial(fMaterial)
    }
    fun deleteAllCacheFMaterial(){
        repository.deleteAllCacheFMaterial()
    }
}