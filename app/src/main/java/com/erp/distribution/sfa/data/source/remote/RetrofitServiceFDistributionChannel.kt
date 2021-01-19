package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FDistributionChannel
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFDistributionChannel {

    @GET("getAllFDistributionChannel")
    fun getRemoteAllFDistributionChannel(@Header("Authorization") authHeader: String?): Single<List<FDistributionChannel>>

    @GET("getFDistributionChannelById/{id}")
    fun getRemoteFDistributionChannelById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDistributionChannel>

    @GET("getAllFDistributionChannelByDivision/{fdivisionBean}")
    fun getRemoteAllFDistributionChannelByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FDistributionChannel>>


    @POST("createFDistributionChannel")
    fun createRemoteFDistributionChannel(@Header("Authorization") authHeader: String?, @Body fDistributionChannelBean: FDistributionChannel): Single<FDistributionChannel>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFDistributionChannel(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fDistributionChannelBean: FDistributionChannel): Single<FDistributionChannel>

    @HTTP(method = "DELETE", path = "deleteFDistributionChannel/{id}", hasBody = true)
    fun  deleteRemoteFDistributionChannel(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDistributionChannel>

}