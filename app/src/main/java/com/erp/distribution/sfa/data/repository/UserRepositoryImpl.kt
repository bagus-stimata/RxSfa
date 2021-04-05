package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceSecurity
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FUserRepository
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.data.source.entity_security.FUserWithFDivisionAndSalesmanAndWarehouse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class UserRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceSecurity
) :
    FUserRepository {


    override fun getRemoteAllFUser(authHeader: String): Single<List<FUserEntity>> {
        return retrofitService.getAllData(authHeader)
//        return retrofitService.getAllData(SecurityUtil.getAuthHeader("bagus", "hacker"))
    }

    override fun getRemoteFUserById(authHeader: String, id: Int): Single<FUserEntity> {
        return retrofitService.getFUserById( authHeader, id)
    }

    override fun getRemoteFUserByUsername(authHeader: String, username: String): Single<FUserEntity> {
//        Log.d("UserRepository", "#result ${authHeader}")
        return retrofitService.getFUserByUsername(authHeader, username)
//        return retrofitService.getFUserByUsername(SecurityUtil.getAuthHeader("bagus", "hacker"), username)
    }
    override fun getRemoteFUserByUsernamePassword(authHeader: String, username: String, password: String): Single<FUserEntity> {
        return retrofitService.getFUserByUsernamePassword(authHeader, username, password)
    }
    override fun getRemoteFUserByEmail(authHeader: String, email: String): Single<FUserEntity> {
        return retrofitService.getFUserByEmail(authHeader, email)
    }
    override fun createRemoteFUser(authHeader: String, fUserEntity: FUserEntity): Single<FUserEntity> {
        return retrofitService.createRemoteFUser(authHeader, fUserEntity)
    }

    override fun putRemoteFUser(authHeader: String, id: Int, fUserEntity: FUserEntity): Single<FUserEntity> {
        return retrofitService.putRemoteFUser(authHeader, id, fUserEntity)
    }

    override fun deleteRemoteFUser(authHeader: String, id: Int): Single<FUserEntity> {
        return retrofitService.deleteRemoteFUser(authHeader, id)
    }


    override fun getCacheAllFUser(): LiveData<List<FUserEntity>> {
       return appDatabase.userDao.getAllFUserLive()
    }
    override fun getCacheAllFUserBiasa(): List<FUserEntity> {
        return appDatabase.userDao.getAllFUserEntity
    }

    override fun getCacheFUserById(id: Int): LiveData<FUserEntity> {
        return appDatabase.userDao.getAllByIdLive(id)
    }

    override fun getCacheFUserByUsername(username: String): LiveData<FUserEntity> {
        return appDatabase.userDao.getAllByUsernameLive(username)
    }

    override fun getCacheFUserByEmail(email: String): LiveData<FUserEntity> {
        return appDatabase.userDao.getAllByEmailLive(email)
    }

    override fun createCacheFUser(fUserEntity: FUserEntity) {
      appDatabase.userDao.insert(fUserEntity)
    }
    override fun putCacheFUser(fUserEntity: FUserEntity) {
        appDatabase.userDao.update(fUserEntity)
    }
    override fun deleteCacheFUser(fUserEntity: FUserEntity) {
        appDatabase.userDao.delete(fUserEntity)
    }

    override fun deleteAllCacheFUser() {
        appDatabase.userDao.deleteAllFUser()
    }

    override fun getCacheAllFUserFlow(): Flow<List<FUserWithFDivisionAndSalesmanAndWarehouse>> {
        return appDatabase.userDao.getAllFUserFlow()
    }

    override fun getCacheAllFUserLive(): LiveData<List<FUserWithFDivisionAndSalesmanAndWarehouse>> {
        return appDatabase.userDao.getAllFUserLiveData()
    }
}