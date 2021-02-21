package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFtSalesh
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FtSaleshRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FtSaleshRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFtSalesh,
) : FtSaleshRepository {

    override fun getRemoteAllFtSalesh(authHeader: String ): Single<List<FtSaleshEntity>> {
        return retrofitService.getRemoteAllFtSalesh(authHeader)
    }

    override fun getRemoteFtSaleshById(authHeader: String, id: Long): Single<FtSaleshEntity> {
        return retrofitService.getRemoteFtSaleshById(authHeader, id)
    }

    override fun getRemoteAllFtSaleshByDivision(authHeader: String, divisionId: Int): Single<List<FtSaleshEntity>> {
        return retrofitService.getRemoteAllFtSaleshByDivision(authHeader, divisionId)
    }

    override fun createRemoteFtSalesh(authHeader: String, ftSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity> {
        return retrofitService.createRemoteFtSalesh(authHeader, ftSaleshEntity)
    }

    override fun putRemoteFtSalesh(authHeader: String, id: Long, ftSaleshEntity: FtSaleshEntity): Single<FtSaleshEntity> {
        return retrofitService.putRemoteFtSalesh(authHeader, id, ftSaleshEntity)
    }

    override fun deleteRemoteFtSalesh(authHeader: String, id: Long): Single<FtSaleshEntity> {
        return retrofitService.deleteRemoteFtSalesh(authHeader, id)
    }



    override fun getCacheAllFtSalesh(): LiveData<List<FtSaleshEntity>> {
        return appDatabase.saleshDao.getAllFtSaleshEntityLive
    }

//    override fun getCacheAllFtSaleshFlow(
//        query: String,
//        sortOrder: SortOrder,
//        hideSelected: Boolean
//    ): Flow<List<FtSaleshEntity>> {
//        return appDatabase.saleshDao.getAllFtSaleshFlow(query, sortOrder, hideSelected)
//    }
//    override fun getCacheAllFtSaleshDomainFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FtSalesh>> {
//        return appDatabase.saleshDao.getAllFtSaleshFlow(query, sortOrder, hideSelected)
//            .map { data ->
//                data.map {
//
//                    it.toDomain()
//                }
//            }
//    }

    override fun getCacheAllFtSaleshFlow(query: String, sortOrder: SortOrder,  limit: Int, currentOffset: Int, hideSelected: Boolean): Flow<List<FtSaleshWithFDivisionAndFCustomer>> {
        return appDatabase.saleshDao.getAllFtSaleshFlow(query, sortOrder, limit, currentOffset, hideSelected)
    }

    override fun getCacheAllFtSaleshLive(): LiveData<List<FtSaleshWithFDivisionAndFCustomer>> {
        return appDatabase.saleshDao.getAllFtSaleshLive()
    }


    override fun getCacheFtSaleshWithItemsByIdFlow(id: Long): Flow<FtSaleshWithFDivisionAndFSalesmanAndFCustomerAndItems> {
        return appDatabase.saleshDao.getFtSaleshWithItemsByIdFLow(id)
    }
    override fun getCacheFtSaleshWithItemsByIdLive(id: Long): LiveData<FtSaleshWithFDivisionAndFCustomer> {
        return appDatabase.saleshDao.getFtSaleshWithItemsByIdLive(id)
    }

    override fun getCacheFtSaleshById(id: Long): LiveData<FtSaleshEntity> {
        return appDatabase.saleshDao.getAllByIdLive(id)
    }

    override fun getCacheAllFtSaleshByDivision(divisionId: Int): LiveData<List<FtSaleshEntity>> {
        return appDatabase.saleshDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFtSalesh(ftSaleshEntity: FtSaleshEntity) {
        return appDatabase.saleshDao.insert(ftSaleshEntity)
    }

    override fun addCacheListFtSalesh(list: List<FtSaleshEntity>) {
        return appDatabase.saleshDao.insertAll(list)
    }

    override fun putCacheFtSalesh(ftSaleshEntity: FtSaleshEntity) {
        return appDatabase.saleshDao.update(ftSaleshEntity)
    }

    override fun deleteCacheFtSalesh(ftSaleshEntity: FtSaleshEntity) {
        return appDatabase.saleshDao.delete(ftSaleshEntity)
    }

    override fun deleteAllCacheFtSalesh() {
        return appDatabase.saleshDao.deleteAllFtSalesh()
    }



}