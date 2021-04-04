package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FtSalesdItemsEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFtSalesdItems {

    @GET("getAllFtSalesdItems")
    fun getRemoteAllFtSalesdItems(@Header("Authorization") authHeader: String?): Single<List<FtSalesdItemsEntity>>

    @GET("getFtSalesdItemsById/{id}")
    fun getRemoteFtSalesdItemsById(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSalesdItemsEntity>

    @GET("getAllFtSalesdItemsByFtSalesh/{ftSaleshBean}")
    fun getRemoteAllFtSalesdItemsByFtSalesh(@Header("Authorization") authHeader: String?, @Path("ftSaleshBean") ftSaleshBean: Long): Single<List<FtSalesdItemsEntity>>


    @POST("createFtSalesdItems")
    fun createRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Body ftSalesdItemsEntityBean: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>
    @POST("createListFtSalesdItems")
    fun createRemoteListFtSalesdItems(@Header("Authorization") authHeader: String?, @Body listFtSalesdItemsEntityBean: List<FtSalesdItemsEntity>): Single<List<FtSalesdItemsEntity>>

    @PUT("updateFtSalesdItems/{id}")
    fun putRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Path("id") id: Long, @Body ftSalesdItemsEntityBean: FtSalesdItemsEntity): Single<FtSalesdItemsEntity>

    @HTTP(method = "DELETE", path = "deleteFtSalesdItems/{id}", hasBody = true)
    fun  deleteRemoteFtSalesdItems(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSalesdItemsEntity>

}