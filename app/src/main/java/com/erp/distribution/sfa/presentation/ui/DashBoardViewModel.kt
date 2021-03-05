package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FStock
import com.erp.distribution.sfa.domain.model.FtPriceAlth
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.usecase.GetFStockUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtPriceAltdItemsUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtPriceAlthUseCase
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
    private val getFStockUseCase: GetFStockUseCase,
    private val getFtPriceAlthUseCase: GetFtPriceAlthUseCase,
    private val getFtPriceAltdItemsUseCase: GetFtPriceAltdItemsUseCase,
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
//                            Log.d(TAG, "#result success get stock: >>  Oke Update")
                            updateCacheFStock_FromRepo(it)
                        },
                        {
//                            Log.e(TAG, "#result ERROR get stock: >>  ${Date()} >> \n ${it}")
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


    fun subscribeUpdatePriceAlth() = viewModelScope.launch {
        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
        getFtPriceAlthUseCase.getRemoteAllFtPriceAlthByDivisionAndShareToCompany(authHeader, userViewState.fDivision!!.id, userViewState.fDivision!!.fcompanyBean)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    insertCacheFtPriceAlth(it)
                }
                .doAfterNext{
                    it.iterator().forEach {  ftPriceAlthEntity ->
                        subscribeListFtPriceAlthItemsByParent_FromRepo(ftPriceAlthEntity)
//                            Log.d(TAG, "#result success get FtPriceAlth: >>  ${ftPriceAlthEntity.id}")
                    }
                }
                .subscribe(
                        {
//                            Log.d(TAG, "#result success get FtPriceAlth: >>  Oke Update ")
                        },
                        {
//                            Log.e(TAG, "#result ERROR get FtPriceAlth: >>  ${Date()} >> \n ${it}")
                        },
                        {}
                )
    }


    fun subscribeListFtPriceAlthItemsByParent_FromRepo(ftPriceAlthEntity: FtPriceAlthEntity){
        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
        val disposable = getFtPriceAltdItemsUseCase.getRemoteAllFtPriceAltdItemsByFtPriceAlth(authHeader, ftPriceAlthEntity.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    insertCacheFtPriceAltdItems(it)
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
//                            Log.d(TAG, "#result success FtPriceAlthdItems ${it}")
                        },
                        {
//                            Log.e(TAG, "#result error FtPriceAlthdItems ${it.printStackTrace()}")
                        } ,{}

                )
        compositeDisposable.add(disposable)
    }


    fun insertCacheFtPriceAlth(list: List<FtPriceAlthEntity>){

        DisposableManager.add(Observable.fromCallable {
            /**
             * Pada Root Harus Hapus Semua dulu semua cabangnya baru Insert
             */
              getFtPriceAlthUseCase.deleteAllCacheFtPriceAlth().also {
                  getFtPriceAlthUseCase.addCacheListFtPriceAlth(list)
              }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {},
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        },{}
                )
        )
    }

    fun insertCacheFtPriceAltdItems(list: List<FtPriceAltdItemsEntity>){

        DisposableManager.add(Observable.fromCallable {
            /**
             * Pada Root Harus Hapus Semua dulu semua cabangnya baru Insert
             */
            getFtPriceAltdItemsUseCase.deleteAllCacheFtPriceAltdItems().also {
                getFtPriceAltdItemsUseCase.addCacheListFtPriceAltdItems(list)
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {},
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        },{}
                )
        )
    }


}