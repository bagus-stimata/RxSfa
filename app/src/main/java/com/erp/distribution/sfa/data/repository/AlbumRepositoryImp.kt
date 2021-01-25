package com.erp.distribution.sfa.data.repository

import com.erp.distribution.sfa.domain.model.Album
import com.erp.distribution.sfa.data.source.remote.service_api.RetrofitService
import com.erp.distribution.sfa.domain.repository.AlbumRepository
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class AlbumRepositoryImp(
    private val retrofitService: RetrofitService
) :
    AlbumRepository {

    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbums()
    }

}