package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterial
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialRepository {
    fun getRemoteAllFMaterial(authHeader: String, ): Single<List<FMaterial>>
    fun getRemoteFMaterialById(authHeader: String, id: Int): Single<FMaterial>
    fun getRemoteAllFMaterialByDivision(authHeader: String, divisionId: Int): Single<List<FMaterial>>
    fun createRemoteFMaterial(authHeader: String, fMaterial: FMaterial): Single<FMaterial>
    fun putRemoteFMaterial(authHeader: String, id: Int, fMaterial: FMaterial): Single<FMaterial>
    fun deleteRemoteFMaterial(authHeader: String, id: Int): Single<FMaterial>

    fun getCacheAllFMaterial(): LiveData<List<FMaterial>>
    fun getCacheAllFMaterialFlow(query: String, sortOrder: SortOrder, hideSelected: Boolean): Flow<List<FMaterial>>
    fun getCacheFMaterialById(id: Int): LiveData<FMaterial>
    fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterial>>
    fun addCacheFMaterial(fMaterial: FMaterial)
    fun addCacheListFMaterial(list: List<FMaterial>)
    fun putCacheFMaterial(fMaterial: FMaterial)
    fun deleteCacheFMaterial(fMaterial: FMaterial)
    fun deleteAllCacheFMaterial()


}