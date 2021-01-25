package com.erp.distribution.sfa.presentation.ui.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.model.Photo

class PhotoViewModel(val photo: Photo) :ViewModel() {

    private val TAG = PhotoViewModel::class.java.name
    val photoData = MutableLiveData<Photo>()

    init {
        photoData.value = photo
    }

}