package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterialGroup3
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialGroup3Repository
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FMaterialGroup3
import com.erp.distribution.sfa.domain.model.toEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialGroup3RepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialGroup3
) : FMaterialGroup3Repository {

    override fun getRemoteAllFMaterialGroup3(authHeader: String ): Single<List<FMaterialGroup3Entity>> {
        return retrofitService.getRemoteAllFMaterialGroup3(authHeader)
    }

    override fun getRemoteFMaterialGroup3ById(authHeader: String, id: Int): Single<FMaterialGroup3Entity> {
        return retrofitService.getRemoteFMaterialGroup3ById(authHeader, id)
    }

    override fun getRemoteAllFMaterialGroup3ByParent(authHeader: String, parentId: Int): Single<List<FMaterialGroup3Entity>> {
        return retrofitService.getRemoteAllFMaterialGroup3ByParent(authHeader, parentId)
    }

    override fun createRemoteFMaterialGroup3(authHeader: String, fMaterialGroup3Entity: FMaterialGroup3Entity): Single<FMaterialGroup3Entity> {
        return retrofitService.createRemoteFMaterialGroup3(authHeader, fMaterialGroup3Entity)
    }

    override fun putRemoteFMaterialGroup3(authHeader: String, id: Int, fMaterialGroup3Entity: FMaterialGroup3Entity): Single<FMaterialGroup3Entity> {
        return retrofitService.putRemoteFMaterialGroup3(authHeader, id, fMaterialGroup3Entity)
    }

    override fun deleteRemoteFMaterialGroup3(authHeader: String, id: Int): Single<FMaterialGroup3Entity> {
        return retrofitService.deleteRemoteFMaterialGroup3(authHeader, id)
    }



    override fun getCacheAllFMaterialGroup3(): LiveData<List<FMaterialGroup3Entity>> {
        return appDatabase.materialGroup3Dao.getAllFMaterialGroup3EntityLive
    }

    override fun getCacheAllFMaterialGroup3DomainFlow(): Flow<List<FMaterialGroup3>> {
        return appDatabase.materialGroup3Dao.getAllFMaterialGroup3EntityFlow
                .map { data ->
                    data.map {
                        it.toDomain()
                    }
                }
    }

    override fun getCacheFMaterialGroup3ById(id: Int): LiveData<FMaterialGroup3Entity> {
        return appDatabase.materialGroup3Dao.getAllByIdLive(id)
    }
    override fun getCacheFMaterialGroup3ByIdDomainFlow(id: Int): Flow<FMaterialGroup3> {
        return appDatabase.materialGroup3Dao.getAllByIdEntityFlow(id)
                .map {
                        it.toDomain()
                }

    }

    override fun getCacheAllFMaterialGroup3ByParent(divisionId: Int): LiveData<List<FMaterialGroup3Entity>> {
        return appDatabase.materialGroup3Dao.getAllByParentLive(divisionId)
    }
    override fun getCacheAllFMaterialGroup3ByParentDomainFlow(parentId: Int): Flow<List<FMaterialGroup3>> {
        return appDatabase.materialGroup3Dao.getAllByParentFlow(parentId)
                .map { data ->
                    data.map {
                        it.toDomain()
                    }
                }

    }

    override fun addCacheListFMaterialGroup3(list: List<FMaterialGroup3Entity>) {
        return appDatabase.materialGroup3Dao.insertAll(list)
    }

    override fun addCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity) {
        return appDatabase.materialGroup3Dao.insert(fMaterialGroup3Entity)
    }
    override fun addCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3) {
        return appDatabase.materialGroup3Dao.insert(fMaterialGroup3.toEntity())
    }

    override fun putCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity) {
        return appDatabase.materialGroup3Dao.update(fMaterialGroup3Entity)
    }
    override fun putCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3) {
        return appDatabase.materialGroup3Dao.update(fMaterialGroup3.toEntity())
    }

    override fun deleteCacheFMaterialGroup3(fMaterialGroup3Entity: FMaterialGroup3Entity) {
        return appDatabase.materialGroup3Dao.delete(fMaterialGroup3Entity)
    }
    override fun deleteCacheFMaterialGroup3Domain(fMaterialGroup3: FMaterialGroup3) {
        return appDatabase.materialGroup3Dao.delete(fMaterialGroup3.toEntity())
    }

    override fun deleteAllCacheFMaterialGroup3() {
        return appDatabase.materialGroup3Dao.deleteAllFMaterialGroup3()
    }




}