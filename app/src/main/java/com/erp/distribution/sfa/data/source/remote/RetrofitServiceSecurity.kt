package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RetrofitServiceSecurity {

    @GET("getAllFUser")
    fun getAllData(@Header("Authorization")  authHeader: String): Single<List<FUser>>

    @GET("getFUserById2/{id}")
    fun getFUserById(@Header("Authorization")  authHeader: String, @Path("id") id: Int): Single<FUser>


    @GET("getFUserByUsername/{username}")
    fun getFUserByUsername(@Header("Authorization")  authHeader: String, @Path("username") username: String): Single<FUser>


    @GET("getFUserByUsername/{email}")
    fun getFUserByEmail(@Header("Authorization")  authHeader: String, @Path("email") email: String): Single<FUser>



//    @GET("albums/{id}/photos")
//    fun getPhotos(@Header("Authorization")  authHeader: String, @Path("id") id: Long): Single<List<FUser>>

}