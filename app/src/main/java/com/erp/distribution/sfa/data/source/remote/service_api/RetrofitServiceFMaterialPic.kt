package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterialPic
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialPic {

    @GET("getAllFMaterialPic")
    fun getRemoteAllFMaterialPic(@Header("Authorization") authHeader: String?): Single<List<FMaterialPic>>

    @GET("getFMaterialPicById/{id}")
    fun getRemoteFMaterialPicById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialPic>

    @GET("getAllFMaterialPicByParent/{parentId}")
    fun getRemoteAllFMaterialPicByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FMaterialPic>>


    @POST("createFMaterialPic")
    fun createRemoteFMaterialPic(@Header("Authorization") authHeader: String?, @Body fMaterialPicBean: FMaterialPic): Single<FMaterialPic>

    @PUT("updateFMaterialPic/{id}")
    fun putRemoteFMaterialPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialPicBean: FMaterialPic): Single<FMaterialPic>

    @HTTP(method = "DELETE", path = "deleteFMaterialPic/{id}", hasBody = true)
    fun  deleteRemoteFMaterialPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialPic>

}