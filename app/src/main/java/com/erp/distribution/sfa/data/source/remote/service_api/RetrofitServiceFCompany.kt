package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity.FCompany
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitServiceFCompany {

    @GET("getAllFCompany")
    fun getRemoteAllFCompany(@Header("Authorization") authHeader: String?): Single<List<FCompany>>

    @GET("getFCompanyById/{id}")
    fun getRemoteFCompanyById(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCompany>

//    @GET("getAllFCompanyByDivision/{fdivisionBean}")
//    fun getRemoteAllFCompanyByDivision(@Header("Authorization") authHeader: String?, @Path("fdivisionBean") fdivisionBean: Int): Single<List<FCompany>>


    @POST("createFCompany")
    fun createRemoteFCompany(@Header("Authorization") authHeader: String?, @Body fCompanyBean: FCompany): Single<FCompany>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFCompany(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fCompanyBean: FCompany): Single<FCompany>

    @HTTP(method = "DELETE", path = "deleteFCompany/{id}", hasBody = true)
    fun  deleteRemoteFCompany(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FCompany>

}