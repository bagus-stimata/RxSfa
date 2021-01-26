package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterial
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialRepository
import com.erp.distribution.sfa.data.source.entity.FMaterial
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterial
) : FMaterialRepository {

    override fun getRemoteAllFMaterial(authHeader: String): Single<List<FMaterial>> {
        return retrofitService.getRemoteAllFMaterial(authHeader)
    }

    override fun getRemoteFMaterialById(authHeader: String, id: Int): Single<FMaterial> {
        return retrofitService.getRemoteFMaterialById(authHeader, id)
    }

    override fun getRemoteAllFMaterialByDivision(authHeader: String, divisionId: Int): Single<List<FMaterial>> {
        return retrofitService.getRemoteAllFMaterialByDivision(authHeader, divisionId)
    }

    override fun createRemoteFMaterial(authHeader: String, fMaterial: FMaterial): Single<FMaterial> {
        return retrofitService.createRemoteFMaterial(authHeader, fMaterial)
    }

    override fun putRemoteFMaterial(authHeader: String, id: Int, fMaterial: FMaterial): Single<FMaterial> {
        return retrofitService.putRemoteFMaterial(authHeader, id, fMaterial)
    }

    override fun deleteRemoteFMaterial(authHeader: String, id: Int): Single<FMaterial> {
        return retrofitService.deleteRemoteFMaterial(authHeader, id)
    }



    override fun getCacheAllFMaterial(): LiveData<List<FMaterial>> {
        return appDatabase.materialDao.getAllFMaterialLive
    }
    override fun getCacheAllFMaterialFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterial>> {
        return appDatabase.materialDao.getAllFMaterialFlow(query, sortOrder, hideSelected)
    }

    override fun getCacheFMaterialById(id: Int): LiveData<FMaterial> {
        return appDatabase.materialDao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterial>> {
        return appDatabase.materialDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFMaterial(fMaterial: FMaterial) {
        return appDatabase.materialDao.insert(fMaterial)
    }
    override fun addCacheListFMaterial(list: List<FMaterial>) {
        return appDatabase.materialDao.insertAll(list)
    }

    override fun putCacheFMaterial(fMaterial: FMaterial) {
        return appDatabase.materialDao.update(fMaterial)
    }

    override fun deleteCacheFMaterial(fMaterial: FMaterial) {
        return appDatabase.materialDao.delete(fMaterial)
    }

    override fun deleteAllCacheFMaterial() {
        return appDatabase.materialDao.deleteAllFMaterial()
    }


}