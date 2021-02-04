package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFArea {

    @GET("getAllFArea")
    fun getRemoteAllFArea(@Header("Authorization") authHeader: String?): Single<List<FAreaEntity>>

    @GET("getFAreaById/{id}")
    fun getRemoteFAreaById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FAreaEntity>

    @GET("getAllFAreaByDivision/{fdivisionBean}")
    fun getRemoteAllFAreaByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FAreaEntity>>

    @GET("getAllFAreaByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getRemoteAllFAreaByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FAreaEntity>>


    @POST("createFArea")
    fun createRemoteFArea(@Header("Authorization") authHeader: String?, @Body fAreaEntityBean: FAreaEntity): Single<FAreaEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fAreaEntityBean: FAreaEntity): Single<FAreaEntity>

    @HTTP(method = "DELETE", path = "deleteFArea/{id}", hasBody = true)
    fun  deleteRemoteFArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FAreaEntity>

}