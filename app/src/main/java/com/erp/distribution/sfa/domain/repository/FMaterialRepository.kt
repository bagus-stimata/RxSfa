package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FMaterial
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialRepository {
    fun getRemoteAllFMaterial(): Single<List<FMaterial>>
    fun getRemoteFMaterialById(id: Int): Single<FMaterial>
    fun getRemoteAllFMaterialByDivision(divisionId: Int): Single<List<FMaterial>>
    fun createRemoteFMaterial(fMaterial: FMaterial): Single<FMaterial>
    fun putRemoteFMaterial(id: Int, fMaterial: FMaterial): Single<FMaterial>
    fun deleteRemoteFMaterial(id: Int): Single<FMaterial>

    fun getCacheAllFMaterial(): LiveData<List<FMaterial>>
    fun getCacheFMaterialById(id: Int): LiveData<FMaterial>
    fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterial>>
    fun addCacheFMaterial(fMaterial: FMaterial)
    fun putCacheFMaterial(fMaterial: FMaterial)
    fun deleteCacheFMaterial(fMaterial: FMaterial)
    fun deleteAllCacheData()


}