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

    override fun getRemoteAllFMaterialPic(): Single<List<FMaterialPic>> {
        return retrofitService.getRemoteAllFMaterialPic(MainApplication.authHeader)
    }

    override fun getRemoteFMaterialPicById(id: Int): Single<FMaterialPic> {
        return retrofitService.getRemoteFMaterialPicById(MainApplication.authHeader, id)
    }

    override fun getRemoteAllFMaterialPicByParent(parentId: Int): Single<List<FMaterialPic>> {
        return retrofitService.getRemoteAllFMaterialPicByParent(MainApplication.authHeader, parentId)
    }

    override fun createRemoteFMaterialPic(fMaterialPic: FMaterialPic): Single<FMaterialPic> {
        return retrofitService.createRemoteFMaterialPic(MainApplication.authHeader, fMaterialPic)
    }

    override fun putRemoteFMaterialPic(id: Int, fMaterialPic: FMaterialPic): Single<FMaterialPic> {
        return retrofitService.putRemoteFMaterialPic(MainApplication.authHeader, id, fMaterialPic)
    }

    override fun deleteRemoteFMaterialPic(id: Int): Single<FMaterialPic> {
        return retrofitService.deleteRemoteFMaterialPic(MainApplication.authHeader, id)
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
//        return retrofitService.getRemoteAllFMaterialPic(MainApplication.authHeader)
//    }


}