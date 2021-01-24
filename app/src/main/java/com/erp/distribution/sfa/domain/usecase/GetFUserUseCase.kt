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
class GetFUserUseCase @Inject constructor(private val repository: FUserRepository) : SingleUseCase<List<FUser>>() {

    override fun buildUseCaseSingle(): Single<List<FUser>> {
        return repository.getRemoteAllFUser("aa")
    }
    fun getRemoteAllFUser(authHeader: String): Single<List<FUser>>{
        return repository.getRemoteAllFUser(authHeader)
    }

    fun getRemoteFUserById(authHeader: String, id: Int): Single<FUser>{
        return repository.getRemoteFUserById(authHeader, id)
    }
    fun getRemoteAllFUserByUsername(authHeader: String, username: String): Single<FUser>{
        return repository.getRemoteFUserByUsername(authHeader, username)
    }
    fun getRemoteAllFUserByUsernamePassword(authHeader: String, username: String, password: String): Single<FUser>{
        return repository.getRemoteFUserByUsernamePassword(authHeader, username, password)
    }
    fun getRemoteAllFUserByEmail(authHeader: String, email: String): Single<FUser>{
        return repository.getRemoteFUserByEmail(authHeader, email)
    }
    fun createRemoteFUser(authHeader: String, fUser: FUser): Single<FUser>{
        return repository.createRemoteFUser(authHeader, fUser)
    }
    fun putRemoteFUser(authHeader: String, id: Int, fUser: FUser): Single<FUser>{
        return repository.putRemoteFUser(authHeader, id, fUser)
    }
    fun deleteRemoteFUser(authHeader: String, id: Int): Single<FUser>{
        return repository.deleteRemoteFUser(authHeader, id)
    }



    fun getCacheAllFUser(): LiveData<List<FUser>>{
        return repository.getCacheAllFUser()
    }

    fun getCacheAllFUserBiasa(): List<FUser>{
        return repository.getCacheAllFUserBiasa()
    }
    fun getCacheFUserById(id: Int): LiveData<FUser>{
        return repository.getCacheFUserById(id)
    }
//    fun getCacheAllFUserByDivision(divisionId: Int): LiveData<List<FUser>>{
//        return repository.getCacheAllFUserByDivision(divisionId)
//    }
    fun addCacheFUser(fUser: FUser){
        repository.createCacheFUser(fUser)
    }
    fun putCacheFUser(fUser: FUser){
        repository.putCacheFUser(fUser)
    }
    fun deleteCacheFUser(fUser: FUser){
        repository.deleteCacheFUser(fUser)
    }
    fun deleteAllCacheFUser(){
        repository.deleteAllCacheFUser()
    }
}