package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceSecurity
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FUserRepository
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
    FUserRepository {


    override fun getRemoteAllFUser(): Single<List<FUser>> {
        return retrofitService.getAllData(Constants.authHeader)
    }

    override fun getRemoteFUserById(id: Int): Single<FUser> {
        return retrofitService.getFUserById(Constants.authHeader, id)
    }

    override fun getRemoteFUserByUsername(username: String): Single<FUser> {
        return retrofitService.getFUserByUsername(Constants.authHeader, username)
    }
    override fun getRemoteFUserByEmail(email: String): Single<FUser> {
        return retrofitService.getFUserByEmail(Constants.authHeader, email)
    }
    override fun createRemoteFUser(fUser: FUser): Single<FUser> {
        return retrofitService.createRemoteFUser(Constants.authHeader, fUser)
    }

    override fun putRemoteFUser(id: Int, fUser: FUser): Single<FUser> {
        return retrofitService.putRemoteFUser(Constants.authHeader, id, fUser)
    }

    override fun deleteRemoteFUser(id: Int): Single<FUser> {
        return retrofitService.deleteRemoteFUser(Constants.authHeader, id)
    }
    
//    override fun getRemoteDataByParentId(parenId: Int): Single<List<FUser>> {
//        TODO("Not yet implemented")
//    }

    override fun getCacheAllFUser(): LiveData<List<FUser>> {
       return appDatabase.userDao.getAllFUserLive()
    }
    override fun getCacheAllFUserBiasa(): List<FUser> {
        return appDatabase.userDao.getAllFUser
    }

    override fun getCacheFUserById(id: Int): LiveData<FUser> {
        return appDatabase.userDao.getAllByIdLive(id)
    }

    override fun getCacheFUserByUsername(username: String): LiveData<FUser> {
        return appDatabase.userDao.getAllByUsernameLive(username)
    }

    override fun getCacheFUserByEmail(email: String): LiveData<FUser> {
        return appDatabase.userDao.getAllByEmailLive(email)
    }

    override fun createCacheFUser(fUser: FUser) {
      appDatabase.userDao.insert(fUser)
    }
    override fun putCacheFUser(fUser: FUser) {
        appDatabase.userDao.update(fUser)
    }
    override fun deleteCacheFUser(fUser: FUser) {
        appDatabase.userDao.delete(fUser)
    }

    override fun deleteAllCacheFUser() {
        appDatabase.userDao.deleteAllFUser()
    }

}