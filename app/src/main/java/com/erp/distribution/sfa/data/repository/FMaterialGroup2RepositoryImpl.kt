package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterialGroup2
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialGroup2Repository
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup2Entity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialGroup2RepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialGroup2
) : FMaterialGroup2Repository {

    override fun getRemoteAllFMaterialGroup2(authHeader: String): Single<List<FMaterialGroup2Entity>> {
        return retrofitService.getRemoteAllFMaterialGroup2(authHeader)
    }

    override fun getRemoteFMaterialGroup2ById(authHeader: String, id: Int): Single<FMaterialGroup2Entity> {
        return retrofitService.getRemoteFMaterialGroup2ById(authHeader, id)
    }

    override fun getRemoteAllFMaterialGroup2ByParent(authHeader: String, parentId: Int): Single<List<FMaterialGroup2Entity>> {
        return retrofitService.getRemoteAllFMaterialGroup2ByParent(authHeader, parentId)
    }

    override fun createRemoteFMaterialGroup2(authHeader: String, fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity> {
        return retrofitService.createRemoteFMaterialGroup2(authHeader, fMaterialGroup2Entity)
    }

    override fun putRemoteFMaterialGroup2(authHeader: String, id: Int, fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity> {
        return retrofitService.putRemoteFMaterialGroup2(authHeader, id, fMaterialGroup2Entity)
    }

    override fun deleteRemoteFMaterialGroup2(authHeader: String, id: Int): Single<FMaterialGroup2Entity> {
        return retrofitService.deleteRemoteFMaterialGroup2(authHeader, id)
    }



    override fun getCacheAllFMaterialGroup2(): LiveData<List<FMaterialGroup2Entity>> {
        return appDatabase.materialGroup2Dao.getAllFMaterialGroup2EntityLive
    }

    override fun getCacheFMaterialGroup2ById(id: Int): LiveData<FMaterialGroup2Entity> {
        return appDatabase.materialGroup2Dao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialGroup2ByParent(divisionId: Int): LiveData<List<FMaterialGroup2Entity>> {
        return appDatabase.materialGroup2Dao.getAllByParentLive(divisionId)
    }

    override fun addCacheListFMaterialGroup2(list: List<FMaterialGroup2Entity>) {
        return appDatabase.materialGroup2Dao.insertAll(list)
    }
    override fun addCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity) {
        return appDatabase.materialGroup2Dao.insert(fMaterialGroup2Entity)
    }

    override fun putCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity) {
        return appDatabase.materialGroup2Dao.update(fMaterialGroup2Entity)
    }

    override fun deleteCacheFMaterialGroup2(fMaterialGroup2Entity: FMaterialGroup2Entity) {
        return appDatabase.materialGroup2Dao.delete(fMaterialGroup2Entity)
    }

    override fun deleteAllCacheFMaterialGroup2() {
        return appDatabase.materialGroup2Dao.deleteAllFMaterialGroup2()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialGroup2>> {
//        return retrofitService.getRemoteAllFMaterialGroup2(authHeader)
//    }


}