package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFTax
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FTaxRepository
import com.erp.distribution.sfa.data.source.entity.FTaxEntity
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FTaxRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFTax
) : FTaxRepository {

    override fun getRemoteAllFTax(authHeader: String ): Single<List<FTaxEntity>> {
        return retrofitService.getRemoteAllFTax(authHeader)
    }

    override fun getRemoteFTaxById(authHeader: String, id: Int): Single<FTaxEntity> {
        return retrofitService.getRemoteFTaxById(authHeader, id)
    }

    override fun getRemoteAllFTaxByDivision(authHeader: String, divisionId: Int): Single<List<FTaxEntity>> {
        return retrofitService.getRemoteAllFTaxByDivision(authHeader, divisionId)
    }

    override fun createRemoteFTax(authHeader: String, fTaxEntity: FTaxEntity): Single<FTaxEntity> {
        return retrofitService.createRemoteFTax(authHeader, fTaxEntity)
    }

    override fun putRemoteFTax(authHeader: String, id: Int, fTaxEntity: FTaxEntity): Single<FTaxEntity> {
        return retrofitService.putRemoteFTax(authHeader, id, fTaxEntity)
    }

    override fun deleteRemoteFTax(authHeader: String, id: Int): Single<FTaxEntity> {
        return retrofitService.deleteRemoteFTax(authHeader, id)
    }



    override fun getCacheAllFTax(): LiveData<List<FTaxEntity>> {
        return appDatabase.taxDao.getAllFTaxEntityLive
    }

    override fun getCacheFTaxById(id: Int): LiveData<FTaxEntity> {
        return appDatabase.taxDao.getAllByIdLive(id)
    }

    override fun getCacheAllFTaxByDivision(divisionId: Int): LiveData<List<FTaxEntity>> {
        return appDatabase.taxDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFTax(fTaxEntity: FTaxEntity) {
        return appDatabase.taxDao.insert(fTaxEntity)
    }

    override fun putCacheFTax(fTaxEntity: FTaxEntity) {
        return appDatabase.taxDao.update(fTaxEntity)
    }

    override fun deleteCacheFTax(fTaxEntity: FTaxEntity) {
        return appDatabase.taxDao.delete(fTaxEntity)
    }

    override fun deleteAllCacheData() {
        return appDatabase.taxDao.deleteAllFTax()
    }


//    override fun getRemoteAllData(): Single<List<FTax>> {
//        return retrofitService.getRemoteAllFTax(authHeader)
//    }


}