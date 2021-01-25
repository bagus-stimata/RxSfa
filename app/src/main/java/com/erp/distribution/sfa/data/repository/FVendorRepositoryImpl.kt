package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFVendor
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FVendorRepository
import com.erp.distribution.sfa.data.source.entity.FVendor
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FVendorRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFVendor
) : FVendorRepository {

    override fun getRemoteAllFVendor(authHeader: String): Single<List<FVendor>> {
        return retrofitService.getRemoteAllFVendor(authHeader)
    }

    override fun getRemoteFVendorById(authHeader: String, id: Int): Single<FVendor> {
        return retrofitService.getRemoteFVendorById(authHeader, id)
    }

    override fun getRemoteAllFVendorByDivision(authHeader: String, divisionId: Int): Single<List<FVendor>> {
        return retrofitService.getRemoteAllFVendorByDivision(authHeader, divisionId)
    }

    override fun createRemoteFVendor(authHeader: String, fVendor: FVendor): Single<FVendor> {
        return retrofitService.createRemoteFVendor(authHeader, fVendor)
    }

    override fun putRemoteFVendor(authHeader: String, id: Int, fVendor: FVendor): Single<FVendor> {
        return retrofitService.putRemoteFVendor(authHeader, id, fVendor)
    }

    override fun deleteRemoteFVendor(authHeader: String, id: Int): Single<FVendor> {
        return retrofitService.deleteRemoteFVendor(authHeader, id)
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
//        return retrofitService.getRemoteAllFVendor(authHeader)
//    }


}