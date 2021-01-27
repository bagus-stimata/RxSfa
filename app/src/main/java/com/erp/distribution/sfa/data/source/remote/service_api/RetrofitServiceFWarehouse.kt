package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FWarehouseEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFWarehouse {

    @GET("getAllFWarehouse")
    fun getRemoteAllFWarehouse(@Header("Authorization") authHeader: String?): Single<List<FWarehouseEntity>>

    @GET("getFWarehouseById/{id}")
    fun getRemoteFWarehouseById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FWarehouseEntity>

    @GET("getAllFWarehouseByDivision/{fdivisionBean}")
    fun getRemoteAllFWarehouseByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FWarehouseEntity>>


    @POST("createFWarehouse")
    fun createRemoteFWarehouse(@Header("Authorization") authHeader: String?, @Body fWarehouseEntityBean: FWarehouseEntity): Single<FWarehouseEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFWarehouse(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fWarehouseEntityBean: FWarehouseEntity): Single<FWarehouseEntity>

    @HTTP(method = "DELETE", path = "deleteFWarehouse/{id}", hasBody = true)
    fun  deleteRemoteFWarehouse(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FWarehouseEntity>

}