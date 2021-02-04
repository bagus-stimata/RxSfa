package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FVendorEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFVendor {

    @GET("getAllFVendor")
    fun getRemoteAllFVendor(@Header("Authorization") authHeader: String?): Single<List<FVendorEntity>>

    @GET("getFVendorById/{id}")
    fun getRemoteFVendorById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FVendorEntity>


    @GET("getAllFVendorByDivision/{fdivisionBean}")
    fun getRemoteAllFVendorByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FVendorEntity>>

    @GET("getAllFVendorByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getRemoteAllFVendorByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FVendorEntity>>

    @POST("createFVendor")
    fun createRemoteFVendor(@Header("Authorization") authHeader: String?, @Body fVendorEntityBean: FVendorEntity): Single<FVendorEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFVendor(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fVendorEntityBean: FVendorEntity): Single<FVendorEntity>

    @HTTP(method = "DELETE", path = "deleteFVendor/{id}", hasBody = true)
    fun  deleteRemoteFVendor(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FVendorEntity>

}