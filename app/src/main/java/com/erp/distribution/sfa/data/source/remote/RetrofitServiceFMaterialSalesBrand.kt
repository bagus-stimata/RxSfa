package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.model.FMaterialSalesBrand
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialSalesBrand {

    @GET("getAllFMaterialSalesBrand")
    fun getRemoteAllFMaterialSalesBrand(@Header("Authorization") authHeader: String?): Single<List<FMaterialSalesBrand>>

    @GET("getFMaterialSalesBrandById/{id}")
    fun getRemoteFMaterialSalesBrandById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialSalesBrand>

    @GET("getAllFMaterialSalesBrandByDivision/{fdivisionBean}")
    fun getRemoteAllFMaterialSalesBrandByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FMaterialSalesBrand>>


    @POST("createFMaterialSalesBrand")
    fun createRemoteFMaterialSalesBrand(@Header("Authorization") authHeader: String?, @Body fMaterialSalesBrandBean: FMaterialSalesBrand): Single<FMaterialSalesBrand>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFMaterialSalesBrand(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialSalesBrandBean: FMaterialSalesBrand): Single<FMaterialSalesBrand>

    @HTTP(method = "DELETE", path = "deleteFMaterialSalesBrand/{id}", hasBody = true)
    fun  deleteRemoteFMaterialSalesBrand(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialSalesBrand>

}