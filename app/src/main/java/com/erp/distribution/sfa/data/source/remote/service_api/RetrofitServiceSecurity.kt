package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity_security.FUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceSecurity {

    @GET("getAllFUser")
    fun getAllData(@Header("Authorization")  authHeader: String): Single<List<FUser>>

    @GET("getFUserById2/{id}")
    fun getFUserById(@Header("Authorization")  authHeader: String, @Path("id") id: Int): Single<FUser>


    @GET("getFUserByUsername/{username}")
    fun getFUserByUsername(@Header("Authorization")  authHeader: String, @Path("username") username: String): Single<FUser>
    @GET("getFUserByUsernamePassword/{username}/{password}")
    fun getFUserByUsernamePassword(@Header("Authorization")  authHeader: String, @Path("username") username: String, @Path("password") password: String): Single<FUser>


    @GET("getFUserByUsername/{email}")
    fun getFUserByEmail(@Header("Authorization")  authHeader: String, @Path("email") email: String): Single<FUser>


    @POST("createFUser")
    fun createRemoteFUser(@Header("Authorization") authHeader: String?, @Body fUserBean: FUser): Single<FUser>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFUser(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fUserBean: FUser): Single<FUser>

    @HTTP(method = "DELETE", path = "deleteFUser/{id}", hasBody = true)
    fun  deleteRemoteFUser(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FUser>
    

//    @GET("albums/{id}/photos")
//    fun getPhotos(@Header("Authorization")  authHeader: String, @Path("id") id: Long): Single<List<FUser>>

}