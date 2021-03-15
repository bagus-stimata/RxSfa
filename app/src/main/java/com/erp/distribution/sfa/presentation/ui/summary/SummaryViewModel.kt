package com.erp.distribution.sfa.presentation.ui.summary

import androidx.hilt.lifecycle.ViewModelInject
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.model.UserViewState
import kotlinx.coroutines.CoroutineExceptionHandler

class SummaryViewModel  @ViewModelInject constructor(

): BaseViewModel() {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewStateLive.value = _userViewStateLive.value?.copy(error = Error(message))
    }

    val TAG = SummaryViewModel::class.java.simpleName


    var userViewState= UserViewState()




}