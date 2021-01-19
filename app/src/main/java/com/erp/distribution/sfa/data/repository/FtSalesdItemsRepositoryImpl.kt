package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFtSalesdItems
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FtSalesdItemsRepository
import com.erp.distribution.sfa.model.FtSalesdItems
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FtSalesdItemsRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFtSalesdItems
) : FtSalesdItemsRepository {

    override fun getRemoteAllFtSalesdItems(): Single<List<FtSalesdItems>> {
        return retrofitService.getRemoteAllFtSalesdItems(Constants.authHeader)
    }

    override fun getRemoteFtSalesdItemsById(id: Long): Single<FtSalesdItems> {
        return retrofitService.getRemoteFtSalesdItemsById(Constants.authHeader, id)
    }

    override fun getRemoteAllFtSalesdItemsByParent(parentId: Long): Single<List<FtSalesdItems>> {
        return retrofitService.getRemoteAllFtSalesdItemsByParent(Constants.authHeader, parentId)
    }

    override fun createRemoteFtSalesdItems(ftSalesdItems: FtSalesdItems): Single<FtSalesdItems> {
        return retrofitService.createRemoteFtSalesdItems(Constants.authHeader, ftSalesdItems)
    }

    override fun putRemoteFtSalesdItems(id: Long, ftSalesdItems: FtSalesdItems): Single<FtSalesdItems> {
        return retrofitService.putRemoteFtSalesdItems(Constants.authHeader, id, ftSalesdItems)
    }

    override fun deleteRemoteFtSalesdItems(id: Long): Single<FtSalesdItems> {
        return retrofitService.deleteRemoteFtSalesdItems(Constants.authHeader, id)
    }



    override fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItems>> {
        return appDatabase.salesdItemsDao.getAllFtSalesdItemsLive
    }

    override fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItems> {
        return appDatabase.salesdItemsDao.getAllByIdLive(id)
    }

    override fun getCacheAllFtSalesdItemsByParent(parentId: Long): LiveData<List<FtSalesdItems>> {
        return appDatabase.salesdItemsDao.getAllByFtSaleshLive(parentId)
    }

    override fun addCacheFtSalesdItems(ftSalesdItems: FtSalesdItems) {
        return appDatabase.salesdItemsDao.insert(ftSalesdItems)
    }

    override fun putCacheFtSalesdItems(ftSalesdItems: FtSalesdItems) {
        return appDatabase.salesdItemsDao.update(ftSalesdItems)
    }

    override fun deleteCacheFtSalesdItems(ftSalesdItems: FtSalesdItems) {
        return appDatabase.salesdItemsDao.delete(ftSalesdItems)
    }

    override fun deleteAllCacheData() {
        return appDatabase.salesdItemsDao.deleteAllFtSalesdItems()
    }


//    override fun getRemoteAllData(): Single<List<FtSalesdItems>> {
//        return retrofitService.getRemoteAllFtSalesdItems(Constants.authHeader)
//    }


}