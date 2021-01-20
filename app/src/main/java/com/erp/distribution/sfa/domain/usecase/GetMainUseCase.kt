package com.erp.distribution.sfa.domain.usecase

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.repository.FUserRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetMainUseCase @Inject constructor(private val repositoryF: FUserRepository) : SingleUseCase<List<FUser>>() {

    override fun buildUseCaseSingle(): Single<List<FUser>> {
        return repositoryF.getRemoteAllFUser()
    }
    fun getRemoteAllData(): Single<List<FUser>>{
        return repositoryF.getRemoteAllFUser()
    }

    fun getRemoteDataById(id: Int): Single<FUser>{
        return repositoryF.getRemoteFUserById(id)
    }
    fun getRemoteDataByUsername(username: String): Single<FUser>{
        return repositoryF.getRemoteFUserByUsername(username)
    }
    fun getRemoteDataByEmail(email: String): Single<FUser>{
        return repositoryF.getRemoteFUserByEmail(email)
    }
//    fun getRemoteDataByParentId(parenId: Int): Single<FUser>{
//        return repository.getRemoteDataByParentId(parenId)
//    }

    fun getCacheAllData(): LiveData<List<FUser>>{
        return repositoryF.getCacheAllFUser()
    }
    fun getCacheDataById(id: Int): LiveData<FUser>{
        return repositoryF.getCacheFUserById(id)
    }
    fun getCacheDataByUsername(username: String): LiveData<FUser>{
        return repositoryF.getCacheFUserByUsername(username)
    }
    fun getCacheDataByEmail(email: String): LiveData<FUser>{
        return repositoryF.getCacheFUserByEmail(email)
    }

    fun addCacheData(fUser: FUser){
        repositoryF.createCacheFUser(fUser)
    }

    fun deleteAllCacheData(){
        repositoryF.deleteAllCacheFUser()
    }
}