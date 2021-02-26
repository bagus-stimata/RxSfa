package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FStock
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.usecase.GetFStockUseCase
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list.FSaleshViewModel
import com.erp.distribution.sfa.utils.DisposableManager
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.*

class DashBoardViewModel @ViewModelInject constructor(
    private val getFStockUseCase: GetFStockUseCase
) : BaseViewModel() {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewStateLive.value = _userViewStateLive.value?.copy(error = Error(message))
    }

    val TAG = DashBoardViewModel::class.java.simpleName
//    var userViewState: UserViewState = UserViewState()
//    val userViewStateLive: LiveData<Resource<UserViewState>> = MutableLiveData()
    var userViewState = UserViewState()


    fun subscribeUpdateStock() = viewModelScope.launch {
        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
        getFStockUseCase.getRemoteAllFStockByWarehouseOnly(authHeader, userViewState.fWarehouse!!.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            Log.d(TAG, "#result success get stock: >>  Oke Update")
                            updateCacheFStock_FromRepo(it)
                        },
                        {
                            Log.e(TAG, "#result ERROR get stock: >>  ${Date()} >> \n ${it}")
                        },
                        {}
                )


    }


    fun updateCacheFStock_FromRepo(list: List<FStock>) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFStockUseCase.deleteAllCacheFStock().also {
                getFStockUseCase.addCacheListFStock(list)
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},
                        {
//                            Log.e(TAG, "#result Error Update ${it}")
                        }, {}
                )
        )
    }


}