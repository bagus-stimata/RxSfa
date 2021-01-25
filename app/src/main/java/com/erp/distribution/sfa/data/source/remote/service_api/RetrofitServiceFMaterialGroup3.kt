package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialGroup3 {

    @GET("getAllFMaterialGroup3")
    fun getRemoteAllFMaterialGroup3(@Header("Authorization") authHeader: String?): Single<List<FMaterialGroup3>>

    @GET("getFMaterialGroup3ById/{id}")
    fun getRemoteFMaterialGroup3ById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup3>

    @GET("getAllFMaterialGroup3ByParent/{parentId}")
    fun getRemoteAllFMaterialGroup3ByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FMaterialGroup3>>


    @POST("createFMaterialGroup3")
    fun createRemoteFMaterialGroup3(@Header("Authorization") authHeader: String?, @Body fMaterialGroup3: FMaterialGroup3): Single<FMaterialGroup3>

    @PUT("updateFMaterialGroup3/{id}")
    fun putRemoteFMaterialGroup3(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialGroup3: FMaterialGroup3): Single<FMaterialGroup3>

    @HTTP(method = "DELETE", path = "deleteFMaterialGroup3/{id}", hasBody = true)
    fun  deleteRemoteFMaterialGroup3(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialGroup3>

}