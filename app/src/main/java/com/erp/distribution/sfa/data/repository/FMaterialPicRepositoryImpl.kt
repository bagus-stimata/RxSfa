package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFMaterialPic
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialPicRepository
import com.erp.distribution.sfa.model.FMaterialPic
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialPicRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialPic
) : FMaterialPicRepository {

    override fun getRemoteAllFMaterialPic(authHeader: String): Single<List<FMaterialPic>> {
        return retrofitService.getRemoteAllFMaterialPic(authHeader)
    }

    override fun getRemoteFMaterialPicById(authHeader: String, id: Int): Single<FMaterialPic> {
        return retrofitService.getRemoteFMaterialPicById(authHeader, id)
    }

    override fun getRemoteAllFMaterialPicByParent(authHeader: String, parentId: Int): Single<List<FMaterialPic>> {
        return retrofitService.getRemoteAllFMaterialPicByParent(authHeader, parentId)
    }

    override fun createRemoteFMaterialPic(authHeader: String, fMaterialPic: FMaterialPic): Single<FMaterialPic> {
        return retrofitService.createRemoteFMaterialPic(authHeader, fMaterialPic)
    }

    override fun putRemoteFMaterialPic(authHeader: String, id: Int, fMaterialPic: FMaterialPic): Single<FMaterialPic> {
        return retrofitService.putRemoteFMaterialPic(authHeader, id, fMaterialPic)
    }

    override fun deleteRemoteFMaterialPic(authHeader: String, id: Int): Single<FMaterialPic> {
        return retrofitService.deleteRemoteFMaterialPic(authHeader, id)
    }



    override fun getCacheAllFMaterialPic(): LiveData<List<FMaterialPic>> {
        return appDatabase.materialPicDao.getAllFMaterialPicLive
    }

    override fun getCacheFMaterialPicById(id: Int): LiveData<FMaterialPic> {
        return appDatabase.materialPicDao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialPicByParent(divisionId: Int): LiveData<List<FMaterialPic>> {
        return appDatabase.materialPicDao.getAllByParentLive(divisionId)
    }

    override fun addCacheFMaterialPic(fMaterialPic: FMaterialPic) {
        return appDatabase.materialPicDao.insert(fMaterialPic)
    }

    override fun putCacheFMaterialPic(fMaterialPic: FMaterialPic) {
        return appDatabase.materialPicDao.update(fMaterialPic)
    }

    override fun deleteCacheFMaterialPic(fMaterialPic: FMaterialPic) {
        return appDatabase.materialPicDao.delete(fMaterialPic)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialPicDao.deleteAllFMaterialPic()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialPic>> {
//        return retrofitService.getRemoteAllFMaterialPic(authHeader)
//    }


}