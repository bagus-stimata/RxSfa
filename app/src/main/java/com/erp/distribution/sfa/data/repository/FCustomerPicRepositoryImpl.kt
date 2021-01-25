package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFCustomerPic
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerPicRepository
import com.erp.distribution.sfa.data.source.entity.FCustomerPic
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerPicRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomerPic
) : FCustomerPicRepository {

    override fun getRemoteAllFCustomerPic(authHeader: String): Single<List<FCustomerPic>> {
        return retrofitService.getRemoteAllFCustomerPic(authHeader)
    }

    override fun getRemoteFCustomerPicById(authHeader: String, id: Int): Single<FCustomerPic> {
        return retrofitService.getRemoteFCustomerPicById(authHeader, id)
    }

    override fun getRemoteAllFCustomerPicByParent(authHeader: String, parentId: Int): Single<List<FCustomerPic>> {
        return retrofitService.getRemoteAllFCustomerPicByParent(authHeader, parentId)
    }

    override fun createRemoteFCustomerPic(authHeader: String, fCustomerPic: FCustomerPic): Single<FCustomerPic> {
        return retrofitService.createRemoteFCustomerPic(authHeader, fCustomerPic)
    }

    override fun putRemoteFCustomerPic(authHeader: String, id: Int, fCustomerPic: FCustomerPic): Single<FCustomerPic> {
        return retrofitService.putRemoteFCustomerPic(authHeader, id, fCustomerPic)
    }

    override fun deleteRemoteFCustomerPic(authHeader: String, id: Int): Single<FCustomerPic> {
        return retrofitService.deleteRemoteFCustomerPic(authHeader, id)
    }



    override fun getCacheAllFCustomerPic(): LiveData<List<FCustomerPic>> {
        return appDatabase.customerPicDao.getAllFCustomerPicLive
    }

    override fun getCacheFCustomerPicById(id: Int): LiveData<FCustomerPic> {
        return appDatabase.customerPicDao.getAllByIdLive(id)
    }

    override fun getCacheAllFCustomerPicByParent(divisionId: Int): LiveData<List<FCustomerPic>> {
        return appDatabase.customerPicDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFCustomerPic(fCustomerPic: FCustomerPic) {
        return appDatabase.customerPicDao.insert(fCustomerPic)
    }

    override fun putCacheFCustomerPic(fCustomerPic: FCustomerPic) {
        return appDatabase.customerPicDao.update(fCustomerPic)
    }

    override fun deleteCacheFCustomerPic(fCustomerPic: FCustomerPic) {
        return appDatabase.customerPicDao.delete(fCustomerPic)
    }

    override fun deleteAllCacheData() {
        return appDatabase.customerPicDao.deleteAllFCustomerPic()
    }


//    override fun getRemoteAllData(): Single<List<FCustomerPic>> {
//        return retrofitService.getRemoteAllFCustomerPic(authHeader)
//    }


}