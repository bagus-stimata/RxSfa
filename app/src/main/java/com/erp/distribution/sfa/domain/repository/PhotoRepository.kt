package com.erp.distribution.sfa.domain.repository

import com.erp.distribution.sfa.domain.model.Photo
import io.reactivex.rxjava3.core.Single


/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 * */
interface PhotoRepository {

    fun getPhotos(albumId: Long?): Single<List<Photo>>

    fun getPhotoDetail(photoId: Long?): Single<Photo>

    fun deletePhoto(photo: Photo)

    fun addPhoto(photo: Photo)

    fun isFavorite(photoId: Long): Boolean
}