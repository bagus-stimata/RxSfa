package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterialSalesBrandEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialSalesBrand {

    @GET("getAllFMaterialSalesBrand")
    fun getRemoteAllFMaterialSalesBrand(@Header("Authorization") authHeader: String?): Single<List<FMaterialSalesBrandEntity>>

    @GET("getFMaterialSalesBrandById/{id}")
    fun getRemoteFMaterialSalesBrandById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialSalesBrandEntity>

    @GET("getAllFMaterialSalesBrandByDivision/{fdivisionBean}")
    fun getRemoteAllFMaterialSalesBrandByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FMaterialSalesBrandEntity>>


    @POST("createFMaterialSalesBrand")
    fun createRemoteFMaterialSalesBrand(@Header("Authorization") authHeader: String?, @Body fMaterialSalesBrandEntityBean: FMaterialSalesBrandEntity): Single<FMaterialSalesBrandEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFMaterialSalesBrand(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialSalesBrandEntityBean: FMaterialSalesBrandEntity): Single<FMaterialSalesBrandEntity>

    @HTTP(method = "DELETE", path = "deleteFMaterialSalesBrand/{id}", hasBody = true)
    fun  deleteRemoteFMaterialSalesBrand(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialSalesBrandEntity>

}