package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.SysvarRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.model.Sysvar
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetSysvarUseCase @Inject constructor(private val repository: SysvarRepository) : SingleUseCase<List<Sysvar>>() {

    override fun buildUseCaseSingle(): Single<List<Sysvar>> {
        return repository.getRemoteAllSysvar()
    }
    fun getRemoteAllSysvar(): Single<List<Sysvar>>{
        return repository.getRemoteAllSysvar()
    }

    fun getRemoteSysvarById(id: Int): Single<Sysvar>{
        return repository.getRemoteSysvarById(id)
    }
    fun getRemoteAllSysvarByDivision(divisionId: Int): Single<List<Sysvar>>{
        return repository.getRemoteAllSysvarByDivision(divisionId)
    }
    fun createRemoteSysvar(sysvar: Sysvar): Single<Sysvar>{
        return repository.createRemoteSysvar(sysvar)
    }
    fun putRemoteSysvar(id: Int, sysvar: Sysvar): Single<Sysvar>{
        return repository.putRemoteSysvar(id, sysvar)
    }
    fun deleteRemoteSysvar(id: Int): Single<Sysvar>{
        return repository.deleteRemoteSysvar(id)
    }



    fun getCacheAllSysvar(): LiveData<List<Sysvar>>{
        return repository.getCacheAllSysvar()
    }
    fun getCacheSysvarById(id: Int): LiveData<Sysvar>{
        return repository.getCacheSysvarById(id)
    }
    fun getCacheAllSysvarByDivision(divisionId: Int): LiveData<List<Sysvar>>{
        return repository.getCacheAllSysvarByDivision(divisionId)
    }
    fun addCacheSysvar(sysvar: Sysvar){
        repository.addCacheSysvar(sysvar)
    }
    fun putCacheSysvar(sysvar: Sysvar){
        repository.putCacheSysvar(sysvar)
    }
    fun deleteCacheSysvar(sysvar: Sysvar){
        repository.deleteCacheSysvar(sysvar)
    }
    fun deleteAllCacheSysvar(){
        repository.deleteAllCacheSysvar()
    }
}