package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FUserRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetMainUseCase @Inject constructor(private val repository: FUserRepository) : SingleUseCase<List<FUserEntity>>() {

    override fun buildUseCaseSingle(): Single<List<FUserEntity>> {
        return repository.getRemoteAllFUser("authHeader")
    }
    fun getRemoteAllData(authHeader: String): Single<List<FUserEntity>>{
        return repository.getRemoteAllFUser(authHeader)
    }

    fun getRemoteDataById(authHeader: String, id: Int): Single<FUserEntity>{
        return repository.getRemoteFUserById(authHeader, id)
    }
    fun getRemoteDataByUsername(authHeader: String, username: String): Single<FUserEntity>{
        return repository.getRemoteFUserByUsername(authHeader, username)
    }
    fun getRemoteDataByEmail(authHeader: String, email: String): Single<FUserEntity>{
        return repository.getRemoteFUserByEmail(authHeader, email)
    }
//    fun getRemoteDataByParentId(parenId: Int): Single<FUser>{
//        return repository.getRemoteDataByParentId(parenId)
//    }

    fun getCacheAllData(): LiveData<List<FUserEntity>>{
        return repository.getCacheAllFUser()
    }
    fun getCacheDataById(id: Int): LiveData<FUserEntity>{
        return repository.getCacheFUserById(id)
    }
    fun getCacheDataByUsername(username: String): LiveData<FUserEntity>{
        return repository.getCacheFUserByUsername(username)
    }
    fun getCacheDataByEmail(email: String): LiveData<FUserEntity>{
        return repository.getCacheFUserByEmail(email)
    }

    fun addCacheData(fUserEntity: FUserEntity){
        repository.createCacheFUser(fUserEntity)
    }

    fun deleteAllCacheData(){
        repository.deleteAllCacheFUser()
    }
}