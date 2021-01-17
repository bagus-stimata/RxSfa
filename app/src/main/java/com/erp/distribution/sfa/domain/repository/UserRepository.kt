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
    fun getRemoteData(): Single<List<FUser>>
//    fun getRemoteDataById(id: Int): Single<DummyUser>
//    fun getRemoteDataByUsername(username: String): Single<List<DummyUser>>
//    fun getRemoteDataByParentId(parenId: Int): Single<List<DummyUser>>

    fun getCacheData(): LiveData<List<FUser>>
    fun addCacheData(fUser: FUser)
    fun deleteAllCacheData()
}