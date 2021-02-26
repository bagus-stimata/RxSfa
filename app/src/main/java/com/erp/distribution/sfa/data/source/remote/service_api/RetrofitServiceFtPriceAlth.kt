package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFtPriceAlth {

    @GET("getAlltPriceAlthByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getAlltPriceAlthByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FAreaEntity>>


}