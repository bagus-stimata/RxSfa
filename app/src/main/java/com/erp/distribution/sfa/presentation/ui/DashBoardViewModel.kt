package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipeCallPlan
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.*
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
        private val getFtSaleshUseCase: GetFtSaleshUseCase,
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
                    it.iterator().forEach { ftPriceAlthEntity ->
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
                        }, {}

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
                .subscribe(
                        {},
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        }, {}
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
                .subscribe(
                        {},
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        }, {}
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
//                    insertCacheFSalesCallPlanh(it)
                }
                .doAfterNext{
                }
                .subscribe(
                        {
//                            Log.d(TAG, "#result success get FSalesCallPlan: >>  ${it}")
                            it.iterator().forEach { parentEntity ->

                                subscribeListFSalesCallPlandItemsByParentFromRepo(parentEntity)

                            }

//                            Log.d(TAG, "#result success get FSalesCallPlan: >>  ${it} \n")
                        },
                        {
//                            Log.e(TAG, "#result ERROR get FtPriceAlth: >>  ${Date()} >> \n")
                        },
                        {
//                            Log.d(TAG, "#result Dijalankan Subsribe 1")
//                            val currDate = Date()
//                            subscribeCallPlanListThisDay(currDate)
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
                .subscribe(
                        {},
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        }, {}
                )
        )
    }

    private fun insertCacheFSalesCallPlandItems(list: List<FSalesCallPlandItemsEntity>){

        DisposableManager.add(Observable.fromCallable {
            /**
             * Pada Root Harus Hapus Semua dulu semua cabangnya baru Insert
             */
            getFSalesCallPlandItemsUseCase.addCacheListFSalesCallPlandItems(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        }, {}
                )
        )
    }


    private fun subscribeListFSalesCallPlandItemsByParentFromRepo(parentBean: FSalesCallPlanhEntity){
        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
        val disposable = getFSalesCallPlandItemsUseCase.getRemoteAllFSalesCallPlandItemsByParent(authHeader, parentBean.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
//                    insertCacheFSalesCallPlandItems(it)
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
//                            Log.d(TAG, "#result success FSalesCallPlandItems: ${it.size} >> ${parentBean.tipeCallPlan} ")

                            val calendar = Calendar.getInstance()
                            calendar.time = Date()
                            val calNoTime = Calendar.getInstance()
                            calNoTime.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
                            calNoTime.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
                            calNoTime.set(Calendar.DATE, calendar.get(Calendar.DATE))

                            /**
                             * 1. Cek is minggu ganjil atau genap
                             */
                            val weekOfYear = calNoTime.get(Calendar.WEEK_OF_YEAR)
                            val mingguGanjilGenap = weekOfYear %2 //akan menghasilakn 0 atau 1
                            val dayOfWeek = calNoTime.get(Calendar.DAY_OF_WEEK)
                            val dayOfYear = calNoTime.get(Calendar.DAY_OF_YEAR)

                            if (parentBean.tipeCallPlan.equals(EnumTipeCallPlan.BIWEEKLY)){
                                /**
                                 * param1 bernilai
                                 * 0. Berarti Genap
                                 * 1. Berarti Ganjil
                                 */
                                if (mingguGanjilGenap==parentBean.param1) {
                                    for (item in it) {
                                        var refnoManual = dayOfYear + item.fcustomerBean

                                        var newFSaleshBean  :FtSalesh = FtSalesh()
                                        newFSaleshBean.refno = refnoManual.toLong()

                                        newFSaleshBean.callPlan = true
                                        newFSaleshBean.fcustomerBean = FCustomer(item.fcustomerBean)
                                        newFSaleshBean.fdivisionBean = userViewState.fDivision!!
                                        newFSaleshBean.fwarehouseBean = userViewState.fWarehouse!!

                                        newFSaleshBean.fcustomerPromoToBean = newFSaleshBean.fcustomerBean
                                        newFSaleshBean.fcustomerShipToBean = newFSaleshBean.fcustomerBean

//                                        Log.d(TAG, "#result >> BiWeekly >> ${dayOfWeek} >> " +
//                                                "${item.value1} : ${item.value2} : ${item.value3} : ${item.value4} : ${item.value5} : ${item.value6} : ${item.value7}")

                                        if (dayOfWeek==2 && item.value1==true){
                                            insertCacheFtSalesh(newFSaleshBean)
                                        }
                                        if (dayOfWeek==3 && item.value2==true){
//                                            Log.d(TAG, "#result >> BiWeekly >> ${dayOfWeek} >> ${item.fcustomerBean} >>" +
//                                                    "${item.value1} : ${item.value2} : ${item.value3} : ${item.value4} : ${item.value5} : ${item.value6} : ${item.value7}")

                                            insertCacheFtSalesh(newFSaleshBean)
//                                            getFtSaleshUseCase.insertSingleCacheFtSalesh(newFSaleshBean.toEntity())
                                        }
                                        if (dayOfWeek==4 && item.value3==true){
                                            insertCacheFtSalesh(newFSaleshBean)
                                        }
                                        if (dayOfWeek==5 && item.value4==true){
                                            insertCacheFtSalesh(newFSaleshBean)
                                        }
                                        if (dayOfWeek==6 && item.value5==true){
                                            insertCacheFtSalesh(newFSaleshBean)
                                        }
                                        if (dayOfWeek==7 && item.value6==true){
                                            insertCacheFtSalesh(newFSaleshBean)
                                        }
                                        if (dayOfWeek==1 && item.value7==true){
                                            insertCacheFtSalesh(newFSaleshBean)
                                        }

//                                        Log.d(TAG, "#result success FSalesCallPlandItems ${item} \n")


                                    }
                                }

                            }else{
                                for (item in it) {
                                    var refnoManual = dayOfYear + item.fcustomerBean

                                    var newFSaleshBean  :FtSalesh = FtSalesh()
                                    newFSaleshBean.refno = refnoManual.toLong()

                                    newFSaleshBean.callPlan = true
                                    newFSaleshBean.fcustomerBean = FCustomer(item.fcustomerBean)
                                    newFSaleshBean.fdivisionBean = userViewState.fDivision!!
                                    newFSaleshBean.fwarehouseBean = userViewState.fWarehouse!!

                                    newFSaleshBean.fcustomerPromoToBean = newFSaleshBean.fcustomerBean
                                    newFSaleshBean.fcustomerShipToBean = newFSaleshBean.fcustomerBean

                                    if (dayOfWeek==2 && item.value1==true){
                                        insertCacheFtSalesh(newFSaleshBean)
                                    }
                                    if (dayOfWeek==3 && item.value2==true){
                                        insertCacheFtSalesh(newFSaleshBean)
                                    }
                                    if (dayOfWeek==4 && item.value3==true){
                                        insertCacheFtSalesh(newFSaleshBean)
                                    }
                                    if (dayOfWeek==5 && item.value4==true){
                                        insertCacheFtSalesh(newFSaleshBean)
                                    }
                                    if (dayOfWeek==6 && item.value5==true){
                                        insertCacheFtSalesh(newFSaleshBean)
                                    }
                                    if (dayOfWeek==7 && item.value6==true){
                                        insertCacheFtSalesh(newFSaleshBean)
                                    }
                                    if (dayOfWeek==1 && item.value7==true){
                                        insertCacheFtSalesh(newFSaleshBean)
                                    }

                                }


                            }


                        },
                        {
                            Log.e(TAG, "#result error FSalesCallPlandItems ${it.printStackTrace()}")
                        }, {}

                )
        compositeDisposable.add(disposable)
    }



    private fun subscribeCallPlanListThisDay_X(date: Date){

        getFSalesCallPlandItemsUseCase.getCacheAllFSalesCallPlandItemsSingle()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
//                    Log.d(TAG, "#result success FSalesCallPlandItems ${it}")
                    for (test in it) {
//                        Log.d(TAG, "#result >> Oke bos ${test}\n")
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


    private fun insertCacheFtSalesh(ftSaleshBean: FtSalesh){

        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.insertCacheFtSaleshNoReplace(ftSaleshBean)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d(TAG, "#result success FtSalesh")

                        },
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        }, {}
                )
        )
    }


}