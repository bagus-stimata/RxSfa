package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FtSalesdItems
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFtSalesdItems {

    @GET("getAllFtSalesdItems")
    fun getRemoteAllFtSalesdItems(@Header("Authorization") authHeader: String?): Single<List<FtSalesdItems>>

    @GET("getFtSalesdItemsById/{id}")
    fun getRemoteFtSalesdItemsById(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSalesdItems>

    @GET("getAllFtSalesdItemsByParent/{parentId}")
    fun getRemoteAllFtSalesdItemsByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Long): Single<List<FtSalesdItems>>


    @POST("createFtSalesdItems")
    fun createRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Body ftSalesdItemsBean: FtSalesdItems): Single<FtSalesdItems>

    @PUT("updateFtSalesdItems/{id}")
    fun putRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Path("id") id: Long, @Body ftSalesdItemsBean: FtSalesdItems): Single<FtSalesdItems>

    @HTTP(method = "DELETE", path = "deleteFtSalesdItems/{id}", hasBody = true)
    fun  deleteRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSalesdItems>

}