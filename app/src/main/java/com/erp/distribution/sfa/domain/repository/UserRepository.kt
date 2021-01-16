package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Photo
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single


/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 * */
interface UserRepository {

    fun getCacheAll(): LiveData<List<FUser>>

    fun getCacheById(entityId: Long): LiveData<FUser>

    fun deleteCache(entity: FUser)

    fun addCache(entity: FUser)


//    fun getRemoteAll(): Single<List<FUser>>
//
//    fun getRemoteById(entityId: Long): Single<FUser>
//
//    fun deleteRemote(entity: FUser)
//
//    fun addRemote(entity: FUser)


}