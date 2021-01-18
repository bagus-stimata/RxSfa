package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.UserRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetMainUseCase @Inject constructor(private val repository: UserRepository) : SingleUseCase<List<FUser>>() {

    override fun buildUseCaseSingle(): Single<List<FUser>> {
        return repository.getRemoteAllData()
    }
    fun getRemoteAllData(): Single<List<FUser>>{
        return repository.getRemoteAllData()
    }

    fun getRemoteDataById(id: Int): Single<FUser>{
        return repository.getRemoteDataById(id)
    }
    fun getRemoteDataByUsername(username: String): Single<FUser>{
        return repository.getRemoteDataByUsername(username)
    }
    fun getRemoteDataByEmail(email: String): Single<FUser>{
        return repository.getRemoteDataByEmail(email)
    }
//    fun getRemoteDataByParentId(parenId: Int): Single<FUser>{
//        return repository.getRemoteDataByParentId(parenId)
//    }

    fun getCacheAllData(): LiveData<List<FUser>>{
        return repository.getCacheData()
    }
    fun getCacheDataById(id: Int): LiveData<FUser>{
        return repository.getCacheDataById(id)
    }
    fun getCacheDataByUsername(username: String): LiveData<FUser>{
        return repository.getCacheDataByUsername(username)
    }
    fun getCacheDataByEmail(email: String): LiveData<FUser>{
        return repository.getCacheDataByEmail(email)
    }

    fun addCacheData(fUser: FUser){
        repository.addCacheData(fUser)
    }

    fun deleteAllCacheData(){
        repository.deleteAllCacheData()
    }
}