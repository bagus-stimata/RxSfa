package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialWithFDivisionAndVendorAndGroup
import com.erp.distribution.sfa.domain.model.FMaterial
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialRepository {
    fun getRemoteAllFMaterial(authHeader: String, ): Single<List<FMaterialEntity>>
    fun getRemoteFMaterialById(authHeader: String, id: Int): Single<FMaterialEntity>
    fun getRemoteAllFMaterialByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialEntity>>
    fun getRemoteAllFMaterialByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterialEntity>>
    fun createRemoteFMaterial(authHeader: String, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity>
    fun putRemoteFMaterial(authHeader: String, id: Int, fMaterialEntity: FMaterialEntity): Single<FMaterialEntity>
    fun deleteRemoteFMaterial(authHeader: String, id: Int): Single<FMaterialEntity>

    fun getCacheAllFMaterial(): LiveData<List<FMaterialEntity>>
    fun getCacheAllFMaterialFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterialWithFDivisionAndVendorAndGroup>>
    fun getCacheFMaterialById(id: Int): LiveData<FMaterialEntity>
    fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterialEntity>>
    fun addCacheFMaterial(fMaterialEntity: FMaterialEntity)
    fun addCacheFMaterialDomain(fMaterial: FMaterial)
    fun addCacheListFMaterial(list: List<FMaterialEntity>)
    fun putCacheFMaterial(fMaterialEntity: FMaterialEntity)
    fun putCacheFMaterialDomain(fmaterial: FMaterial)
    fun deleteCacheFMaterial(fMaterialEntity: FMaterialEntity)
    fun deleteCacheFMaterialDomain(fmaterial: FMaterial)
    fun deleteAllCacheFMaterial()


}