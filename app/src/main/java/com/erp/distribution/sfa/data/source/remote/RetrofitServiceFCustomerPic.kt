package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FCustomerPic
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFCustomerPic {

    @GET("getAllFCustomerPic")
    fun getRemoteAllFCustomerPic(@Header("Authorization") authHeader: String?): Single<List<FCustomerPic>>

    @GET("getFCustomerPicById/{id}")
    fun getRemoteFCustomerPicById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerPic>

    @GET("getAllFCustomerPicByParent/{parentId}")
    fun getRemoteAllFCustomerPicByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FCustomerPic>>


    @POST("createFCustomerPic")
    fun createRemoteFCustomerPic(@Header("Authorization") authHeader: String?, @Body fCustomerPicBean: FCustomerPic): Single<FCustomerPic>

    @PUT("updateFCustomerPic/{id}")
    fun putRemoteFCustomerPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCustomerPicBean: FCustomerPic): Single<FCustomerPic>

    @HTTP(method = "DELETE", path = "deleteFCustomerPic/{id}", hasBody = true)
    fun  deleteRemoteFCustomerPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCustomerPic>

}