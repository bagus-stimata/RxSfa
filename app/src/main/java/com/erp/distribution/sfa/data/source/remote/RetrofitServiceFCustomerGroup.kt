package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FCustomerGroup
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFCustomerGroup {

    @GET("getAllFCustomerGroup")
    fun getRemoteAllFCustomerGroup(@Header("Authorization") authHeader: String?): Single<List<FCustomerGroup>>

    @GET("getFCustomerGroupById/{id}")
    fun getRemoteFCustomerGroupById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerGroup>

    @GET("getAllFCustomerGroupByDivision/{fdivisionBean}")
    fun getRemoteAllFCustomerGroupByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FCustomerGroup>>


    @POST("createFCustomerGroup")
    fun createRemoteFCustomerGroup(@Header("Authorization") authHeader: String?, @Body fCustomerGroupBean: FCustomerGroup): Single<FCustomerGroup>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFCustomerGroup(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCustomerGroupBean: FCustomerGroup): Single<FCustomerGroup>

    @HTTP(method = "DELETE", path = "deleteFCustomerGroup/{id}", hasBody = true)
    fun  deleteRemoteFCustomerGroup(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerGroup>

}