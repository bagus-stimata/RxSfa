package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFCustomer {

    @GET("getAllFCustomer")
    fun getRemoteAllFCustomer(@Header("Authorization") authHeader: String?): Single<List<FCustomerEntity>>

    @GET("getFCustomerById/{id}")
    fun getRemoteFCustomerById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerEntity>

    @GET("getAllFCustomerByDivision/{fdivisionBean}")
    fun getRemoteAllFCustomerByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FCustomerEntity>>


    @POST("createFCustomer")
    fun createRemoteFCustomer(@Header("Authorization") authHeader: String?, @Body fCustomerEntityBean: FCustomerEntity): Single<FCustomerEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFCustomer(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCustomerEntityBean: FCustomerEntity): Single<FCustomerEntity>

    @HTTP(method = "DELETE", path = "deleteFCustomer/{id}", hasBody = true)
    fun  deleteRemoteFCustomer(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerEntity>

}