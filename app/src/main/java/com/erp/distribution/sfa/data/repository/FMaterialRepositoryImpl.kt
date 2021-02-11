package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterial
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialRepository
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialWithFDivisionAndVendorAndGroup
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.toEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterial
) : FMaterialRepository {

    override fun getRemoteAllFMaterial(authHeader: String): Single<List<FMaterialEntity>> {
        return retrofitService.getRemoteAllFMaterial(authHeader)
    }

    override fun getRemoteFMaterialById(authHeader: String, id: Int): Single<FMaterialEntity> {
        return retrofitService.getRemoteFMaterialById(authHeader, id)
    }

    override fun getRemoteAllFMaterialByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialEntity>> {
        return retrofitService.getRemoteAllFMaterialByDivision(authHeader, divisionId)
    }
    override fun getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterialEntity>> {
        return retrofitService.getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun createRemoteFMaterial(authHeader: String, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity> {
        return retrofitService.createRemoteFMaterial(authHeader, fMaterialEntity)
    }

    override fun putRemoteFMaterial(authHeader: String, id: Int, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity> {
        return retrofitService.putRemoteFMaterial(authHeader, id, fMaterialEntity)
    }

    override fun deleteRemoteFMaterial(authHeader: String, id: Int): Single<FMaterialEntity> {
        return retrofitService.deleteRemoteFMaterial(authHeader, id)
    }



    override fun getCacheAllFMaterial(): LiveData<List<FMaterialEntity>> {
        return appDatabase.materialDao.getAllFMaterialEntityLive
    }
    override fun getCacheAllFMaterialFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialWithFDivisionAndVendorAndGroup>> {
        return appDatabase.materialDao.getAllFMaterialFlow(query, sortOrder, hideSelected)
    }



    override fun getCacheFMaterialById(id: Int): LiveData<FMaterialEntity> {
        return appDatabase.materialDao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterialEntity>> {
        return appDatabase.materialDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFMaterialDomain(fMaterial: FMaterial) {
        return appDatabase.materialDao.insert(fMaterial.toEntity())
    }
    override fun addCacheFMaterial(fMaterialEntity: FMaterialEntity) {
        return appDatabase.materialDao.insert(fMaterialEntity)
    }
    override fun addCacheListFMaterial(list: List<FMaterialEntity>) {
        return appDatabase.materialDao.insertAll(list)
    }

    override fun putCacheFMaterial(fMaterialEntity: FMaterialEntity) {
        return appDatabase.materialDao.update(fMaterialEntity)
    }
    override fun putCacheFMaterialDomain(fmaterial: FMaterial) {
        return appDatabase.materialDao.update(fmaterial.toEntity())
    }

    override fun deleteCacheFMaterial(fMaterialEntity: FMaterialEntity) {
        return appDatabase.materialDao.delete(fMaterialEntity)
    }
    override fun deleteCacheFMaterialDomain(fmaterial: FMaterial) {
        return appDatabase.materialDao.delete(fmaterial.toEntity())
    }

    override fun deleteAllCacheFMaterial() {
        return appDatabase.materialDao.deleteAllFMaterial()
    }


}