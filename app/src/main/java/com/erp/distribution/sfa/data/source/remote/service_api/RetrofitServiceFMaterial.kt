package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterial {

    @GET("getAllFMaterial")
    fun getRemoteAllFMaterial(@Header("Authorization") authHeader: String?): Single<List<FMaterialEntity>>

    @GET("getFMaterialById/{id}")
    fun getRemoteFMaterialById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialEntity>

    @GET("getAllFMaterialByDivision/{fdivisionBean}")
    fun getRemoteAllFMaterialByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FMaterialEntity>>


    @POST("createFMaterial")
    fun createRemoteFMaterial(@Header("Authorization") authHeader: String?, @Body fMaterialEntityBean: FMaterialEntity): Single<FMaterialEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFMaterial(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialEntityBean: FMaterialEntity): Single<FMaterialEntity>

    @HTTP(method = "DELETE", path = "deleteFMaterial/{id}", hasBody = true)
    fun  deleteRemoteFMaterial(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialEntity>

}