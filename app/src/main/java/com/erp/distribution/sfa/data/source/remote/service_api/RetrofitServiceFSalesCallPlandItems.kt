package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFSalesCallPlandItems {

    @GET("getAllFSalesCallPlandItemsByParent/{fparentBean}")
    fun getAllFSalesCallPlandItemsByParent(@Header("Authorization") authHeader: String?, @Path("fparentBean") fparentBean: Long): Single<List<FSalesCallPlandItemsEntity>>

}