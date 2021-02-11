package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterialGroup2Entity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialGroup2 {

    @GET("getAllFMaterialGroup2")
    fun getRemoteAllFMaterialGroup2(@Header("Authorization") authHeader: String?): Single<List<FMaterialGroup2Entity>>

    @GET("getFMaterialGroup2ById/{id}")
    fun getRemoteFMaterialGroup2ById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup2Entity>

    @GET("getAllFMaterialGroup2ByParent/{parentId}")
    fun getRemoteAllFMaterialGroup2ByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FMaterialGroup2Entity>>


    @POST("createFMaterialGroup2")
    fun createRemoteFMaterialGroup2(@Header("Authorization") authHeader: String?, @Body fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity>

    @PUT("updateFMaterialGroup2/{id}")
    fun putRemoteFMaterialGroup2(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialGroup2Entity: FMaterialGroup2Entity): Single<FMaterialGroup2Entity>

    @HTTP(method = "DELETE", path = "deleteFMaterialGroup2/{id}", hasBody = true)
    fun  deleteRemoteFMaterialGroup2(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup2Entity>

}