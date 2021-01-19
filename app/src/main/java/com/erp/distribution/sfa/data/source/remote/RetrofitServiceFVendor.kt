package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FVendor
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFVendor {

    @GET("getAllFVendor")
    fun getRemoteAllFVendor(@Header("Authorization") authHeader: String?): Single<List<FVendor>>

    @GET("getFVendorById/{id}")
    fun getRemoteFVendorById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FVendor>

    @GET("getAllFVendorByDivision/{fdivisionBean}")
    fun getRemoteAllFVendorByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FVendor>>


    @POST("createFVendor")
    fun createRemoteFVendor(@Header("Authorization") authHeader: String?, @Body fVendorBean: FVendor): Single<FVendor>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFVendor(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fVendorBean: FVendor): Single<FVendor>

    @HTTP(method = "DELETE", path = "deleteFVendor/{id}", hasBody = true)
    fun  deleteRemoteFVendor(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FVendor>

}