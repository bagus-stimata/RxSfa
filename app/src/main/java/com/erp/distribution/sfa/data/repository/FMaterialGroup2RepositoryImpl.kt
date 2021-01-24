package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFMaterialGroup2
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialGroup2Repository
import com.erp.distribution.sfa.model.FMaterialGroup2
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialGroup2RepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialGroup2
) : FMaterialGroup2Repository {

    override fun getRemoteAllFMaterialGroup2(authHeader: String): Single<List<FMaterialGroup2>> {
        return retrofitService.getRemoteAllFMaterialGroup2(authHeader)
    }

    override fun getRemoteFMaterialGroup2ById(authHeader: String, id: Int): Single<FMaterialGroup2> {
        return retrofitService.getRemoteFMaterialGroup2ById(authHeader, id)
    }

    override fun getRemoteAllFMaterialGroup2ByParent(authHeader: String, parentId: Int): Single<List<FMaterialGroup2>> {
        return retrofitService.getRemoteAllFMaterialGroup2ByParent(authHeader, parentId)
    }

    override fun createRemoteFMaterialGroup2(authHeader: String, fMaterialGroup2: FMaterialGroup2): Single<FMaterialGroup2> {
        return retrofitService.createRemoteFMaterialGroup2(authHeader, fMaterialGroup2)
    }

    override fun putRemoteFMaterialGroup2(authHeader: String, id: Int, fMaterialGroup2: FMaterialGroup2): Single<FMaterialGroup2> {
        return retrofitService.putRemoteFMaterialGroup2(authHeader, id, fMaterialGroup2)
    }

    override fun deleteRemoteFMaterialGroup2(authHeader: String, id: Int): Single<FMaterialGroup2> {
        return retrofitService.deleteRemoteFMaterialGroup2(authHeader, id)
    }



    override fun getCacheAllFMaterialGroup2(): LiveData<List<FMaterialGroup2>> {
        return appDatabase.materialGroup2Dao.getAllFMaterialGroup2Live
    }

    override fun getCacheFMaterialGroup2ById(id: Int): LiveData<FMaterialGroup2> {
        return appDatabase.materialGroup2Dao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialGroup2ByParent(divisionId: Int): LiveData<List<FMaterialGroup2>> {
        return appDatabase.materialGroup2Dao.getAllByParentLive(divisionId)
    }

    override fun addCacheFMaterialGroup2(fMaterialGroup2: FMaterialGroup2) {
        return appDatabase.materialGroup2Dao.insert(fMaterialGroup2)
    }

    override fun putCacheFMaterialGroup2(fMaterialGroup2: FMaterialGroup2) {
        return appDatabase.materialGroup2Dao.update(fMaterialGroup2)
    }

    override fun deleteCacheFMaterialGroup2(fMaterialGroup2: FMaterialGroup2) {
        return appDatabase.materialGroup2Dao.delete(fMaterialGroup2)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialGroup2Dao.deleteAllFMaterialGroup2()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialGroup2>> {
//        return retrofitService.getRemoteAllFMaterialGroup2(authHeader)
//    }


}