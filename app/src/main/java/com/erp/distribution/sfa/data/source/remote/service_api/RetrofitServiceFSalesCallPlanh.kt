package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFSalesCallPlanh {

    @GET("getAllFsalesCallPlanhByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getAllFsalesCallPlanhByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FSalesCallPlanhEntity>>

    @GET("getAllFSalesCallPlanhBySalesman/{fsalesmanBean}")
    fun getAllFSalesCallPlanhBySalesman(@Header("Authorization") authHeader: String?, @Path("fsalesmanBean") fsalesmanBean: Int): Single<List<FSalesCallPlanhEntity>>

}