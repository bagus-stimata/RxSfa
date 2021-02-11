package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FSubAreaEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFSubArea {

    @GET("getAllFSubArea")
    fun getRemoteAllFSubArea(@Header("Authorization") authHeader: String?): Single<List<FSubAreaEntity>>

    @GET("getFSubAreaById/{id}")
    fun getRemoteFSubAreaById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSubAreaEntity>

    @GET("getAllFSubAreaByParent/{parentId}")
    fun getRemoteAllFSubAreaByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FSubAreaEntity>>


    @POST("createFSubArea")
    fun createRemoteFSubArea(@Header("Authorization") authHeader: String?, @Body fSubAreaEntityBean: FSubAreaEntity): Single<FSubAreaEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFSubArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fSubAreaEntityBean: FSubAreaEntity): Single<FSubAreaEntity>

    @HTTP(method = "DELETE", path = "deleteFSubArea/{id}", hasBody = true)
    fun  deleteRemoteFSubArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSubAreaEntity>

}