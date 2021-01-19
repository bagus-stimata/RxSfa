package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
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

    override fun getRemoteAllFTax(): Single<List<FTax>> {
        return retrofitService.getRemoteAllFTax(Constants.authHeader)
    }

    override fun getRemoteFTaxById(id: Int): Single<FTax> {
        return retrofitService.getRemoteFTaxById(Constants.authHeader, id)
    }

    override fun getRemoteAllFTaxByDivision(divisionId: Int): Single<List<FTax>> {
        return retrofitService.getRemoteAllFTaxByDivision(Constants.authHeader, divisionId)
    }

    override fun createRemoteFTax(fTax: FTax): Single<FTax> {
        return retrofitService.createRemoteFTax(Constants.authHeader, fTax)
    }

    override fun putRemoteFTax(id: Int, fTax: FTax): Single<FTax> {
        return retrofitService.putRemoteFTax(Constants.authHeader, id, fTax)
    }

    override fun deleteRemoteFTax(id: Int): Single<FTax> {
        return retrofitService.deleteRemoteFTax(Constants.authHeader, id)
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
//        return retrofitService.getRemoteAllFTax(Constants.authHeader)
//    }


}