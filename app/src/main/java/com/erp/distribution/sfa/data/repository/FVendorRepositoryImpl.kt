package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFVendor
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FVendorRepository
import com.erp.distribution.sfa.model.FVendor
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FVendorRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFVendor
) : FVendorRepository {

    override fun getRemoteAllFVendor(): Single<List<FVendor>> {
        return retrofitService.getRemoteAllFVendor(Constants.authHeader)
    }

    override fun getRemoteFVendorById(id: Int): Single<FVendor> {
        return retrofitService.getRemoteFVendorById(Constants.authHeader, id)
    }

    override fun getRemoteAllFVendorByDivision(divisionId: Int): Single<List<FVendor>> {
        return retrofitService.getRemoteAllFVendorByDivision(Constants.authHeader, divisionId)
    }

    override fun createRemoteFVendor(fVendor: FVendor): Single<FVendor> {
        return retrofitService.createRemoteFVendor(Constants.authHeader, fVendor)
    }

    override fun putRemoteFVendor(id: Int, fVendor: FVendor): Single<FVendor> {
        return retrofitService.putRemoteFVendor(Constants.authHeader, id, fVendor)
    }

    override fun deleteRemoteFVendor(id: Int): Single<FVendor> {
        return retrofitService.deleteRemoteFVendor(Constants.authHeader, id)
    }



    override fun getCacheAllFVendor(): LiveData<List<FVendor>> {
        return appDatabase.vendorDao.getAllFVendorLive
    }

    override fun getCacheFVendorById(id: Int): LiveData<FVendor> {
        return appDatabase.vendorDao.getAllByIdLive(id)
    }

    override fun getCacheAllFVendorByDivision(divisionId: Int): LiveData<List<FVendor>> {
        return appDatabase.vendorDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFVendor(fVendor: FVendor) {
        return appDatabase.vendorDao.insert(fVendor)
    }

    override fun putCacheFVendor(fVendor: FVendor) {
        return appDatabase.vendorDao.update(fVendor)
    }

    override fun deleteCacheFVendor(fVendor: FVendor) {
        return appDatabase.vendorDao.delete(fVendor)
    }

    override fun deleteAllCacheData() {
        return appDatabase.vendorDao.deleteAllFVendor()
    }


//    override fun getRemoteAllData(): Single<List<FVendor>> {
//        return retrofitService.getRemoteAllFVendor(Constants.authHeader)
//    }


}