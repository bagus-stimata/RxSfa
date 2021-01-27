package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FMaterialPicEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFMaterialPic {

    @GET("getAllFMaterialPic")
    fun getRemoteAllFMaterialPic(@Header("Authorization") authHeader: String?): Single<List<FMaterialPicEntity>>

    @GET("getFMaterialPicById/{id}")
    fun getRemoteFMaterialPicById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialPicEntity>

    @GET("getAllFMaterialPicByParent/{parentId}")
    fun getRemoteAllFMaterialPicByParent(@Header("Authorization") authHeader: String?, @Path("parentId") parentId: Int): Single<List<FMaterialPicEntity>>


    @POST("createFMaterialPic")
    fun createRemoteFMaterialPic(@Header("Authorization") authHeader: String?, @Body fMaterialPicEntityBean: FMaterialPicEntity): Single<FMaterialPicEntity>

    @PUT("updateFMaterialPic/{id}")
    fun putRemoteFMaterialPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fMaterialPicEntityBean: FMaterialPicEntity): Single<FMaterialPicEntity>

    @HTTP(method = "DELETE", path = "deleteFMaterialPic/{id}", hasBody = true)
    fun  deleteRemoteFMaterialPic(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FMaterialPicEntity>

}