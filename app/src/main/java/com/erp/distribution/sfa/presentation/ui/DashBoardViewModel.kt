package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FSalesCallPlandItems
import com.erp.distribution.sfa.domain.model.FSalesCallPlanh
import com.erp.distribution.sfa.domain.model.FStock
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.utils.DisposableManager
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class DashBoardViewModel @ViewModelInject constructor(
    private val getFStockUseCase: GetFStockUseCase,
    private val getFtPriceAlthUseCase: GetFtPriceAlthUseCase,
    private val getFtPriceAltdItemsUseCase: GetFtPriceAltdItemsUseCase,
    private val getFSalesCallPlanhUseCase: GetFSalesCallPlanhUseCase,
    private val getFSalesCallPlandItemsUseCase: GetFSalesCallPlandItemsUseCase
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
                        subscribeListFtPriceAltdItemsByParent_FromRepo(ftPriceAlthEntity)
                            Log.d(TAG, "#result success get FtPriceAlth: >>  ${ftPriceAlthEntity.id}")
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


    fun subscribeListFtPriceAltdItemsByParent_FromRepo(ftPriceAlthEntity: FtPriceAlthEntity){
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
            getFtPriceAltdItemsUseCase.deleteAllCacheFtPriceAltdItems()

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
            getFtPriceAltdItemsUseCase.addCacheListFtPriceAltdItems(list)
            /**
             * HAPUSNYA DI FtPurchaseh yabos: haahah
             */
//            getFtPriceAltdItemsUseCase.deleteAllCacheFtPriceAltdItems().also {
//                getFtPriceAltdItemsUseCase.addCacheListFtPriceAltdItems(list)
//            }
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






    fun subscribeSalesCallPlanh() = viewModelScope.launch {

        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
        getFSalesCallPlanhUseCase.getRemoteAllFSalesCallPlanhBySalesman(authHeader, userViewState.fSalesman!!.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    insertCache_FSalesCallPlanh(it)
                }
                .doAfterNext{
                    it.iterator().forEach {  parentEntity ->
                        subscribeListFSalesCallPlandItemsByParent_FromRepo(parentEntity)
//                        Log.d(TAG, "#result success get FSalesCallPlan: >>  ${parentEntity.id}")
                    }
                }
                .subscribe(
                        {
                            Log.d(TAG, "#result success get FSalesCallPlan: >>  ${it} \n")
                        },
                        {
                            Log.e(TAG, "#result ERROR get FtPriceAlth: >>  ${Date()} >> \n")
                        },
                        {
                            Log.d(TAG, "#result Dijalankan Subsribe 1")
                            val currDate = Date()
                            subscribeCallPlanListThisDay(currDate)
                            Log.d(TAG, "#result Dijalankan Subsribe 2")
                        }
                )
    }


    fun insertCache_FSalesCallPlanh(list: List<FSalesCallPlanhEntity>){

        DisposableManager.add(Observable.fromCallable {
            /**
             * Pada Root Harus Hapus Semua dulu semua cabangnya baru Insert
             */
            getFSalesCallPlandItemsUseCase.deleteAllCacheFSalesCallPlandItems()

            getFSalesCallPlanhUseCase.deleteAllCacheFSalesCallPlanh().also {
                getFSalesCallPlanhUseCase.addCacheListFSalesCallPlanh(list)
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

    fun insertCache_FSalesCallPlandItems(list: List<FSalesCallPlandItemsEntity>){

        DisposableManager.add(Observable.fromCallable {
            /**
             * Pada Root Harus Hapus Semua dulu semua cabangnya baru Insert
             */
            getFSalesCallPlandItemsUseCase.addCacheListFSalesCallPlandItems(list)
            /**
             * HAPUSNYA DI FtPurchaseh yabos: haahah
             */
//            getFtPriceAltdItemsUseCase.deleteAllCacheFtPriceAltdItems().also {
//                getFtPriceAltdItemsUseCase.addCacheListFtPriceAltdItems(list)
//            }
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


    fun subscribeListFSalesCallPlandItemsByParent_FromRepo(parentBean: FSalesCallPlanhEntity){
        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
        val disposable = getFSalesCallPlandItemsUseCase.getRemoteAllFSalesCallPlandItemsByParent(authHeader, parentBean.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    insertCache_FSalesCallPlandItems(it)
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            Log.d(TAG, "#result success FSalesCallPlandItems ${it}")
                        },
                        {
                            Log.e(TAG, "#result error FSalesCallPlandItems ${it.printStackTrace()}")
                        } ,{}

                )
        compositeDisposable.add(disposable)
    }


    var fsalesCallPlandItemsLive = MutableLiveData<List<FSalesCallPlandItems>>()

    fun subscribeCallPlanListThisDay(date: Date){
        DisposableManager.add(Disposable.fromRunnable(

        ))
        getFSalesCallPlandItemsUseCase.getCacheAllFSalesCallPlandItems(date)
    }

//    fun subscribeCallPlanListThisDay(date: Date) {
//        DisposableManager.add(Observable.fromCallable{
//            getFSalesCallPlandItemsUseCase.getCacheAllFSalesCallPlandItems(date).also {
//
//            }
//
//        }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe (
//                        {},
//                        {
////                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
//                        },{}
//                )
//        )
//    }

}