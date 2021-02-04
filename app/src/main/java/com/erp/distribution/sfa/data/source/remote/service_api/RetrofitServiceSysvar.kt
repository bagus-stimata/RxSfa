package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.SysvarEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceSysvar {

    @GET("getAllSysvar")
    fun getRemoteAllSysvar(@Header("Authorization") authHeader: String?): Single<List<SysvarEntity>>

    @GET("getSysvarById/{id}")
    fun getRemoteSysvarById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<SysvarEntity>


    @GET("getAllSysvarByDivision/{fdivisionBean}")
    fun getRemoteAllSysvarByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<SysvarEntity>>

    @GET("getAllSysvarByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getRemoteAllSysvarByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<SysvarEntity>>

    @POST("createSysvar")
    fun createRemoteSysvar(@Header("Authorization") authHeader: String?, @Body sysvarEntityBean: SysvarEntity): Single<SysvarEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteSysvar(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body sysvarEntityBean: SysvarEntity): Single<SysvarEntity>

    @HTTP(method = "DELETE", path = "deleteSysvar/{id}", hasBody = true)
    fun  deleteRemoteSysvar(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<SysvarEntity>

}