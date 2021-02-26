package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFtPriceAltdItems {


    @GET("getAllFtPriceAltdItemsByParent/{ftPricehAlthBean}")
    fun getAllFtPriceAltdItemsByParent(@Header("Authorization") authHeader: String?, @Path("ftPricehAlthBean") ftPricehAlthBean: Int): Single<List<FtPriceAltdItemsEntity>>

}