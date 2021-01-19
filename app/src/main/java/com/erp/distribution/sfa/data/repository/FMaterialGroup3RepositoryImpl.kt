package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFMaterialGroup3
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialGroup3Repository
import com.erp.distribution.sfa.model.FMaterialGroup3
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialGroup3RepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialGroup3
) : FMaterialGroup3Repository {

    override fun getRemoteAllFMaterialGroup3(): Single<List<FMaterialGroup3>> {
        return retrofitService.getRemoteAllFMaterialGroup3(Constants.authHeader)
    }

    override fun getRemoteFMaterialGroup3ById(id: Int): Single<FMaterialGroup3> {
        return retrofitService.getRemoteFMaterialGroup3ById(Constants.authHeader, id)
    }

    override fun getRemoteAllFMaterialGroup3ByParent(parentId: Int): Single<List<FMaterialGroup3>> {
        return retrofitService.getRemoteAllFMaterialGroup3ByParent(Constants.authHeader, parentId)
    }

    override fun createRemoteFMaterialGroup3(fMaterialGroup3: FMaterialGroup3): Single<FMaterialGroup3> {
        return retrofitService.createRemoteFMaterialGroup3(Constants.authHeader, fMaterialGroup3)
    }

    override fun putRemoteFMaterialGroup3(id: Int, fMaterialGroup3: FMaterialGroup3): Single<FMaterialGroup3> {
        return retrofitService.putRemoteFMaterialGroup3(Constants.authHeader, id, fMaterialGroup3)
    }

    override fun deleteRemoteFMaterialGroup3(id: Int): Single<FMaterialGroup3> {
        return retrofitService.deleteRemoteFMaterialGroup3(Constants.authHeader, id)
    }



    override fun getCacheAllFMaterialGroup3(): LiveData<List<FMaterialGroup3>> {
        return appDatabase.materialGroup3Dao.getAllFMaterialGroup3Live
    }

    override fun getCacheFMaterialGroup3ById(id: Int): LiveData<FMaterialGroup3> {
        return appDatabase.materialGroup3Dao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialGroup3ByParent(divisionId: Int): LiveData<List<FMaterialGroup3>> {
        return appDatabase.materialGroup3Dao.getAllByParentLive(divisionId)
    }

    override fun addCacheFMaterialGroup3(fMaterialGroup3: FMaterialGroup3) {
        return appDatabase.materialGroup3Dao.insert(fMaterialGroup3)
    }

    override fun putCacheFMaterialGroup3(fMaterialGroup3: FMaterialGroup3) {
        return appDatabase.materialGroup3Dao.update(fMaterialGroup3)
    }

    override fun deleteCacheFMaterialGroup3(fMaterialGroup3: FMaterialGroup3) {
        return appDatabase.materialGroup3Dao.delete(fMaterialGroup3)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialGroup3Dao.deleteAllFMaterialGroup3()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialGroup3>> {
//        return retrofitService.getRemoteAllFMaterialGroup3(Constants.authHeader)
//    }


}