package com.erp.distribution.sfa.domain.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface FtPriceAltdItemsRepository {
//    fun getRemoteAllFtPriceAltdItems(authHeader: String): Single<List<FtPriceAltdItemsEntity>>
//    fun getRemoteFtPriceAltdItemsById(authHeader: String, id: Int): Single<FtPriceAltdItemsEntity>
//    fun getRemoteAllFtPriceAltdItemsByDivision(authHeader: String, divisionId: Int): Single<List<FtPriceAltdItemsEntity>>
    fun getRemoteAllFtPriceAltdItemsByFtPriceAlth(authHeader: String, ftPricehAlthBean: Int): Single<List<FtPriceAltdItemsEntity>>
//    fun createRemoteFtPriceAltdItems(authHeader: String, ftPriceAltdItemsEntity: FtPriceAltdItemsEntity): Single<FtPriceAltdItemsEntity>
//    fun putRemoteFtPriceAltdItems(authHeader: String, id: Int, ftPriceAltdItemsEntity: FtPriceAltdItemsEntity): Single<FtPriceAltdItemsEntity>
//    fun deleteRemoteFtPriceAltdItems(authHeader: String, id: Int): Single<FtPriceAltdItemsEntity>
//
    fun getCacheAllFtPriceAltdItems(): LiveData<List<FtPriceAltdItemsEntity>>
    fun getCacheAllFtPriceAltdItemsByParent(ftPriceAlthBean: Int): LiveData<List<FtPriceAltdItemsEntity>>
    fun getCacheAllFtPriceAltdItemsByFtPriceAlthAndFMaterial(ftPriceAlthBean: Int, fMaterialBean: Int): LiveData<List<FtPriceAltdItemsEntity>>
    fun addCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity)
    fun addCacheListFtPriceAltdItems(list: List<FtPriceAltdItemsEntity>)
    fun putCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity)
    fun deleteCacheFtPriceAltdItems(ftPriceAltdItemsEntity: FtPriceAltdItemsEntity)
    fun deleteAllCacheFtPriceAltdItems()


}