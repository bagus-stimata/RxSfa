package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterialSalesBrand
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialSalesBrandRepository
import com.erp.distribution.sfa.data.source.entity.FMaterialSalesBrandEntity
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialSalesBrandRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialSalesBrand
) : FMaterialSalesBrandRepository {

    override fun getRemoteAllFMaterialSalesBrand(authHeader: String): Single<List<FMaterialSalesBrandEntity>> {
        return retrofitService.getRemoteAllFMaterialSalesBrand(authHeader)
    }

    override fun getRemoteFMaterialSalesBrandById(authHeader: String, id: Int): Single<FMaterialSalesBrandEntity> {
        return retrofitService.getRemoteFMaterialSalesBrandById(authHeader, id)
    }

    override fun getRemoteAllFMaterialSalesBrandByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialSalesBrandEntity>> {
        return retrofitService.getRemoteAllFMaterialSalesBrandByDivision(authHeader, divisionId)
    }

    override fun getRemoteAllFMaterialSalesBrandByDivisionAndShareToCompany(authHeader: String, divisionId: Int, companyId: Int): Single<List<FMaterialSalesBrandEntity>> {
        return retrofitService.getRemoteAllFMaterialSalesBrandByDivisionAndShareToCompany(authHeader, divisionId, companyId)
    }

    override fun createRemoteFMaterialSalesBrand(authHeader: String, fMaterialSalesBrandEntity: FMaterialSalesBrandEntity): Single<FMaterialSalesBrandEntity> {
        return retrofitService.createRemoteFMaterialSalesBrand(authHeader, fMaterialSalesBrandEntity)
    }

    override fun putRemoteFMaterialSalesBrand(authHeader: String, id: Int, fMaterialSalesBrandEntity: FMaterialSalesBrandEntity): Single<FMaterialSalesBrandEntity> {
        return retrofitService.putRemoteFMaterialSalesBrand(authHeader, id, fMaterialSalesBrandEntity)
    }

    override fun deleteRemoteFMaterialSalesBrand(authHeader: String, id: Int): Single<FMaterialSalesBrandEntity> {
        return retrofitService.deleteRemoteFMaterialSalesBrand(authHeader, id)
    }



    override fun getCacheAllFMaterialSalesBrand(): LiveData<List<FMaterialSalesBrandEntity>> {
        return appDatabase.materialSalesBrandDao.getAllFMaterialSalesBrandEntityLive
    }

    override fun getCacheFMaterialSalesBrandById(id: Int): LiveData<FMaterialSalesBrandEntity> {
        return appDatabase.materialSalesBrandDao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialSalesBrandByDivision(divisionId: Int): LiveData<List<FMaterialSalesBrandEntity>> {
        return appDatabase.materialSalesBrandDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFMaterialSalesBrand(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity) {
        return appDatabase.materialSalesBrandDao.insert(fMaterialSalesBrandEntity)
    }

    override fun putCacheFMaterialSalesBrand(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity) {
        return appDatabase.materialSalesBrandDao.update(fMaterialSalesBrandEntity)
    }

    override fun deleteCacheFMaterialSalesBrand(fMaterialSalesBrandEntity: FMaterialSalesBrandEntity) {
        return appDatabase.materialSalesBrandDao.delete(fMaterialSalesBrandEntity)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialSalesBrandDao.deleteAllFMaterialSalesBrand()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialSalesBrand>> {
//        return retrofitService.getRemoteAllFMaterialSalesBrand(authHeader)
//    }


}