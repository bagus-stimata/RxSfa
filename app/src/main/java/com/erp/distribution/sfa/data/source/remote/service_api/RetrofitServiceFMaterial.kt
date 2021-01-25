package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterial
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterial {

    @GET("getAllFMaterial")
    fun getRemoteAllFMaterial(@Header("Authorization") authHeader: String?): Single<List<FMaterial>>

    @GET("getFMaterialById/{id}")
    fun getRemoteFMaterialById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterial>

    @GET("getAllFMaterialByDivision/{fdivisionBean}")
    fun getRemoteAllFMaterialByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FMaterial>>


    @POST("createFMaterial")
    fun createRemoteFMaterial(@Header("Authorization") authHeader: String?, @Body fMaterialBean: FMaterial): Single<FMaterial>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFMaterial(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialBean: FMaterial): Single<FMaterial>

    @HTTP(method = "DELETE", path = "deleteFMaterial/{id}", hasBody = true)
    fun  deleteRemoteFMaterial(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterial>

}