package com.erp.distribution.sfa.data.source.remote.service_api

import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitServiceSecurity {

    @GET("getAllFUser")
    fun getAllData(@Header("Authorization")  authHeader: String): Single<List<FUserEntity>>

    @GET("getFUserById2/{id}")
    fun getFUserById(@Header("Authorization")  authHeader: String, @Path("id") id: Int): Single<FUserEntity>


    @GET("getFUserByUsername/{username}")
    fun getFUserByUsername(@Header("Authorization")  authHeader: String, @Path("username") username: String): Single<FUserEntity>
    @GET("getFUserByUsernamePassword/{username}/{password}")
    fun getFUserByUsernamePassword(@Header("Authorization")  authHeader: String, @Path("username") username: String, @Path("password") password: String): Single<FUserEntity>


    @GET("getFUserByUsername/{email}")
    fun getFUserByEmail(@Header("Authorization")  authHeader: String, @Path("email") email: String): Single<FUserEntity>


    @POST("createFUser")
    fun createRemoteFUser(@Header("Authorization") authHeader: String?, @Body fUserEntityBean: FUserEntity): Single<FUserEntity>

    @PUT("updateFSubArea/{id}")
    fun putRemoteFUser(@Header("Authorization") authHeader: String?, @Path("id") id: Int, @Body fUserEntityBean: FUserEntity): Single<FUserEntity>

    @HTTP(method = "DELETE", path = "deleteFUser/{id}", hasBody = true)
    fun  deleteRemoteFUser(@Header("Authorization") authHeader: String?, @Path("id") id: Int): Single<FUserEntity>
    

//    @GET("albums/{id}/photos")
//    fun getPhotos(@Header("Authorization")  authHeader: String, @Path("id") id: Long): Single<List<FUser>>

}