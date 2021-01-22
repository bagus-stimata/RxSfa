package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FUserRepository {
    fun getRemoteAllFUser(): Single<List<FUser>>
    fun getRemoteFUserById(id: Int): Single<FUser>
    fun getRemoteFUserByUsername(username: String): Single<FUser>
    fun getRemoteFUserByUsernamePassword(username: String, password: String): Single<FUser>
    fun getRemoteFUserByEmail(email: String): Single<FUser>
//    fun getRemoteDataByParentId(parenId: Int): Single<List<FUser>>
    fun createRemoteFUser(fUser: FUser): Single<FUser>
    fun putRemoteFUser(id: Int, fUser: FUser): Single<FUser>
    fun deleteRemoteFUser(id: Int): Single<FUser>
    

    fun getCacheAllFUser(): LiveData<List<FUser>>
    fun getCacheAllFUserBiasa(): List<FUser>
    fun getCacheFUserById(id: Int): LiveData<FUser>
    fun getCacheFUserByUsername(username: String): LiveData<FUser>
    fun getCacheFUserByEmail(email: String): LiveData<FUser>

    fun createCacheFUser(fUser: FUser)
    fun putCacheFUser(fUser: FUser)
    fun deleteCacheFUser(fUser: FUser)
    
    fun deleteAllCacheFUser()


}