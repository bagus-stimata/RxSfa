package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FSalesmanEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFSalesman {

    @GET("getAllFSalesman")
    fun getRemoteAllFSalesman(@Header("Authorization") authHeader: String?): Single<List<FSalesmanEntity>>

    @GET("getFSalesmanById/{id}")
    fun getRemoteFSalesmanById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSalesmanEntity>

    @GET("getAllFSalesmanByDivision/{fdivisionBean}")
    fun getRemoteAllFSalesmanByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FSalesmanEntity>>


    @POST("createFSalesman")
    fun createRemoteFSalesman(@Header("Authorization") authHeader: String?, @Body fSalesmanEntityBean: FSalesmanEntity): Single<FSalesmanEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFSalesman(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fSalesmanEnittyBean: FSalesmanEntity): Single<FSalesmanEntity>

    @HTTP(method = "DELETE", path = "deleteFSalesman/{id}", hasBody = true)
    fun  deleteRemoteFSalesman(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSalesmanEntity>

}