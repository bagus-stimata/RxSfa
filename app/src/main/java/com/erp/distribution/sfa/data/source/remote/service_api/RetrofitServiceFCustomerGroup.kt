package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FCustomerGroupEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFCustomerGroup {

    @GET("getAllFCustomerGroup")
    fun getRemoteAllFCustomerGroup(@Header("Authorization") authHeader: String?): Single<List<FCustomerGroupEntity>>

    @GET("getFCustomerGroupById/{id}")
    fun getRemoteFCustomerGroupById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerGroupEntity>

    @GET("getAllFCustomerGroupByDivision/{fdivisionBean}")
    fun getRemoteAllFCustomerGroupByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FCustomerGroupEntity>>

    @GET("getAllFCustomerGroupByDivisionAndShareToCompany/{fdivisionBean}/{fcompanyBean}")
    fun getRemoteAllFCustomerGroupByDivisionAndShareToCompany(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int, @Path("fcompanyBean") fcompanyBean: Int): Single<List<FCustomerGroupEntity>>

    @POST("createFCustomerGroup")
    fun createRemoteFCustomerGroup(@Header("Authorization") authHeader: String?, @Body fCustomerGroupEntityBean: FCustomerGroupEntity): Single<FCustomerGroupEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFCustomerGroup(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCustomerGroupEntityBean: FCustomerGroupEntity): Single<FCustomerGroupEntity>

    @HTTP(method = "DELETE", path = "deleteFCustomerGroup/{id}", hasBody = true)
    fun  deleteRemoteFCustomerGroup(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerGroupEntity>

}