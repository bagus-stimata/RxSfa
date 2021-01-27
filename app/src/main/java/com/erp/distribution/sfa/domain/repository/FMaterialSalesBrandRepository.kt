package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FMaterialSalesBrandEntity
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialSalesBrandRepository {
    fun getRemoteAllFMaterialSalesBrand(authHeader: String): Single<List<FMaterialSalesBrandEntity>>
    fun getRemoteFMaterialSalesBrandById(authHeader: String, id: Int): Single<FMaterialSalesBrandEntity>
    fun getRemoteAllFMaterialSalesBrandByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialSalesBrandEntity>>
    fun createRemoteFMaterialSalesBrand(authHeader: String, fMaterialSalesBrandEntity: FMaterialSalesBrandEntity): Single<FMaterialSalesBrandEntity>
    fun putRemoteFMaterialSalesBrand(authHeader: String, id: Int, fMaterialSalesBrandEntity: FMaterialSalesBrandEntity): Single<FMaterialSalesBrandEntity>
    fun deleteRemoteFMaterialSalesBrand(authHeader: String, id: Int): Single<FMaterialSalesBrandEntity>

    fun getCacheAllFMaterialSalesBrand(): LiveData<List<FMaterialSalesBrandEntity>>
    fun getCacheFMaterialSalesBrandById(id: Int): LiveData<FMaterialSalesBrandEntity>
    fun getCacheAllFMaterialSalesBrandByDivision(divisionId: Int): LiveData<List<FMaterialSalesBrandEntity>>
    fun addCacheFMaterialSalesBrand(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity)
    fun putCacheFMaterialSalesBrand(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity)
    fun deleteCacheFMaterialSalesBrand(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity)
    fun deleteAllCacheData()


}