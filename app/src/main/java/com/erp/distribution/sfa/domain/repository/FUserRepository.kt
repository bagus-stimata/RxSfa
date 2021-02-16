package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.data.source.entity_security.FUserWithFDivisionAndSalesmanAndWarehouse
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FUserRepository {
    fun getRemoteAllFUser(authHeader: String): Single<List<FUserEntity>>
    fun getRemoteFUserById(authHeader: String, id: Int): Single<FUserEntity>
    fun getRemoteFUserByUsername(authHeader: String, username: String): Single<FUserEntity>
    fun getRemoteFUserByUsernamePassword(authHeader: String, username: String, password: String): Single<FUserEntity>
    fun getRemoteFUserByEmail(authHeader: String, email: String): Single<FUserEntity>
//    fun getRemoteDataByParentId(parenId: Int): Single<List<FUser>>
    fun createRemoteFUser(authHeader: String, fUserEntity: FUserEntity): Single<FUserEntity>
    fun putRemoteFUser(authHeader: String, id: Int, fUserEntity: FUserEntity): Single<FUserEntity>
    fun deleteRemoteFUser(authHeader: String, id: Int): Single<FUserEntity>
    

    fun getCacheAllFUser(): LiveData<List<FUserEntity>>
    fun getCacheAllFUserBiasa(): List<FUserEntity>
    fun getCacheFUserById(id: Int): LiveData<FUserEntity>
    fun getCacheFUserByUsername(username: String): LiveData<FUserEntity>
    fun getCacheFUserByEmail(email: String): LiveData<FUserEntity>

    fun createCacheFUser(fUserEntity: FUserEntity)
    fun putCacheFUser(fUserEntity: FUserEntity)
    fun deleteCacheFUser(fUserEntity: FUserEntity)
    
    fun deleteAllCacheFUser()

    fun getCacheAllFUserFlow(): Flow<List<FUserWithFDivisionAndSalesmanAndWarehouse>>
    fun getCacheAllFUserLive(): LiveData<List<FUserWithFDivisionAndSalesmanAndWarehouse>>



}