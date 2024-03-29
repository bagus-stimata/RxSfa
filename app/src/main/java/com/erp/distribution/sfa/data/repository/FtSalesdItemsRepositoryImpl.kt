package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFtSalesdItems
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FtSalesdItemsRepository
import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtSalesdWithFMaterial
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FtSalesdItemsRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFtSalesdItems
) : FtSalesdItemsRepository {

    override fun getRemoteAllFtSalesdItems(authHeader: String): Single<List<FtSalesdItemsEntity>> {
        return retrofitService.getRemoteAllFtSalesdItems(authHeader)
    }

    override fun getRemoteFtSalesdItemsById(authHeader: String, id: Long): Single<FtSalesdItemsEntity> {
        return retrofitService.getRemoteFtSalesdItemsById(authHeader, id)
    }

    override fun getRemoteAllFtSalesdItemsByFtSalesh(authHeader: String, ftSaleshBean: Long): Single<List<FtSalesdItemsEntity>> {
        return retrofitService.getRemoteAllFtSalesdItemsByFtSalesh(authHeader, ftSaleshBean)
    }

    override fun createRemoteFtSalesdItems(authHeader: String, ftSalesdItemsEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity> {
        return retrofitService.createRemoteFtSalesdItems(authHeader, ftSalesdItemsEntity)
    }
    override fun createRemoteListFtSalesdItems(authHeader: String, listFtSalesdItemsEntity: List<FtSalesdItemsEntity>): Single<List<FtSalesdItemsEntity>> {
        return retrofitService.createRemoteListFtSalesdItems(authHeader, listFtSalesdItemsEntity)
    }
    override fun putRemoteFtSalesdItems(authHeader: String, id: Long, ftSalesdItemsEntity: FtSalesdItemsEntity): Single<FtSalesdItemsEntity> {
        return retrofitService.putRemoteFtSalesdItems(authHeader, id, ftSalesdItemsEntity)
    }
    override fun deleteRemoteFtSalesdItems(authHeader: String, id: Long): Single<FtSalesdItemsEntity> {
        return retrofitService.deleteRemoteFtSalesdItems(authHeader, id)
    }

    override fun getCacheAllFtSalesdItems(): LiveData<List<FtSalesdItemsEntity>> {
        return appDatabase.salesdItemsDao.getAllFtSalesdItemsEntityLive
    }









    override fun getCacheListFtSalesdItemsByFtSaleshAndMaterialLive(ftSalesBean: Long, fmaterialBean: Int): LiveData<List<FtSalesdWithFMaterial>> {
        return appDatabase.salesdItemsDao.getAllByFtSaleshAndMaterialLive(ftSalesBean, fmaterialBean)
    }

    override fun getCacheListFtSalesdItemsFtSaleshLive(ftSalesBean: Long): LiveData<List<FtSalesdWithFMaterial>> {
        return appDatabase.salesdItemsDao.getAllFtSalesdItemsByFtSaleshLive(ftSalesBean)
    }
    override fun getCacheListFtSalesdItemsByFtSalesdItemsByFMaterialLive(fmaterialBean: Int): LiveData<List<FtSalesdWithFMaterial>> {
        return appDatabase.salesdItemsDao.getAllFtSalesdItemsByFMaterialLive(fmaterialBean)
    }







    override fun getCacheFtSalesdItemsById(id: Long): LiveData<FtSalesdItemsEntity> {
        return appDatabase.salesdItemsDao.getAllByIdLive(id)
    }
    override fun getCacheAllFtSalesdItemsByParent(parentId: Long): LiveData<List<FtSalesdItemsEntity>> {
        return appDatabase.salesdItemsDao.getAllByFtSaleshLive(parentId)
    }
    override fun addCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity) {
        return appDatabase.salesdItemsDao.insert(ftSalesdItemsEntity)
    }
    override fun addCacheListFtSalesdItems(list: List<FtSalesdItemsEntity>) {
        return appDatabase.salesdItemsDao.insertAll(list)
    }

    override fun putCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity) {
        return appDatabase.salesdItemsDao.update(ftSalesdItemsEntity)
    }

    override fun deleteCacheFtSalesdItems(ftSalesdItemsEntity: FtSalesdItemsEntity) {
        return appDatabase.salesdItemsDao.delete(ftSalesdItemsEntity)
    }
    override fun deleteAllCacheFtSalesdItemsByFtSalesh(ftSaleshBean: Long) {
        return appDatabase.salesdItemsDao.deleteAllByFtSalesh(ftSaleshBean)
    }


    override fun deleteAllCacheFtsalesdItems() {
        return appDatabase.salesdItemsDao.deleteAllFtSalesdItems()
    }


}