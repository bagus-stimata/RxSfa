package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFCustomerPic
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerPicRepository
import com.erp.distribution.sfa.data.source.entity.FCustomerPicEntity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerPicRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomerPic
) : FCustomerPicRepository {

    override fun getRemoteAllFCustomerPic(authHeader: String): Single<List<FCustomerPicEntity>> {
        return retrofitService.getRemoteAllFCustomerPic(authHeader)
    }

    override fun getRemoteFCustomerPicById(authHeader: String, id: Int): Single<FCustomerPicEntity> {
        return retrofitService.getRemoteFCustomerPicById(authHeader, id)
    }

    override fun getRemoteAllFCustomerPicByParent(authHeader: String, parentId: Int): Single<List<FCustomerPicEntity>> {
        return retrofitService.getRemoteAllFCustomerPicByParent(authHeader, parentId)
    }

    override fun createRemoteFCustomerPic(authHeader: String, fCustomerPicEntity: FCustomerPicEntity): Single<FCustomerPicEntity> {
        return retrofitService.createRemoteFCustomerPic(authHeader, fCustomerPicEntity)
    }

    override fun putRemoteFCustomerPic(authHeader: String, id: Int, fCustomerPicEntity: FCustomerPicEntity): Single<FCustomerPicEntity> {
        return retrofitService.putRemoteFCustomerPic(authHeader, id, fCustomerPicEntity)
    }

    override fun deleteRemoteFCustomerPic(authHeader: String, id: Int): Single<FCustomerPicEntity> {
        return retrofitService.deleteRemoteFCustomerPic(authHeader, id)
    }



    override fun getCacheAllFCustomerPic(): LiveData<List<FCustomerPicEntity>> {
        return appDatabase.customerPicDao.getAllFCustomerPicEntityLive
    }

    override fun getCacheFCustomerPicById(id: Int): LiveData<FCustomerPicEntity> {
        return appDatabase.customerPicDao.getAllByIdLive(id)
    }

    override fun getCacheAllFCustomerPicByParent(divisionId: Int): LiveData<List<FCustomerPicEntity>> {
        return appDatabase.customerPicDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFCustomerPic(fCustomerPicEntity: FCustomerPicEntity) {
        return appDatabase.customerPicDao.insert(fCustomerPicEntity)
    }

    override fun putCacheFCustomerPic(fCustomerPicEntity: FCustomerPicEntity) {
        return appDatabase.customerPicDao.update(fCustomerPicEntity)
    }

    override fun deleteCacheFCustomerPic(fCustomerPicEntity: FCustomerPicEntity) {
        return appDatabase.customerPicDao.delete(fCustomerPicEntity)
    }

    override fun deleteAllCacheData() {
        return appDatabase.customerPicDao.deleteAllFCustomerPic()
    }


//    override fun getRemoteAllData(): Single<List<FCustomerPic>> {
//        return retrofitService.getRemoteAllFCustomerPic(authHeader)
//    }


}