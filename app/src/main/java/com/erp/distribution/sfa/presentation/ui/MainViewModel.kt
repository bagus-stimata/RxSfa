package com.erp.distribution.sfa.presentation.ui

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.presentation.extention.map
import com.erp.distribution.sfa.data.source.entity_security.FUser
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.jetbrains.anko.textColor
import java.util.*
import io.reactivex.rxjava3.core.Observable


class MainViewModel  @ViewModelInject constructor(
        private val getFUserUseCase: GetFUserUseCase,
        private val getFSalesmanUseCase: GetFSalesmanUseCase,
        private val getFDivisionUseCase: GetFDivisionUseCase,
        private val getFCompanyUseCase: GetFCompanyUseCase,
        private val getFWarehouseUseCase: GetFWarehouseUseCase,
        private val getFMaterialUseCase: GetFMaterialUseCase
)  : BaseViewModel() {
    private val TAG = MainViewModel::class.java.simpleName
    

    private val mainEventChannel = Channel<MainViewModel.MainEvent>()
    val mainEvent = mainEventChannel.receiveAsFlow()

    var userActive: FUser = FUser()
    var divisionEntityActive: FDivisionEntity = FDivisionEntity()
    var salesmanEntityActive: FSalesmanEntity = FSalesmanEntity()
    var warehouseEntityActive: FWarehouseEntity = FWarehouseEntity()

    var listUserActiveLive: LiveData<Resource<List<FUser>?>?> = MutableLiveData()

    init {
        subscribeAllFUser()
    }
    private val disposable = CompositeDisposable()

    var listUserActiveMutableLive: MutableLiveData<List<FUser>> = MutableLiveData()

    var username: String = ""
    var password: String = ""



    var listFUser: List<FUser> = listOf()



    fun fetchRemoteFUser() {

        getFUserUseCase.execute(
            onSuccess = {
                listFUser = it
//                Log.d("#result", "Masuk bos ${it}")

        },
            onError = {
                it.printStackTrace()
            }
        )
    }


    fun getRemoteFUserByUser(fUser: FUser): Single<FUser> {
        return getFUserUseCase.getRemoteAllFUserByUsername(SecurityUtil.getAuthHeader(fUser.username, fUser.passwordConfirm), fUser.username)
    }

    fun fetchFUserFromRepo(): Single<List<FUser>> {
       return getFUserUseCase.getRemoteAllFUser(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm))

    }

    fun fetchRemoteFUser(fUser: FUser) {

        var returnFUser : FUser = fUser
        disposable.add(
            getFUserUseCase.getRemoteAllFUserByUsername(SecurityUtil.getAuthHeader(fUser.username, fUser.passwordConfirm), fUser.username)
                .map {
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<FUser>() {
                    override fun onSuccess(successValue: FUser) {
                        returnFUser = successValue

                        returnFUser.lastModified = Date()
                        returnFUser.created = Date()
                        returnFUser.modifiedBy = "bagus"

                        //CREATE JIKA SAMA SAJA
                        if (successValue.password == fUser.password) {
                            disposable.add(io.reactivex.rxjava3.core.Observable.fromCallable {
                                getFUserUseCase.addCacheFUser(returnFUser)
                            }
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
//                                    Log.d(TAG, "#result Berhasil Input Bos")
                                }
                            )
                        }else{
                            Log.d(TAG, "#result Password tidak Match")
                        }

                    }
                    override fun onError(e: Throwable) {
                        Log.d(TAG, "#result Error bos Fetch Remote User")
                    }

                }))

    }


    fun insert(fUser: FUser): FUser {
        var returnFUser : FUser = fUser
        disposable.add(Observable.fromCallable {
                        getFUserUseCase.addCacheFUser(fUser)
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "#result Berhasil Input Bos")
                }
        )


        return returnFUser
    }

    fun delete(fUser: FUser): FUser {
        var returnFUser : FUser
        disposable.add(
                getFUserUseCase.deleteRemoteFUser(SecurityUtil.getAuthHeader(fUser.username, fUser.passwordConfirm), fUser.id)
                        .map {
                            it
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<FUser>() {
                            override fun onSuccess(successValue: FUser) {
                                Log.d("result", "Masuk bos ${successValue}")
                                returnFUser = successValue
                            }

                            override fun onError(e: Throwable) {
                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
                            }
                        }))
//        repository.delete(fUser)
        return fUser
    }


    fun deleteCacheAllFUser() {
//        getFUserUseCase.deleteAllCacheFUser()

        disposable.add(Observable.fromCallable {
            getFUserUseCase.deleteAllCacheFUser()
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "#result Berhasil Delete All Cache Bos")
                }
            )

    }


    fun insertCacheFUser(fUser: FUser){
//        getFUserUseCase.addCacheFUser(fUser)
        disposable.add(Observable.fromCallable {
            getFUserUseCase.addCacheFUser(fUser)
        }
               .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe {

                }
        )

    }

    fun insertCacheFDivision(fDivisionEntity: FDivisionEntity){
        disposable.add(Observable.fromCallable {
            getFDivisionUseCase.addCacheFDivision(fDivisionEntity)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .subscribe {
            }
        )
    }
    fun insertCacheFSalesman(fSalesmanEntity: FSalesmanEntity){
        disposable.add(Observable.fromCallable {
            getFSalesmanUseCase.addCacheFSalesman(fSalesmanEntity)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .subscribe {
            }
        )
    }
    fun insertCacheFWarehouse(fWarehouseEntity: FWarehouseEntity){
        disposable.add(Observable.fromCallable {
            getFWarehouseUseCase.addCacheFWarehouse(fWarehouseEntity)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
            .subscribe {
            }
        )
    }


    fun subscribeAllFUser(){
        listUserActiveLive = getFUserUseCase.getCacheAllFUser().map {
            Resource.Loading<Boolean>()
            try {
                Resource.Success(it)
           }catch (e: Exception){
               Resource.Failure(e)
           }
        }
    }


    fun getRemoteFDivision(fUser: FUser): Single<FDivisionEntity> {
        /**
         * 1. Cari Divisinya -> Dapat Kode Company
         * 2. Cari List Divivsi yan companynya sama
         */

        return getFDivisionUseCase.getRemoteFDivisionById(SecurityUtil.getAuthHeader(fUser.username, fUser.password), fUser.fdivisionBean)
    }

    fun getRemoteFSalesman(fUser: FUser): Single<FSalesmanEntity> {
        return getFSalesmanUseCase.getRemoteFSalesmanById(SecurityUtil.getAuthHeader(fUser.username, fUser.password), fUser.fsalesmanBean)
    }
    fun getRemoteFWarehouse(fUser: FUser): Single<FWarehouseEntity> {
        return getFWarehouseUseCase.getRemoteFWarehouseById(SecurityUtil.getAuthHeader(fUser.username, fUser.password), fUser.fwarehouseBean)
    }


    val getCacheFMaterialEntity: LiveData<List<FMaterialEntity>>
        get() = getFMaterialUseCase.getCacheAllFMaterial()



    sealed class MainEvent {
//        object NavigateToAddCustomerScreen : MainEvent()
//        data class NavigateToEditCustomerScreen(val fCustomer: FCustomer) : MainEvent()
//        data class ShowUndoDeleteCustomerMessage(val fCustomer: FCustomer) : MainEvent()
//        data class ShowCustomerSavedConfirmationMessage(val msg: String) : MainEvent()
//        object NavigateToDeleteAllCompletedScreen : MainEvent()

        data class NavigateToCustomerScreen(val fUser: FUser) : MainEvent()

        data class NavigateBackWithResult(val result: Int) : MainViewModel.MainEvent()
    }


}