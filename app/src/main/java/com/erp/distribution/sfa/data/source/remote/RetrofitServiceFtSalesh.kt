package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FtSalesh
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFtSalesh {

    @GET("getAllFtSalesh")
    fun getRemoteAllFtSalesh(@Header("Authorization") authHeader: String?): Single<List<FtSalesh>>

    @GET("getFtSaleshById/{id}")
    fun getRemoteFtSaleshById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FtSalesh>

    @GET("getAllFtSaleshByDivision/{fdivisionBean}")
    fun getRemoteAllFtSaleshByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FtSalesh>>


    @POST("createFtSalesh")
    fun createRemoteFtSalesh(@Header("Authorization") authHeader: String?, @Body ftSaleshBean: FtSalesh): Single<FtSalesh>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFtSalesh(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body ftSaleshBean: FtSalesh): Single<FtSalesh>

    @HTTP(method = "DELETE", path = "deleteFtSalesh/{id}", hasBody = true)
    fun  deleteRemoteFtSalesh(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FtSalesh>

}