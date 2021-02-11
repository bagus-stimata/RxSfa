package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FCustomerPicEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFCustomerPic {

    @GET("getAllFCustomerPic")
    fun getRemoteAllFCustomerPic(@Header("Authorization") authHeader: String?): Single<List<FCustomerPicEntity>>

    @GET("getFCustomerPicById/{id}")
    fun getRemoteFCustomerPicById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerPicEntity>

    @GET("getAllFCustomerPicByParent/{parentId}")
    fun getRemoteAllFCustomerPicByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FCustomerPicEntity>>


    @POST("createFCustomerPic")
    fun createRemoteFCustomerPic(@Header("Authorization") authHeader: String?, @Body fCustomerPicEntityBean: FCustomerPicEntity): Single<FCustomerPicEntity>

    @PUT("updateFCustomerPic/{id}")
    fun putRemoteFCustomerPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCustomerPicEntityBean: FCustomerPicEntity): Single<FCustomerPicEntity>

    @HTTP(method = "DELETE", path = "deleteFCustomerPic/{id}", hasBody = true)
    fun  deleteRemoteFCustomerPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerPicEntity>

}