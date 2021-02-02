package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FDivisionEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFDivision {

    @GET("getAllFDivision")
    fun getRemoteAllFDivision(@Header("Authorization") authHeader: String?): Single<List<FDivisionEntity>>

    @GET("getFDivisionById/{id}")
    fun getRemoteFDivisionById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDivisionEntity>

    @GET("getAllFDivisionByParent/{parentId}")
    fun getRemoteAllFDivisionByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FDivisionEntity>>

    @POST("createFDivision")
    fun createRemoteFDivision(@Header("Authorization") authHeader: String?, @Body fDivisionEntityBean: FDivisionEntity): Single<FDivisionEntity>

    @PUT("updateFDivision/{id}")
    fun putRemoteFDivision(@Header("Author`ization") authHeader: String?, @Path("id") id: Int, @Body fDivisionEntityBean: FDivisionEntity): Single<FDivisionEntity>

    @HTTP(method = "DELETE", path = "deleteFDivision/{id}", hasBody = true)
    fun  deleteRemoteFDivision(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDivisionEntity>

}