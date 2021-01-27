package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FCompanyEntity
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFCompany {

    @GET("getAllFCompany")
    fun getRemoteAllFCompany(@Header("Authorization") authHeader: String?): Single<List<FCompanyEntity>>

    @GET("getFCompanyById/{id}")
    fun getRemoteFCompanyById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCompanyEntity>

//    @GET("getAllFCompanyByDivision/{fdivisionBean}")
//    fun getRemoteAllFCompanyByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FCompany>>


    @POST("createFCompany")
    fun createRemoteFCompany(@Header("Authorization") authHeader: String?, @Body fCompanyEntityBean: FCompanyEntity): Single<FCompanyEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFCompany(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCompanyEntityBean: FCompanyEntity): Single<FCompanyEntity>

    @HTTP(method = "DELETE", path = "deleteFCompany/{id}", hasBody = true)
    fun  deleteRemoteFCompany(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCompanyEntity>

}