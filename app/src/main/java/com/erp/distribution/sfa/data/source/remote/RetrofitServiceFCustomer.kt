package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FCustomer
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFCustomer {

    @GET("getAllFCustomer")
    fun getRemoteAllFCustomer(@Header("Authorization") authHeader: String?): Single<List<FCustomer>>

    @GET("getFCustomerById/{id}")
    fun getRemoteFCustomerById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomer>

    @GET("getAllFCustomerByDivision/{fdivisionBean}")
    fun getRemoteAllFCustomerByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FCustomer>>


    @POST("createFCustomer")
    fun createRemoteFCustomer(@Header("Authorization") authHeader: String?, @Body fCustomerBean: FCustomer): Single<FCustomer>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFCustomer(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCustomerBean: FCustomer): Single<FCustomer>

    @HTTP(method = "DELETE", path = "deleteFCustomer/{id}", hasBody = true)
    fun  deleteRemoteFCustomer(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomer>

}