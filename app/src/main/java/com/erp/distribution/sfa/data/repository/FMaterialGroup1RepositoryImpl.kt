package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterialGroup1
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialGroup1Repository
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup1
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialGroup1RepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialGroup1
) : FMaterialGroup1Repository {

    override fun getRemoteAllFMaterialGroup1(authHeader: String, ): Single<List<FMaterialGroup1>> {
        return retrofitService.getRemoteAllFMaterialGroup1(authHeader)
    }

    override fun getRemoteFMaterialGroup1ById(authHeader: String, id: Int): Single<FMaterialGroup1> {
        return retrofitService.getRemoteFMaterialGroup1ById(authHeader, id)
    }

    override fun getRemoteAllFMaterialGroup1ByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialGroup1>> {
        return retrofitService.getRemoteAllFMaterialGroup1ByDivision(authHeader, divisionId)
    }

    override fun createRemoteFMaterialGroup1(authHeader: String, fMaterialGroup1: FMaterialGroup1): Single<FMaterialGroup1> {
        return retrofitService.createRemoteFMaterialGroup1(authHeader, fMaterialGroup1)
    }

    override fun putRemoteFMaterialGroup1(authHeader: String, id: Int, fMaterialGroup1: FMaterialGroup1): Single<FMaterialGroup1> {
        return retrofitService.putRemoteFMaterialGroup1(authHeader, id, fMaterialGroup1)
    }

    override fun deleteRemoteFMaterialGroup1(authHeader: String, id: Int): Single<FMaterialGroup1> {
        return retrofitService.deleteRemoteFMaterialGroup1(authHeader, id)
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
//        return retrofitService.getRemoteAllFMaterialGroup1(authHeader)
//    }


}