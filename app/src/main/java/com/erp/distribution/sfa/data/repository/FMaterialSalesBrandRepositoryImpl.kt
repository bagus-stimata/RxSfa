package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitServiceFMaterialSalesBrand
import com.erp.distribution.sfa.data.source.local.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialSalesBrandRepository
import com.erp.distribution.sfa.data.source.entity.FMaterialSalesBrand
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialSalesBrandRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialSalesBrand
) : FMaterialSalesBrandRepository {

    override fun getRemoteAllFMaterialSalesBrand(authHeader: String): Single<List<FMaterialSalesBrand>> {
        return retrofitService.getRemoteAllFMaterialSalesBrand(authHeader)
    }

    override fun getRemoteFMaterialSalesBrandById(authHeader: String, id: Int): Single<FMaterialSalesBrand> {
        return retrofitService.getRemoteFMaterialSalesBrandById(authHeader, id)
    }

    override fun getRemoteAllFMaterialSalesBrandByDivision(authHeader: String, divisionId: Int): Single<List<FMaterialSalesBrand>> {
        return retrofitService.getRemoteAllFMaterialSalesBrandByDivision(authHeader, divisionId)
    }

    override fun createRemoteFMaterialSalesBrand(authHeader: String, fMaterialSalesBrand: FMaterialSalesBrand): Single<FMaterialSalesBrand> {
        return retrofitService.createRemoteFMaterialSalesBrand(authHeader, fMaterialSalesBrand)
    }

    override fun putRemoteFMaterialSalesBrand(authHeader: String, id: Int, fMaterialSalesBrand: FMaterialSalesBrand): Single<FMaterialSalesBrand> {
        return retrofitService.putRemoteFMaterialSalesBrand(authHeader, id, fMaterialSalesBrand)
    }

    override fun deleteRemoteFMaterialSalesBrand(authHeader: String, id: Int): Single<FMaterialSalesBrand> {
        return retrofitService.deleteRemoteFMaterialSalesBrand(authHeader, id)
    }



    override fun getCacheAllFMaterialSalesBrand(): LiveData<List<FMaterialSalesBrand>> {
        return appDatabase.materialSalesBrandDao.getAllFMaterialSalesBrandLive
    }

    override fun getCacheFMaterialSalesBrandById(id: Int): LiveData<FMaterialSalesBrand> {
        return appDatabase.materialSalesBrandDao.getAllByIdLive(id)
    }

    override fun getCacheAllFMaterialSalesBrandByDivision(divisionId: Int): LiveData<List<FMaterialSalesBrand>> {
        return appDatabase.materialSalesBrandDao.getAllByDivisionLive(divisionId)
    }

    override fun addCacheFMaterialSalesBrand(fMaterialSalesBrand: FMaterialSalesBrand) {
        return appDatabase.materialSalesBrandDao.insert(fMaterialSalesBrand)
    }

    override fun putCacheFMaterialSalesBrand(fMaterialSalesBrand: FMaterialSalesBrand) {
        return appDatabase.materialSalesBrandDao.update(fMaterialSalesBrand)
    }

    override fun deleteCacheFMaterialSalesBrand(fMaterialSalesBrand: FMaterialSalesBrand) {
        return appDatabase.materialSalesBrandDao.delete(fMaterialSalesBrand)
    }

    override fun deleteAllCacheData() {
        return appDatabase.materialSalesBrandDao.deleteAllFMaterialSalesBrand()
    }


//    override fun getRemoteAllData(): Single<List<FMaterialSalesBrand>> {
//        return retrofitService.getRemoteAllFMaterialSalesBrand(authHeader)
//    }


}