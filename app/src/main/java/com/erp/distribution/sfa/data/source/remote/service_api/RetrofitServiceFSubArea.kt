package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FSubArea
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFSubArea {

    @GET("getAllFSubArea")
    fun getRemoteAllFSubArea(@Header("Authorization") authHeader: String?): Single<List<FSubArea>>

    @GET("getFSubAreaById/{id}")
    fun getRemoteFSubAreaById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSubArea>

    @GET("getAllFSubAreaByParent/{parentId}")
    fun getRemoteAllFSubAreaByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FSubArea>>


    @POST("createFSubArea")
    fun createRemoteFSubArea(@Header("Authorization") authHeader: String?, @Body fSubAreaBean: FSubArea): Single<FSubArea>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFSubArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fSubAreaBean: FSubArea): Single<FSubArea>

    @HTTP(method = "DELETE", path = "deleteFSubArea/{id}", hasBody = true)
    fun  deleteRemoteFSubArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSubArea>

}