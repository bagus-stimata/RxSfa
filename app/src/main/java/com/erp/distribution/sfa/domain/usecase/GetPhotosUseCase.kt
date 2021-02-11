package com.erp.distribution.sfa.domain.usecase

import com.erp.distribution.sfa.domain.model.Photo
import com.erp.distribution.sfa.domain.repository.PhotoRepository
import com.erp.distribution.sfa.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [PhotosViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) : SingleUseCase<List<Photo>>() {

    private var albumId: Long? = null

    fun saveAlbumId(id: Long) {
        albumId = id
    }

    override fun buildUseCaseSingle(): Single<List<Photo>> {
        return repository.getPhotos(albumId)
    }
}