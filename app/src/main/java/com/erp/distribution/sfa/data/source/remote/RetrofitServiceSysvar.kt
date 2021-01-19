package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.Sysvar
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceSysvar {

    @GET("getAllSysvar")
    fun getRemoteAllSysvar(@Header("Authorization") authHeader: String?): Single<List<Sysvar>>

    @GET("getSysvarById/{id}")
    fun getRemoteSysvarById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<Sysvar>

    @GET("getAllSysvarByDivision/{fdivisionBean}")
    fun getRemoteAllSysvarByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<Sysvar>>


    @POST("createSysvar")
    fun createRemoteSysvar(@Header("Authorization") authHeader: String?, @Body sysvarBean: Sysvar): Single<Sysvar>

    @PUT("updateFSubArea/{id}")
    fun putRemoteSysvar(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body sysvarBean: Sysvar): Single<Sysvar>

    @HTTP(method = "DELETE", path = "deleteSysvar/{id}", hasBody = true)
    fun  deleteRemoteSysvar(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<Sysvar>

}