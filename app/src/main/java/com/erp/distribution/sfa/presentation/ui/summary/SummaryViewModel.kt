package com.erp.distribution.sfa.presentation.ui.summary

import androidx.hilt.lifecycle.ViewModelInject
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.usecase.GetSysvarUseCase
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.extention.map
import com.erp.distribution.sfa.presentation.model.UserViewState
import kotlinx.coroutines.CoroutineExceptionHandler

class SummaryViewModel  @ViewModelInject constructor(
        private val getSysvarUseCase: GetSysvarUseCase

): BaseViewModel() {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewStateLive.value = _userViewStateLive.value?.copy(error = Error(message))
    }

    val TAG = SummaryViewModel::class.java.simpleName

    var userViewState= UserViewState()

    val summaryReportLive = getSysvarUseCase.getCacheAllSysvar().map {
        it.filter {
            it.groupSysvar == "SUMMARY_REPORT"
        }
    }


}