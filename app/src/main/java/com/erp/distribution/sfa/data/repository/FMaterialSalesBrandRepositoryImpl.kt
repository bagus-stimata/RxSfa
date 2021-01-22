package com.erp.distribution.sfa.data.repository

import androidx.lifecycle.LiveData
import com.erp.distribution.sfa.MainApplication
import com.erp.distribution.sfa.data.source.remote.RetrofitServiceFMaterialSalesBrand
import com.erp.distribution.sfa.database.AppDatabase
import com.erp.distribution.sfa.domain.repository.FMaterialSalesBrandRepository
import com.erp.distribution.sfa.model.FMaterialSalesBrand
import com.erp.distribution.sfa.utils.Constants
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class FMaterialSalesBrandRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val retrofitService: RetrofitServiceFMaterialSalesBrand
) : FMaterialSalesBrandRepository {

    override fun getRemoteAllFMaterialSalesBrand(): Single<List<FMaterialSalesBrand>> {
        return retrofitService.getRemoteAllFMaterialSalesBrand(MainApplication.authHeader)
    }

    override fun getRemoteFMaterialSalesBrandById(id: Int): Single<FMaterialSalesBrand> {
        return retrofitService.getRemoteFMaterialSalesBrandById(MainApplication.authHeader, id)
    }

    override fun getRemoteAllFMaterialSalesBrandByDivision(divisionId: Int): Single<List<FMaterialSalesBrand>> {
        return retrofitService.getRemoteAllFMaterialSalesBrandByDivision(MainApplication.authHeader, divisionId)
    }

    override fun createRemoteFMaterialSalesBrand(fMaterialSalesBrand: FMaterialSalesBrand): Single<FMaterialSalesBrand> {
        return retrofitService.createRemoteFMaterialSalesBrand(MainApplication.authHeader, fMaterialSalesBrand)
    }

    override fun putRemoteFMaterialSalesBrand(id: Int, fMaterialSalesBrand: FMaterialSalesBrand): Single<FMaterialSalesBrand> {
        return retrofitService.putRemoteFMaterialSalesBrand(MainApplication.authHeader, id, fMaterialSalesBrand)
    }

    override fun deleteRemoteFMaterialSalesBrand(id: Int): Single<FMaterialSalesBrand> {
        return retrofitService.deleteRemoteFMaterialSalesBrand(MainApplication.authHeader, id)
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
//        return retrofitService.getRemoteAllFMaterialSalesBrand(MainApplication.authHeader)
//    }


}