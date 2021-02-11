package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity_security.FUser
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FUserRepository {
    fun getRemoteAllFUser(authHeader: String): Single<List<FUser>>
    fun getRemoteFUserById(authHeader: String, id: Int): Single<FUser>
    fun getRemoteFUserByUsername(authHeader: String, username: String): Single<FUser>
    fun getRemoteFUserByUsernamePassword(authHeader: String, username: String, password: String): Single<FUser>
    fun getRemoteFUserByEmail(authHeader: String, email: String): Single<FUser>
//    fun getRemoteDataByParentId(parenId: Int): Single<List<FUser>>
    fun createRemoteFUser(authHeader: String, fUser: FUser): Single<FUser>
    fun putRemoteFUser(authHeader: String, id: Int, fUser: FUser): Single<FUser>
    fun deleteRemoteFUser(authHeader: String, id: Int): Single<FUser>
    

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