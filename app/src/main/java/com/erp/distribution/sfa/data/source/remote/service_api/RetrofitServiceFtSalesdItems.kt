package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFtSalesdItems {

    @GET("getAllFtSalesdItems")
    fun getRemoteAllFtSalesdItems(@Header("Authorization") authHeader: String?): Single<List<FtSalesdItemsEntity>>

    @GET("getFtSalesdItemsById/{id}")
    fun getRemoteFtSalesdItemsById(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSalesdItemsEntity>

    @GET("getAllFtSalesdItemsByParent/{parentId}")
    fun getRemoteAllFtSalesdItemsByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Long): Single<List<FtSalesdItemsEntity>>


    @POST("createFtSalesdItems")
    fun createRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Body ftSalesdItemsEntityBean: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>

    @PUT("updateFtSalesdItems/{id}")
    fun putRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Path("id") id: Long, @Body ftSalesdItemsEntityBean: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>

    @HTTP(method = "DELETE", path = "deleteFtSalesdItems/{id}", hasBody = true)
    fun  deleteRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSalesdItemsEntity>

}