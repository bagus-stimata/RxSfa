package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFMaterialGroup1
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialGroup1Repository
import com.erp.distribution.sfa.model.FMaterialGroup1
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialGroup1RepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialGroup1
) : FMaterialGroup1Repository {

    override fun getRemoteAllFMaterialGroup1(): Single<List<FMaterialGroup1>> {
        return retrofitService.getRemoteAllFMaterialGroup1(MainApplication.authHeader)
    }

    override fun getRemoteFMaterialGroup1ById(id: Int): Single<FMaterialGroup1> {
        return retrofitService.getRemoteFMaterialGroup1ById(MainApplication.authHeader, id)
    }

    override fun getRemoteAllFMaterialGroup1ByDivision(divisionId: Int): Single<List<FMaterialGroup1>> {
        return retrofitService.getRemoteAllFMaterialGroup1ByDivision(MainApplication.authHeader, divisionId)
    }

    override fun createRemoteFMaterialGroup1(fMaterialGroup1: FMaterialGroup1): Single<FMaterialGroup1> {
        return retrofitService.createRemoteFMaterialGroup1(MainApplication.authHeader, fMaterialGroup1)
    }

    override fun putRemoteFMaterialGroup1(id: Int, fMaterialGroup1: FMaterialGroup1): Single<FMaterialGroup1> {
        return retrofitService.putRemoteFMaterialGroup1(MainApplication.authHeader, id, fMaterialGroup1)
    }

    override fun deleteRemoteFMaterialGroup1(id: Int): Single<FMaterialGroup1> {
        return retrofitService.deleteRemoteFMaterialGroup1(MainApplication.authHeader, id)
    }



    override fun getCacheAllFMaterialGroup1(): LiveData<List<FMaterialGroup1>> {
        return appDatabase.materialGroup1Dao.getAllFMaterialGroup1Live
    }

    override fun getCacheFMaterialGroup1ById(id: Int): LiveData<FMaterialGroup1> {
        return appDatabase.materialGroup1Dao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialGroup1ByDivision(divisionId: Int): LiveData<List<FMaterialGroup1>> {
        return appDatabase.materialGroup1Dao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFMaterialGroup1(fMaterialGroup1: FMaterialGroup1) {
        return appDatabase.materialGroup1Dao.insert(fMaterialGroup1)
    }

    override fun putCacheFMaterialGroup1(fMaterialGroup1: FMaterialGroup1) {
        return appDatabase.materialGroup1Dao.update(fMaterialGroup1)
    }

    override fun deleteCacheFMaterialGroup1(fMaterialGroup1: FMaterialGroup1) {
        return appDatabase.materialGroup1Dao.delete(fMaterialGroup1)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialGroup1Dao.deleteAllFMaterialGroup1()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialGroup1>> {
//        return retrofitService.getRemoteAllFMaterialGroup1(MainApplication.authHeader)
//    }


}