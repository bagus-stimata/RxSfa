package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceSecurity
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FUserRepository
import com.erp.distribution.sfa.data.source.entity_security.FUser
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class UserRepositoryImp(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceSecurity
) :
    FUserRepository {


    override fun getRemoteAllFUser(authHeader: String): Single<List<FUser>> {
        return retrofitService.getAllData(authHeader)
//        return retrofitService.getAllData(SecurityUtil.getAuthHeader("bagus", "hacker"))
    }

    override fun getRemoteFUserById(authHeader: String, id: Int): Single<FUser> {
        return retrofitService.getFUserById( authHeader, id)
    }

    override fun getRemoteFUserByUsername(authHeader: String, username: String): Single<FUser> {
//        Log.d("UserRepository", "#result ${authHeader}")
        return retrofitService.getFUserByUsername(authHeader, username)
//        return retrofitService.getFUserByUsername(SecurityUtil.getAuthHeader("bagus", "hacker"), username)
    }
    override fun getRemoteFUserByUsernamePassword(authHeader: String, username: String, password: String): Single<FUser> {
        return retrofitService.getFUserByUsernamePassword(authHeader, username, password)
    }
    override fun getRemoteFUserByEmail(authHeader: String, email: String): Single<FUser> {
        return retrofitService.getFUserByEmail(authHeader, email)
    }
    override fun createRemoteFUser(authHeader: String, fUser: FUser): Single<FUser> {
        return retrofitService.createRemoteFUser(authHeader, fUser)
    }

    override fun putRemoteFUser(authHeader: String, id: Int, fUser: FUser): Single<FUser> {
        return retrofitService.putRemoteFUser(authHeader, id, fUser)
    }

    override fun deleteRemoteFUser(authHeader: String, id: Int): Single<FUser> {
        return retrofitService.deleteRemoteFUser(authHeader, id)
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