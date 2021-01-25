package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FStock
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

interface RetrofitServiceFStock {

    @GET("getAllFStock")
    fun getRemoteAllFStock(@Header("Authorization") authHeader: String?): Single<List<FStock>>

    @GET("getFStockById/{id}")
    fun getRemoteFStockById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FStock>

    @GET("getAllFStockByFMaterial/{materialId}/{stockDateFrom}/{stockDateTo}")
    fun getRemoteAllFStockByFMaterial(@Header("Authorization") authHeader: String?, @Path("materialId") materialId: Int, @Path("stockDateFrom") stockDateFrom: Date, @Path("stockDateTo") stockDateTo: Date): Single<List<FStock>>

    @GET("getAllFStockByFWarehouse/{warehouseId}/{stockDateFrom}/{stockDateTo}")
    fun getRemoteAllFStockByFWarehouse(@Header("Authorization") authHeader: String?, @Path("warehouseId") warehouseId: Int, @Path("stockDateFrom") stockDateFrom: Date, @Path("stockDateTo") stockDateTo: Date): Single<List<FStock>>


    @POST("createFStock")
    fun createRemoteFStock(@Header("Authorization") authHeader: String?, @Body fStockBean: FStock): Single<FStock>

    @PUT("updateFStock/{id}")
    fun putRemoteFStock(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fStockBean: FStock): Single<FStock>

    @HTTP(method = "DELETE", path = "deleteFStock/{id}", hasBody = true)
    fun  deleteRemoteFStock(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FStock>

}