package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterialGroup1Entity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialGroup1 {

    @GET("getAllFMaterialGroup1")
    fun getRemoteAllFMaterialGroup1(@Header("Authorization") authHeader: String?): Single<List<FMaterialGroup1Entity>>

    @GET("getFMaterialGroup1ById/{id}")
    fun getRemoteFMaterialGroup1ById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup1Entity>

    @GET("getAllFMaterialGroup1ByDivision/{fdivisionBean}")
    fun getRemoteAllFMaterialGroup1ByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FMaterialGroup1Entity>>

    @GET("getAllFMaterialGroup1ByCompany/{fcompanyBean}")
    fun getRemoteAllFMaterialGroup1ByCompany(@Header("Authorization") authHeader: String?, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FMaterialGroup1Entity>>

    @GET("getAllFMaterialGroup1ByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getRemoteAllFMaterialGroup1ByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FMaterialGroup1Entity>>


    @POST("createFMaterialGroup1")
    fun createRemoteFMaterialGroup1(@Header("Authorization") authHeader: String?, @Body fMaterialGroup1EntityBean: FMaterialGroup1Entity): Single<FMaterialGroup1Entity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFMaterialGroup1(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialGroup1EntityBean: FMaterialGroup1Entity): Single<FMaterialGroup1Entity>

    @HTTP(method = "DELETE", path = "deleteFMaterialGroup1/{id}", hasBody = true)
    fun  deleteRemoteFMaterialGroup1(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup1Entity>

}