package com.erp.distribution.sfa.domain.repository

import com.erp.distribution.sfa.domain.model.Album
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface AlbumRepository {
    fun getAlbums(): Single<List<Album>>
}