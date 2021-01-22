package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFMaterial
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialRepository
import com.erp.distribution.sfa.model.FMaterial
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterial
) : FMaterialRepository {

    override fun getRemoteAllFMaterial(): Single<List<FMaterial>> {
        return retrofitService.getRemoteAllFMaterial(MainApplication.authHeader)
    }

    override fun getRemoteFMaterialById(id: Int): Single<FMaterial> {
        return retrofitService.getRemoteFMaterialById(MainApplication.authHeader, id)
    }

    override fun getRemoteAllFMaterialByDivision(divisionId: Int): Single<List<FMaterial>> {
        return retrofitService.getRemoteAllFMaterialByDivision(MainApplication.authHeader, divisionId)
    }

    override fun createRemoteFMaterial(fMaterial: FMaterial): Single<FMaterial> {
        return retrofitService.createRemoteFMaterial(MainApplication.authHeader, fMaterial)
    }

    override fun putRemoteFMaterial(id: Int, fMaterial: FMaterial): Single<FMaterial> {
        return retrofitService.putRemoteFMaterial(MainApplication.authHeader, id, fMaterial)
    }

    override fun deleteRemoteFMaterial(id: Int): Single<FMaterial> {
        return retrofitService.deleteRemoteFMaterial(MainApplication.authHeader, id)
    }



    override fun getCacheAllFMaterial(): LiveData<List<FMaterial>> {
        return appDatabase.materialDao.getAllFMaterialLive
    }

    override fun getCacheFMaterialById(id: Int): LiveData<FMaterial> {
        return appDatabase.materialDao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialByDivision(divisionId: Int): LiveData<List<FMaterial>> {
        return appDatabase.materialDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFMaterial(fMaterial: FMaterial) {
        return appDatabase.materialDao.insert(fMaterial)
    }

    override fun putCacheFMaterial(fMaterial: FMaterial) {
        return appDatabase.materialDao.update(fMaterial)
    }

    override fun deleteCacheFMaterial(fMaterial: FMaterial) {
        return appDatabase.materialDao.delete(fMaterial)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialDao.deleteAllFMaterial()
    }


//    override fun getRemoteAllData(): Single<List<FMaterial>> {
//        return retrofitService.getRemoteAllFMaterial(MainApplication.authHeader)
//    }


}