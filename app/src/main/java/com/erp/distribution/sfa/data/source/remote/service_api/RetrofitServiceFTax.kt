package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FTax
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFTax {

    @GET("getAllFTax")
    fun getRemoteAllFTax(@Header("Authorization") authHeader: String?): Single<List<FTax>>

    @GET("getFTaxById/{id}")
    fun getRemoteFTaxById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FTax>

    @GET("getAllFTaxByDivision/{fdivisionBean}")
    fun getRemoteAllFTaxByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FTax>>


    @POST("createFTax")
    fun createRemoteFTax(@Header("Authorization") authHeader: String?, @Body fTaxBean: FTax): Single<FTax>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFTax(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fTaxBean: FTax): Single<FTax>

    @HTTP(method = "DELETE", path = "deleteFTax/{id}", hasBody = true)
    fun  deleteRemoteFTax(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FTax>

}