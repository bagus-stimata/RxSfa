package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FStockEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import java.util.*

interface RetrofitServiceFStock {

    @GET("getAllFStock")
    fun getRemoteAllFStock(@Header("Authorization") authHeader: String?): Single<List<FStockEntity>>

    @GET("getFStockById/{id}")
    fun getRemoteFStockById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FStockEntity>

    @GET("getAllFStockByFMaterial/{materialId}/{stockDateFrom}/{stockDateTo}")
    fun getRemoteAllFStockByFMaterial(@Header("Authorization") authHeader: String?, @Path("materialId") materialId: Int, @Path("stockDateFrom") stockDateFrom: Date, @Path("stockDateTo") stockDateTo: Date): Single<List<FStockEntity>>

    @GET("getAllFStockByFWarehouse/{warehouseId}/{stockDateFrom}/{stockDateTo}")
    fun getRemoteAllFStockByFWarehouse(@Header("Authorization") authHeader: String?, @Path("warehouseId") warehouseId: Int, @Path("stockDateFrom") stockDateFrom: Date, @Path("stockDateTo") stockDateTo: Date): Single<List<FStockEntity>>


    @POST("createFStock")
    fun createRemoteFStock(@Header("Authorization") authHeader: String?, @Body fStockEntityBean: FStockEntity): Single<FStockEntity>

    @PUT("updateFStock/{id}")
    fun putRemoteFStock(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fStockEntityBean: FStockEntity): Single<FStockEntity>

    @HTTP(method = "DELETE", path = "deleteFStock/{id}", hasBody = true)
    fun  deleteRemoteFStock(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FStockEntity>

}