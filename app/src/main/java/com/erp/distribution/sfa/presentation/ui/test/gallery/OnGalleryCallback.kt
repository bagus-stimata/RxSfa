package com.erp.distribution.sfa.presentation.ui.test.gallery

import android.widget.ImageView
import com.erp.distribution.sfa.domain.model.Album

/**
 * To make an interaction between [GalleryActivity] & its children
 * */
interface OnGalleryCallback {

    fun navigateToAlbumPage(album: Album)

    fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long)
}