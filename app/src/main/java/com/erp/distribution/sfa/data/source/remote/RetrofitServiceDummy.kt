package com.erp.distribution.sfa.data.source.remote

import com.erp.distribution.sfa.domain.model.Photo
import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.domain.model.DummyUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServiceDummy {

    @GET("albums/")
    fun getAlbums(): Single<List<DummyUser>>

    @GET("albums/{id}/photos")
    fun getPhotos(@Path("id") id: Long): Single<List<DummyUser>>

    @GET("photos/{id}")
    fun getPhotoDetail(@Path("id") id: Long):Single<DummyUser>
}