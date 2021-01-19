package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FMaterialGroup1
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialGroup1 {

    @GET("getAllFMaterialGroup1")
    fun getRemoteAllFMaterialGroup1(@Header("Authorization") authHeader: String?): Single<List<FMaterialGroup1>>

    @GET("getFMaterialGroup1ById/{id}")
    fun getRemoteFMaterialGroup1ById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup1>

    @GET("getAllFMaterialGroup1ByDivision/{fdivisionBean}")
    fun getRemoteAllFMaterialGroup1ByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FMaterialGroup1>>


    @POST("createFMaterialGroup1")
    fun createRemoteFMaterialGroup1(@Header("Authorization") authHeader: String?, @Body fMaterialGroup1Bean: FMaterialGroup1): Single<FMaterialGroup1>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFMaterialGroup1(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialGroup1Bean: FMaterialGroup1): Single<FMaterialGroup1>

    @HTTP(method = "DELETE", path = "deleteFMaterialGroup1/{id}", hasBody = true)
    fun  deleteRemoteFMaterialGroup1(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup1>

}