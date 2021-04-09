package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceFtSalesh {

    @GET("getAllFtSalesh")
    fun getRemoteAllFtSalesh(@Header("Authorization") authHeader: String?): Single<List<FtSaleshEntity>>

    @GET("getFtSaleshById/{id}")
    fun getRemoteFtSaleshById(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSaleshEntity>

    @GET("getAllFtSaleshByDivision/{fdivisionBean}")
    fun getRemoteAllFtSaleshByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FtSaleshEntity>>

    @GET("getAllTotalSalesByFSalesmanThisMonth/{fsalesmanBean}")
    fun getRemoteAllTotalSalesByFSalesmanThisMonth(@Header("Authorization") authHeader: String?, @Path("fsalesmanBean") fsalesmanBean: Int): Single<Map<String, Double>>


    @POST("createFtSalesh")
    fun createRemoteFtSalesh(@Header("Authorization") authHeader: String?, @Body ftSaleshEntityBean: FtSaleshEntity): Single<FtSaleshEntity>
    @POST("createFtSaleshFromAndroid")
    fun createRemoteFtSaleshFromAndroid(@Header("Authorization") authHeader: String?, @Body ftSaleshEntityBean: FtSaleshEntity): Single<FtSaleshEntity>

    @PUT("updateFtSalesh/{id}")
    fun putRemoteFtSalesh(@Header("Authorization") authHeader: String?, @Path("id") id: Long, @Body ftSaleshEntityBean: FtSaleshEntity): Single<FtSaleshEntity>

    @HTTP(method = "DELETE", path = "deleteFtSalesh/{id}", hasBody = true)
    fun  deleteRemoteFtSalesh(@Header("Authorization") authHeader: String?, @Path("id") id: Long): Single<FtSaleshEntity>

}