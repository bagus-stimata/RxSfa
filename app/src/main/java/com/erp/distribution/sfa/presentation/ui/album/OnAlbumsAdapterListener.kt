package com.erp.distribution.sfa.presentation.ui.album

import com.erp.distribution.sfa.domain.model.Album

/**
 * To make an interaction between [AlbumsAdapter] & [AlbumsFragment]
 * */
interface OnAlbumsAdapterListener {

    fun showPhotos(album: Album)
}