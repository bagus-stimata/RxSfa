package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FArea
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFArea {

    @GET("getAllFArea")
    fun getRemoteAllFArea(@Header("Authorization") authHeader: String?): Single<List<FArea>>

    @GET("getFAreaById/{id}")
    fun getRemoteFAreaById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FArea>

    @GET("getAllFAreaByDivision/{fdivisionBean}")
    fun getRemoteAllFAreaByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FArea>>


    @POST("createFArea")
    fun createRemoteFArea(@Header("Authorization") authHeader: String?, @Body fAreaBean: FArea): Single<FArea>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fAreaBean: FArea): Single<FArea>

    @HTTP(method = "DELETE", path = "deleteFArea/{id}", hasBody = true)
    fun  deleteRemoteFArea(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FArea>

}