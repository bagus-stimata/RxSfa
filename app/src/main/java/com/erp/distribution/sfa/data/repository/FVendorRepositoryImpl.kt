package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFVendor
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FVendorRepository
import com.erp.distribution.sfa.data.source.entity.FVendorEntity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FVendorRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFVendor
) : FVendorRepository {

    override fun getRemoteAllFVendor(authHeader: String): Single<List<FVendorEntity>> {
        return retrofitService.getRemoteAllFVendor(authHeader)
    }

    override fun getRemoteFVendorById(authHeader: String, id: Int): Single<FVendorEntity> {
        return retrofitService.getRemoteFVendorById(authHeader, id)
    }

    override fun getRemoteAllFVendorByDivision(authHeader: String, divisionId: Int): Single<List<FVendorEntity>> {
        return retrofitService.getRemoteAllFVendorByDivision(authHeader, divisionId)
    }

    override fun getRemoteAllFVendorByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FVendorEntity>> {
        return retrofitService.getRemoteAllFVendorByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun createRemoteFVendor(authHeader: String, fVendorEntity: FVendorEntity): Single<FVendorEntity> {
        return retrofitService.createRemoteFVendor(authHeader, fVendorEntity)
    }

    override fun putRemoteFVendor(authHeader: String, id: Int, fVendorEntity: FVendorEntity): Single<FVendorEntity> {
        return retrofitService.putRemoteFVendor(authHeader, id, fVendorEntity)
    }

    override fun deleteRemoteFVendor(authHeader: String, id: Int): Single<FVendorEntity> {
        return retrofitService.deleteRemoteFVendor(authHeader, id)
    }



    override fun getCacheAllFVendor(): LiveData<List<FVendorEntity>> {
        return appDatabase.vendorDao.getAllFVendorEntityLive
    }

    override fun getCacheFVendorById(id: Int): LiveData<FVendorEntity> {
        return appDatabase.vendorDao.getAllByIdLive(id)
    }

    override fun getCacheAllFVendorByDivision(divisionId: Int): LiveData<List<FVendorEntity>> {
        return appDatabase.vendorDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFVendor(fVendorEntity: FVendorEntity) {
        return appDatabase.vendorDao.insert(fVendorEntity)
    }

    override fun putCacheFVendor(fVendorEntity: FVendorEntity) {
        return appDatabase.vendorDao.update(fVendorEntity)
    }

    override fun deleteCacheFVendor(fVendorEntity: FVendorEntity) {
        return appDatabase.vendorDao.delete(fVendorEntity)
    }

    override fun deleteAllCacheData() {
        return appDatabase.vendorDao.deleteAllFVendor()
    }


//    override fun getRemoteAllData(): Single<List<FVendor>> {
//        return retrofitService.getRemoteAllFVendor(authHeader)
//    }


}