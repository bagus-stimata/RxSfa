package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFCustomerPic
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCustomerPicRepository
import com.erp.distribution.sfa.model.FCustomerPic
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCustomerPicRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCustomerPic
) : FCustomerPicRepository {

    override fun getRemoteAllFCustomerPic(): Single<List<FCustomerPic>> {
        return retrofitService.getRemoteAllFCustomerPic(Constants.authHeader)
    }

    override fun getRemoteFCustomerPicById(id: Int): Single<FCustomerPic> {
        return retrofitService.getRemoteFCustomerPicById(Constants.authHeader, id)
    }

    override fun getRemoteAllFCustomerPicByParent(parentId: Int): Single<List<FCustomerPic>> {
        return retrofitService.getRemoteAllFCustomerPicByParent(Constants.authHeader, parentId)
    }

    override fun createRemoteFCustomerPic(fCustomerPic: FCustomerPic): Single<FCustomerPic> {
        return retrofitService.createRemoteFCustomerPic(Constants.authHeader, fCustomerPic)
    }

    override fun putRemoteFCustomerPic(id: Int, fCustomerPic: FCustomerPic): Single<FCustomerPic> {
        return retrofitService.putRemoteFCustomerPic(Constants.authHeader, id, fCustomerPic)
    }

    override fun deleteRemoteFCustomerPic(id: Int): Single<FCustomerPic> {
        return retrofitService.deleteRemoteFCustomerPic(Constants.authHeader, id)
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
//        return retrofitService.getRemoteAllFCustomerPic(Constants.authHeader)
//    }


}