package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlandItemsEntity
import com.erp.distribution.sfa.data.source.entity.FSalesCallPlanhEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FStock
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.model.UserViewState
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
                            updateCacheFStockFromRepo(it)
                        },
                        {
//                            Log.e(TAG, "#result ERROR get stock: >>  ${Date()} >> \n ${it}")
                        },
                        {}
                )


    }


    private fun updateCacheFStockFromRepo(list: List<FStock>) = viewModelScope.launch {
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
                        subscribeListFtPriceAltdItemsByParentFromRepo(ftPriceAlthEntity)
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


    private fun subscribeListFtPriceAltdItemsByParentFromRepo(ftPriceAlthEntity: FtPriceAlthEntity){
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


    private fun insertCacheFtPriceAlth(list: List<FtPriceAlthEntity>){

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

    private fun insertCacheFtPriceAltdItems(list: List<FtPriceAltdItemsEntity>){

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
                    insertCacheFSalesCallPlanh(it)
                }
                .doAfterNext{
                }
                .subscribe(
                        {
                            it.iterator().forEach {  parentEntity ->
                                subscribeListFSalesCallPlandItemsByParentFromRepo(parentEntity)
    //                        Log.d(TAG, "#result success get FSalesCallPlan: >>  ${parentEntity.id}")
                            }

//                            Log.d(TAG, "#result success get FSalesCallPlan: >>  ${it} \n")
                        },
                        {
//                            Log.e(TAG, "#result ERROR get FtPriceAlth: >>  ${Date()} >> \n")
                        },
                        {
//                            Log.d(TAG, "#result Dijalankan Subsribe 1")
                            val currDate = Date()
                            subscribeCallPlanListThisDay(currDate)
//                            Log.d(TAG, "#result Dijalankan Subsribe 2")
                        }
                )
    }


    private fun insertCacheFSalesCallPlanh(list: List<FSalesCallPlanhEntity>){

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

    private fun insertCacheFSalesCallPlandItems(list: List<FSalesCallPlandItemsEntity>){

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


    private fun subscribeListFSalesCallPlandItemsByParentFromRepo(parentBean: FSalesCallPlanhEntity){
        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
        val disposable = getFSalesCallPlandItemsUseCase.getRemoteAllFSalesCallPlandItemsByParent(authHeader, parentBean.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    insertCacheFSalesCallPlandItems(it)
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
//                            Log.d(TAG, "#result success FSalesCallPlandItems ${it}")
                        },
                        {
                            Log.e(TAG, "#result error FSalesCallPlandItems ${it.printStackTrace()}")
                        } ,{}

                )
        compositeDisposable.add(disposable)
    }



    private fun subscribeCallPlanListThisDay(date: Date){

        getFSalesCallPlandItemsUseCase.getCacheAllFSalesCallPlandItemsSingle()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    Log.d(TAG, "#result success FSalesCallPlandItems ${it}")
                    for (test in it) {
                        Log.d(TAG, "#result >> Oke bos ${test}\n")
                    }
                }
                .subscribe{
                }

//        DisposableManager.add(Disposable.fromRunnable(
//
//        ))
//        getFSalesCallPlandItemsUseCase.getCacheAllFSalesCallPlandItems(date)

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