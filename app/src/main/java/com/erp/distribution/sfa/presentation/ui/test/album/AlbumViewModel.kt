package com.erp.distribution.sfa.presentation.ui.test.album

import androidx.lifecycle.MutableLiveData
import com.erp.distribution.sfa.domain.model.Album

/**A helper class for the UI controller that is responsible for
 * preparing data for [AlbumViewModel] as the UI
 *
 * @CREATOR ZARA
 * */
class AlbumViewModel(val album: Album) {

    private val TAG = AlbumViewModel::class.java.simpleName
    val albumData = MutableLiveData<Album>()

    init {
        albumData.value = album
    }

}