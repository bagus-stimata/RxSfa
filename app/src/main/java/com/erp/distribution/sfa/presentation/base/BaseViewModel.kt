package com.erp.distribution.sfa.presentation.base

import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.exception.*
import com.erp.distribution.sfa.domain.exception.annotation.Redirect
import com.erp.distribution.sfa.domain.exception.model.Dialog
import com.erp.distribution.sfa.domain.exception.model.Tag
import com.erp.distribution.sfa.presentation.ui.utils.SingleLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel  : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val snackBarMessage = SingleLiveData<String>()
    val toastMessage = SingleLiveData<String>()
    val inlineException = SingleLiveData<List<Tag>>()
    val alertException = SingleLiveData<Pair<String?, String>>()
    val dialogException = SingleLiveData<Dialog>()
    val redirectException = SingleLiveData<Redirect>()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun setThrowable(throwable: Throwable) {
        when (throwable) {
            is SnackBarException -> snackBarMessage.value = throwable.message
            is ToastException -> toastMessage.value = throwable.message
            is InlineException -> inlineException.value = throwable.tags.toList()
            is AlertException -> alertException.value = Pair(throwable.title, throwable.message)
            is DialogException -> dialogException.value = throwable.dialog
            is RedirectException -> redirectException.value = throwable.redirect
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
//        useCases.let { userCases ->
//            if (userCases.isNotEmpty()) userCases.forEach { it?.onCleared() }
//        }
        super.onCleared()
    }
}