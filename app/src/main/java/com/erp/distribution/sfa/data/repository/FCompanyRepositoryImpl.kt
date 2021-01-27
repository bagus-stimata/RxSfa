package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFCompany
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCompanyRepository
import com.erp.distribution.sfa.data.source.entity.FCompanyEntity
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCompanyRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCompany
) : FCompanyRepository {

    override fun getRemoteAllFCompany(authHeader: String): Single<List<FCompanyEntity>> {
        return retrofitService.getRemoteAllFCompany(authHeader)
    }

    override fun getRemoteFCompanyById(authHeader: String, id: Int): Single<FCompanyEntity> {
        return retrofitService.getRemoteFCompanyById(authHeader, id)
    }

//    override fun getRemoteAllFCompanyByDivision(divisionId: Int): Single<List<FCompany>> {
//        return retrofitService.getRemoteAllFCompanyByDivision(authHeader, divisionId)
//    }

    override fun createRemoteFCompany(authHeader: String, fCompanyEntity: FCompanyEntity): Single<FCompanyEntity> {
        return retrofitService.createRemoteFCompany(authHeader, fCompanyEntity)
    }

    override fun putRemoteFCompany(authHeader: String, id: Int, fCompanyEntity: FCompanyEntity): Single<FCompanyEntity> {
        return retrofitService.putRemoteFCompany(authHeader, id, fCompanyEntity)
    }

    override fun deleteRemoteFCompany(authHeader: String, id: Int): Single<FCompanyEntity> {
        return retrofitService.deleteRemoteFCompany(authHeader, id)
    }



    override fun getCacheAllFCompany(): LiveData<List<FCompanyEntity>> {
        return appDatabase.companyDao.getAllFCompanyEntityLive
    }

    override fun getCacheFCompanyById(id: Int): LiveData<FCompanyEntity> {
        return appDatabase.companyDao.getAllByIdLive(id)
    }

//    override fun getCacheAllFCompanyByDivision(divisionId: Int): LiveData<List<FCompany>> {
//        return appDatabase.companyDao.getAllByDivisionLive(divisionId)
//    }

    override fun addCacheFCompany(fCompanyEntity: FCompanyEntity) {
        return appDatabase.companyDao.insert(fCompanyEntity)
    }
    override fun addCacheListFCompany(list: List<FCompanyEntity>) {
        return appDatabase.companyDao.insertAll(list)
    }

    override fun putCacheFCompany(fCompanyEntity: FCompanyEntity) {
        return appDatabase.companyDao.update(fCompanyEntity)
    }

    override fun deleteCacheFCompany(fCompanyEntity: FCompanyEntity) {
        return appDatabase.companyDao.delete(fCompanyEntity)
    }

    override fun deleteAllCacheFCompany() {
        return appDatabase.companyDao.deleteAllFCompany()
    }


//    override fun getRemoteAllData(): Single<List<FCompany>> {
//        return retrofitService.getRemoteAllFCompany(authHeader)
//    }


}