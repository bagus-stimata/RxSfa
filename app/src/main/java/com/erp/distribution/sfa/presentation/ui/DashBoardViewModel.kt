package com.erp.distribution.sfa.presentation.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import kotlinx.coroutines.CoroutineExceptionHandler

class DashBoardViewModel @ViewModelInject constructor(

) : BaseViewModel() {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewStateLive.value = _userViewStateLive.value?.copy(error = Error(message))
    }
//    var userViewState: UserViewState = UserViewState()
//    val userViewStateLive: LiveData<Resource<UserViewState>> = MutableLiveData()



}