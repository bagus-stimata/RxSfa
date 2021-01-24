package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFTax
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FTaxRepository
import com.erp.distribution.sfa.model.FTax
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FTaxRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFTax
) : FTaxRepository {

    override fun getRemoteAllFTax(authHeader: String ): Single<List<FTax>> {
        return retrofitService.getRemoteAllFTax(authHeader)
    }

    override fun getRemoteFTaxById(authHeader: String, id: Int): Single<FTax> {
        return retrofitService.getRemoteFTaxById(authHeader, id)
    }

    override fun getRemoteAllFTaxByDivision(authHeader: String, divisionId: Int): Single<List<FTax>> {
        return retrofitService.getRemoteAllFTaxByDivision(authHeader, divisionId)
    }

    override fun createRemoteFTax(authHeader: String, fTax: FTax): Single<FTax> {
        return retrofitService.createRemoteFTax(authHeader, fTax)
    }

    override fun putRemoteFTax(authHeader: String, id: Int, fTax: FTax): Single<FTax> {
        return retrofitService.putRemoteFTax(authHeader, id, fTax)
    }

    override fun deleteRemoteFTax(authHeader: String, id: Int): Single<FTax> {
        return retrofitService.deleteRemoteFTax(authHeader, id)
    }



    override fun getCacheAllFTax(): LiveData<List<FTax>> {
        return appDatabase.taxDao.getAllFTaxLive
    }

    override fun getCacheFTaxById(id: Int): LiveData<FTax> {
        return appDatabase.taxDao.getAllByIdLive(id)
    }

    override fun getCacheAllFTaxByDivision(divisionId: Int): LiveData<List<FTax>> {
        return appDatabase.taxDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFTax(fTax: FTax) {
        return appDatabase.taxDao.insert(fTax)
    }

    override fun putCacheFTax(fTax: FTax) {
        return appDatabase.taxDao.update(fTax)
    }

    override fun deleteCacheFTax(fTax: FTax) {
        return appDatabase.taxDao.delete(fTax)
    }

    override fun deleteAllCacheData() {
        return appDatabase.taxDao.deleteAllFTax()
    }


//    override fun getRemoteAllData(): Single<List<FTax>> {
//        return retrofitService.getRemoteAllFTax(authHeader)
//    }


}