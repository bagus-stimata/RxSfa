package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FSalesman
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFSalesman {

    @GET("getAllFSalesman")
    fun getRemoteAllFSalesman(@Header("Authorization") authHeader: String?): Single<List<FSalesman>>

    @GET("getFSalesmanById/{id}")
    fun getRemoteFSalesmanById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSalesman>

    @GET("getAllFSalesmanByDivision/{fdivisionBean}")
    fun getRemoteAllFSalesmanByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FSalesman>>


    @POST("createFSalesman")
    fun createRemoteFSalesman(@Header("Authorization") authHeader: String?, @Body fSalesmanBean: FSalesman): Single<FSalesman>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFSalesman(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fSalesmanBean: FSalesman): Single<FSalesman>

    @HTTP(method = "DELETE", path = "deleteFSalesman/{id}", hasBody = true)
    fun  deleteRemoteFSalesman(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FSalesman>

}