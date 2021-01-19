package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FWarehouse
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFWarehouse {

    @GET("getAllFWarehouse")
    fun getRemoteAllFWarehouse(@Header("Authorization") authHeader: String?): Single<List<FWarehouse>>

    @GET("getFWarehouseById/{id}")
    fun getRemoteFWarehouseById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FWarehouse>

    @GET("getAllFWarehouseByDivision/{fdivisionBean}")
    fun getRemoteAllFWarehouseByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FWarehouse>>


    @POST("createFWarehouse")
    fun createRemoteFWarehouse(@Header("Authorization") authHeader: String?, @Body fWarehouseBean: FWarehouse): Single<FWarehouse>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFWarehouse(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fWarehouseBean: FWarehouse): Single<FWarehouse>

    @HTTP(method = "DELETE", path = "deleteFWarehouse/{id}", hasBody = true)
    fun  deleteRemoteFWarehouse(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FWarehouse>

}