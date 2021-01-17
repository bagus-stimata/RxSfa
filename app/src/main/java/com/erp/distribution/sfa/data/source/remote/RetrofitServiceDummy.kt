package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RetrofitServiceDummy {

    @GET("getAllFUser")
    fun getAllData(@Header("Authorization")  authHeader: String): Single<List<FUser>>

    @GET("albums/{id}/photos")
    fun getPhotos(@Header("Authorization")  authHeader: String, @Path("id") id: Long): Single<List<FUser>>

    @GET("photos/{id}")
    fun getPhotoDetail(@Header("Authorization")  authHeader: String, @Path("id") id: Long):Single<FUser>

}