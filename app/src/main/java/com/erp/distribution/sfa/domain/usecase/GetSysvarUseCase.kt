package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.SysvarRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity.SysvarEntity
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetSysvarUseCase @Inject constructor(private val repository: SysvarRepository) : SingleUseCase<List<SysvarEntity>>() {

    override fun buildUseCaseSingle(): Single<List<SysvarEntity>> {
        return repository.getRemoteAllSysvar("authHeader")
    }
    fun getRemoteAllSysvar(authHeader: String ): Single<List<SysvarEntity>>{
        return repository.getRemoteAllSysvar(authHeader)
    }

    fun getRemoteSysvarById(authHeader: String, id: Int): Single<SysvarEntity>{
        return repository.getRemoteSysvarById(authHeader, id)
    }
    fun getRemoteAllSysvarByDivision(authHeader: String, divisionId: Int): Single<List<SysvarEntity>>{
        return repository.getRemoteAllSysvarByDivision(authHeader, divisionId)
    }
    fun createRemoteSysvar(authHeader: String, sysvarEntity: SysvarEntity): Single<SysvarEntity>{
        return repository.createRemoteSysvar(authHeader, sysvarEntity)
    }
    fun putRemoteSysvar(authHeader: String, id: Int, sysvarEntity: SysvarEntity): Single<SysvarEntity>{
        return repository.putRemoteSysvar(authHeader, id, sysvarEntity)
    }
    fun deleteRemoteSysvar(authHeader: String, id: Int): Single<SysvarEntity>{
        return repository.deleteRemoteSysvar(authHeader, id)
    }



    fun getCacheAllSysvar(): LiveData<List<SysvarEntity>>{
        return repository.getCacheAllSysvar()
    }
    fun getCacheSysvarById(id: Int): LiveData<SysvarEntity>{
        return repository.getCacheSysvarById(id)
    }
    fun getCacheAllSysvarByDivision(divisionId: Int): LiveData<List<SysvarEntity>>{
        return repository.getCacheAllSysvarByDivision(divisionId)
    }
    fun addCacheSysvar(sysvarEntity: SysvarEntity){
        repository.addCacheSysvar(sysvarEntity)
    }
    fun putCacheSysvar(sysvarEntity: SysvarEntity){
        repository.putCacheSysvar(sysvarEntity)
    }
    fun deleteCacheSysvar(sysvarEntity: SysvarEntity){
        repository.deleteCacheSysvar(sysvarEntity)
    }
    fun deleteAllCacheSysvar(){
        repository.deleteAllCacheSysvar()
    }
}