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

    override fun getRemoteAllFCompany(authHeader: String): Single<List<FCompany>> {
        return retrofitService.getRemoteAllFCompany(authHeader)
    }

    override fun getRemoteFCompanyById(authHeader: String, id: Int): Single<FCompany> {
        return retrofitService.getRemoteFCompanyById(authHeader, id)
    }

//    override fun getRemoteAllFCompanyByDivision(divisionId: Int): Single<List<FCompany>> {
//        return retrofitService.getRemoteAllFCompanyByDivision(authHeader, divisionId)
//    }

    override fun createRemoteFCompany(authHeader: String, fCompany: FCompany): Single<FCompany> {
        return retrofitService.createRemoteFCompany(authHeader, fCompany)
    }

    override fun putRemoteFCompany(authHeader: String, id: Int, fCompany: FCompany): Single<FCompany> {
        return retrofitService.putRemoteFCompany(authHeader, id, fCompany)
    }

    override fun deleteRemoteFCompany(authHeader: String, id: Int): Single<FCompany> {
        return retrofitService.deleteRemoteFCompany(authHeader, id)
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
//        return retrofitService.getRemoteAllFCompany(authHeader)
//    }


}