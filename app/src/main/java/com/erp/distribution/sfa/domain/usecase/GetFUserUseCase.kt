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
        return repository.getRemoteAllFUser()
    }
    fun getRemoteAllFUser(): Single<List<FUser>>{
        return repository.getRemoteAllFUser()
    }

    fun getRemoteFUserById(id: Int): Single<FUser>{
        return repository.getRemoteFUserById(id)
    }
    fun getRemoteAllFUserByUsername(username: String): Single<FUser>{
        return repository.getRemoteFUserByUsername(username)
    }
    fun getRemoteAllFUserByEmail(email: String): Single<FUser>{
        return repository.getRemoteFUserByEmail(email)
    }
    fun createRemoteFUser(fUser: FUser): Single<FUser>{
        return repository.createRemoteFUser(fUser)
    }
    fun putRemoteFUser(id: Int, fUser: FUser): Single<FUser>{
        return repository.putRemoteFUser(id, fUser)
    }
    fun deleteRemoteFUser(id: Int): Single<FUser>{
        return repository.deleteRemoteFUser(id)
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