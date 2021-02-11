package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterialPic
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialPicRepository
import com.erp.distribution.sfa.data.source.entity.FMaterialPicEntity
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialPicRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialPic
) : FMaterialPicRepository {

    override fun getRemoteAllFMaterialPic(authHeader: String): Single<List<FMaterialPicEntity>> {
        return retrofitService.getRemoteAllFMaterialPic(authHeader)
    }

    override fun getRemoteFMaterialPicById(authHeader: String, id: Int): Single<FMaterialPicEntity> {
        return retrofitService.getRemoteFMaterialPicById(authHeader, id)
    }

    override fun getRemoteAllFMaterialPicByParent(authHeader: String, parentId: Int): Single<List<FMaterialPicEntity>> {
        return retrofitService.getRemoteAllFMaterialPicByParent(authHeader, parentId)
    }

    override fun createRemoteFMaterialPic(authHeader: String, fMaterialPicEntity: FMaterialPicEntity): Single<FMaterialPicEntity> {
        return retrofitService.createRemoteFMaterialPic(authHeader, fMaterialPicEntity)
    }

    override fun putRemoteFMaterialPic(authHeader: String, id: Int, fMaterialPicEntity: FMaterialPicEntity): Single<FMaterialPicEntity> {
        return retrofitService.putRemoteFMaterialPic(authHeader, id, fMaterialPicEntity)
    }

    override fun deleteRemoteFMaterialPic(authHeader: String, id: Int): Single<FMaterialPicEntity> {
        return retrofitService.deleteRemoteFMaterialPic(authHeader, id)
    }



    override fun getCacheAllFMaterialPic(): LiveData<List<FMaterialPicEntity>> {
        return appDatabase.materialPicDao.getAllFMaterialPicEntityLive
    }

    override fun getCacheFMaterialPicById(id: Int): LiveData<FMaterialPicEntity> {
        return appDatabase.materialPicDao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialPicByParent(divisionId: Int): LiveData<List<FMaterialPicEntity>> {
        return appDatabase.materialPicDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFMaterialPic(fMaterialPicEntity: FMaterialPicEntity) {
        return appDatabase.materialPicDao.insert(fMaterialPicEntity)
    }

    override fun putCacheFMaterialPic(fMaterialPicEntity: FMaterialPicEntity) {
        return appDatabase.materialPicDao.update(fMaterialPicEntity)
    }

    override fun deleteCacheFMaterialPic(fMaterialPicEntity: FMaterialPicEntity) {
        return appDatabase.materialPicDao.delete(fMaterialPicEntity)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialPicDao.deleteAllFMaterialPic()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialPic>> {
//        return retrofitService.getRemoteAllFMaterialPic(authHeader)
//    }


}