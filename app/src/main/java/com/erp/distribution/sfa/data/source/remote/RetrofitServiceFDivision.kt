package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FDivision
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFDivision {

    @GET("getAllFDivision")
    fun getRemoteAllFDivision(@Header("Authorization") authHeader: String?): Single<List<FDivision>>

    @GET("getFDivisionById/{id}")
    fun getRemoteFDivisionById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDivision>

    @GET("getAllFDivisionByParentId/{parentId}")
    fun getRemoteAllFDivisionByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FDivision>>


    @POST("createFDivision")
    fun createRemoteFDivision(@Header("Authorization") authHeader: String?, @Body fDivisionBean: FDivision): Single<FDivision>

    @PUT("updateFDivision/{id}")
    fun putRemoteFDivision(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fDivisionBean: FDivision): Single<FDivision>

    @HTTP(method = "DELETE", path = "deleteFDivision/{id}", hasBody = true)
    fun  deleteRemoteFDivision(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FDivision>

}