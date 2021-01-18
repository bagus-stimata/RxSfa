package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceSecurity
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.UserRepository
import com.erp.distribution.sfa.security_model.FUser
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class UserRepositoryImp(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceSecurity
) :
    UserRepository {


    override fun getRemoteAllData(): Single<List<FUser>> {
        return retrofitService.getAllData(Constants.authHeader)
    }

    override fun getRemoteDataById(id: Int): Single<FUser> {
        return retrofitService.getFUserById(Constants.authHeader, id)
    }

    override fun getRemoteDataByUsername(username: String): Single<FUser> {
        return retrofitService.getFUserByUsername(Constants.authHeader, username)
    }
    override fun getRemoteDataByEmail(email: String): Single<FUser> {
        return retrofitService.getFUserByUsername(Constants.authHeader, email)
    }

//    override fun getRemoteDataByParentId(parenId: Int): Single<List<FUser>> {
//        TODO("Not yet implemented")
//    }

    override fun getCacheData(): LiveData<List<FUser>> {
       return appDatabase.userDao.getAllFUserLive
    }

    override fun getCacheDataById(id: Int): LiveData<FUser> {
        return appDatabase.userDao.getAllByIdLive(id)
    }

    override fun getCacheDataByUsername(username: String): LiveData<FUser> {
        return appDatabase.userDao.getAllByUsernameLive(username)
    }

    override fun getCacheDataByEmail(email: String): LiveData<FUser> {
        return appDatabase.userDao.getAllByEmailLive(email)
    }

    override fun addCacheData(fUser: FUser) {
      appDatabase.userDao.insert(fUser)
    }

    override fun deleteAllCacheData() {
        appDatabase.userDao.deleteAllFUser()
    }

}