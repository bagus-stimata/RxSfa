package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.data.source.remote.RetrofitService
import com.erp.distribution.sfa.domain.model.Photo
import com.erp.distribution.sfa.domain.repository.PhotoRepository
import com.erp.distribution.sfa.domain.repository.UserRepository
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single

/**
 * This repository is responsible for
 * fetching data [photo] from server or db
 * */
class userRepositoryImp(
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
) : UserRepository {

    override fun getCacheAll(): LiveData<List<FUser>> {
        return database.userDao.allFUserLive
    }

    override fun getCacheById(entityId: Long): LiveData<FUser> {
        return database.userDao.getAllByIdLive(entityId.toInt())
    }

    override fun deleteCache(entity: FUser) {
        return database.userDao.delete(entity)
    }

    override fun addCache(entity: FUser) {
        return database.userDao.insert(entity)
    }

    override fun getRemoteAll(): Single<List<FUser>> {
        TODO("Not yet implemented")
    }

    override fun getRemoteById(entityId: Long): Single<FUser> {
        TODO("Not yet implemented")
    }

    override fun deleteRemote(entity: FUser) {
        TODO("Not yet implemented")
    }

    override fun addRemote(entity: FUser) {
        TODO("Not yet implemented")
    }

}