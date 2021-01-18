package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.domain.model.DummyUser
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface UserRepository {
    fun getRemoteAllData(): Single<List<FUser>>
    fun getRemoteDataById(id: Int): Single<FUser>
    fun getRemoteDataByUsername(username: String): Single<FUser>
    fun getRemoteDataByEmail(email: String): Single<FUser>
//    fun getRemoteDataByParentId(parenId: Int): Single<List<FUser>>

    fun getCacheData(): LiveData<List<FUser>>
    fun getCacheDataById(id: Int): LiveData<FUser>
    fun getCacheDataByUsername(username: String): LiveData<FUser>
    fun getCacheDataByEmail(email: String): LiveData<FUser>

    fun addCacheData(fUser: FUser)
    fun deleteAllCacheData()


}