package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.model.FMaterialSalesBrand
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FMaterialSalesBrandRepository {
    fun getRemoteAllFMaterialSalesBrand(authHeader: String): Single<List<FMaterialSalesBrand>>
    fun getRemoteFMaterialSalesBrandById(authHeader: String, id: Int): Single<FMaterialSalesBrand>
    fun getRemoteAllFMaterialSalesBrandByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialSalesBrand>>
    fun createRemoteFMaterialSalesBrand(authHeader: String, fMaterialSalesBrand: FMaterialSalesBrand): Single<FMaterialSalesBrand>
    fun putRemoteFMaterialSalesBrand(authHeader: String, id: Int, fMaterialSalesBrand: FMaterialSalesBrand): Single<FMaterialSalesBrand>
    fun deleteRemoteFMaterialSalesBrand(authHeader: String, id: Int): Single<FMaterialSalesBrand>

    fun getCacheAllFMaterialSalesBrand(): LiveData<List<FMaterialSalesBrand>>
    fun getCacheFMaterialSalesBrandById(id: Int): LiveData<FMaterialSalesBrand>
    fun getCacheAllFMaterialSalesBrandByDivision(divisionId: Int): LiveData<List<FMaterialSalesBrand>>
    fun addCacheFMaterialSalesBrand(fMaterialSalesBrand: FMaterialSalesBrand)
    fun putCacheFMaterialSalesBrand(fMaterialSalesBrand: FMaterialSalesBrand)
    fun deleteCacheFMaterialSalesBrand(fMaterialSalesBrand: FMaterialSalesBrand)
    fun deleteAllCacheData()


}