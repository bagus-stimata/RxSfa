package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFCompany
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FCompanyRepository
import com.erp.distribution.sfa.model.FCompany
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FCompanyRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFCompany
) : FCompanyRepository {

    override fun getRemoteAllFCompany(): Single<List<FCompany>> {
        return retrofitService.getRemoteAllFCompany(MainApplication.authHeader)
    }

    override fun getRemoteFCompanyById(id: Int): Single<FCompany> {
        return retrofitService.getRemoteFCompanyById(MainApplication.authHeader, id)
    }

//    override fun getRemoteAllFCompanyByDivision(divisionId: Int): Single<List<FCompany>> {
//        return retrofitService.getRemoteAllFCompanyByDivision(MainApplication.authHeader, divisionId)
//    }

    override fun createRemoteFCompany(fCompany: FCompany): Single<FCompany> {
        return retrofitService.createRemoteFCompany(MainApplication.authHeader, fCompany)
    }

    override fun putRemoteFCompany(id: Int, fCompany: FCompany): Single<FCompany> {
        return retrofitService.putRemoteFCompany(MainApplication.authHeader, id, fCompany)
    }

    override fun deleteRemoteFCompany(id: Int): Single<FCompany> {
        return retrofitService.deleteRemoteFCompany(MainApplication.authHeader, id)
    }



    override fun getCacheAllFCompany(): LiveData<List<FCompany>> {
        return appDatabase.companyDao.getAllFCompanyLive
    }

    override fun getCacheFCompanyById(id: Int): LiveData<FCompany> {
        return appDatabase.companyDao.getAllByIdLive(id)
    }

//    override fun getCacheAllFCompanyByDivision(divisionId: Int): LiveData<List<FCompany>> {
//        return appDatabase.companyDao.getAllByDivisionLive(divisionId)
//    }

    override fun addCacheFCompany(fCompany: FCompany) {
        return appDatabase.companyDao.insert(fCompany)
    }

    override fun putCacheFCompany(fCompany: FCompany) {
        return appDatabase.companyDao.update(fCompany)
    }

    override fun deleteCacheFCompany(fCompany: FCompany) {
        return appDatabase.companyDao.delete(fCompany)
    }

    override fun deleteAllCacheData() {
        return appDatabase.companyDao.deleteAllFCompany()
    }


//    override fun getRemoteAllData(): Single<List<FCompany>> {
//        return retrofitService.getRemoteAllFCompany(MainApplication.authHeader)
//    }


}