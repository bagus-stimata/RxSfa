package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FTaxEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFTax {

    @GET("getAllFTax")
    fun getRemoteAllFTax(@Header("Authorization") authHeader: String?): Single<List<FTaxEntity>>

    @GET("getFTaxById/{id}")
    fun getRemoteFTaxById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FTaxEntity>

    @GET("getAllFTaxByDivision/{fdivisionBean}")
    fun getRemoteAllFTaxByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FTaxEntity>>

    @GET("getAllFTaxByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getRemoteAllFTaxByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FTaxEntity>>

    @POST("createFTax")
    fun createRemoteFTax(@Header("Authorization") authHeader: String?, @Body fTaxEntityBean: FTaxEntity): Single<FTaxEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFTax(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fTaxEntityBean: FTaxEntity): Single<FTaxEntity>

    @HTTP(method = "DELETE", path = "deleteFTax/{id}", hasBody = true)
    fun  deleteRemoteFTax(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FTaxEntity>

}