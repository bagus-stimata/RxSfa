package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FDistributionChannelEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFDistributionChannel {

    @GET("getAllFDistributionChannel")
    fun getRemoteAllFDistributionChannel(@Header("Authorization") authHeader: String?): Single<List<FDistributionChannelEntity>>

    @GET("getFDistributionChannelById/{id}")
    fun getRemoteFDistributionChannelById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDistributionChannelEntity>

    @GET("getAllFDistributionChannelByDivision/{fdivisionBean}")
    fun getRemoteAllFDistributionChannelByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FDistributionChannelEntity>>
    @GET("getAllFDistributionChannelByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getRemoteAllFDistributionChannelByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FDistributionChannelEntity>>


    @POST("createFDistributionChannel")
    fun createRemoteFDistributionChannel(@Header("Authorization") authHeader: String?, @Body fDistributionChannelEntityBean: FDistributionChannelEntity): Single<FDistributionChannelEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFDistributionChannel(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fDistributionChannelEntityBean: FDistributionChannelEntity): Single<FDistributionChannelEntity>

    @HTTP(method = "DELETE", path = "deleteFDistributionChannel/{id}", hasBody = true)
    fun  deleteRemoteFDistributionChannel(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDistributionChannelEntity>

}