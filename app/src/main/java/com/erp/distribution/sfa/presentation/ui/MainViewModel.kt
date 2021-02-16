package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.FDivisionEntity
import com.erp.distribution.sfa.data.source.entity.FSalesmanEntity
import com.erp.distribution.sfa.data.source.entity.FWarehouseEntity
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.data.source.entity_security.toDomain
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.customer.customer_addedit.AddEditCustomerViewModel
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainViewModel @ViewModelInject constructor(
        private val getFUserUseCase: GetFUserUseCase,
        private val getFSalesmanUseCase: GetFSalesmanUseCase,
        private val getFDivisionUseCase: GetFDivisionUseCase,
        private val getFCompanyUseCase: GetFCompanyUseCase,
        private val getFWarehouseUseCase: GetFWarehouseUseCase,
        private val getFMaterialUseCase: GetFMaterialUseCase,
        @Assisted private val state: SavedStateHandle

): BaseViewModel() {

    private val TAG = MainViewModel::class.java.simpleName

    private val mainEventChannel = Channel<MainViewModel.MainEvent>()
    val mainEventFlow = mainEventChannel.receiveAsFlow()


    //    override val coroutineExceptionHandler: CoroutineExceptionHandler
//        get() = TODO("Not yet implemented")
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewStateLive.value = _userViewStateLive.value?.copy(error = Error(message))
    }

    override var userViewState: UserViewState = UserViewState()
//    override val userViewStateLive: LiveData<Resource<UserViewState>>
//        get() = getWhenChangeCacheFromUserViewStateLive()
    override val userViewStateLive: LiveData<Resource<UserViewState>>
        get() = getFUserUseCase.getWhenChangeCacheFromUserViewStateLive()


    fun getRemoteFUserByUser(fUser: FUser): Single<FUserEntity> {
        val result = getFUserUseCase
                .getRemoteAllFUserByUsername(SecurityUtil.getAuthHeader(fUser.toEntity().username, fUser.toEntity().passwordConfirm), fUser.toEntity().username)
        return result
    }

    fun insertCacheFUser(fUserEntity: FUserEntity){
        compositeDisposable.add(Observable.fromCallable {
            getFUserUseCase.addCacheFUser(fUserEntity)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                }
        )
    }
    fun insertCacheFDivision(fDivisionEntity: FDivisionEntity){
        compositeDisposable.add(Observable.fromCallable {
            getFDivisionUseCase.addCacheFDivision(fDivisionEntity)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe {

                }
        )
    }
    fun insertCacheFSalesman(fSalesmanEntity: FSalesmanEntity){
        compositeDisposable.add(Observable.fromCallable {
            getFSalesmanUseCase.addCacheFSalesman(fSalesmanEntity)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                }
        )
    }
    fun insertCacheFWarehouse(fWarehouseEntity: FWarehouseEntity){
        compositeDisposable.add(Observable.fromCallable {
            getFWarehouseUseCase.addCacheFWarehouse(fWarehouseEntity)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                }
        )
    }

    fun getRemoteFDivisionAndSaveToCache(fUserEntity: FUserEntity) {
        getFDivisionUseCase
                .getRemoteFDivisionById(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.password), fUserEntity.fdivisionBean!!)
                .toObservable()
                .subscribe{
                    insertCacheFDivision(it)
                }
    }
    fun getRemoteFSalesmanAndSaveToCache(fUserEntity: FUserEntity) {
        getFSalesmanUseCase
                .getRemoteFSalesmanById(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.password), fUserEntity.fsalesmanBean!!)
                .toObservable()
                .subscribe {
                    Log.d(TAG, "#result Salesman ==== ${fUserEntity.fsalesmanBean} >> ${it}")
                    insertCacheFSalesman(it)
                }
    }
    fun getRemoteFWarehouseAndSaveToCache(fUserEntity: FUserEntity) {
        getFWarehouseUseCase
                .getRemoteFWarehouseById(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.password), fUserEntity.fwarehouseBean!!)
                .toObservable()
                .subscribe {
                    insertCacheFWarehouse(it)
                }
    }


    fun deleteCacheAllFUserAndAllParentRelationship() {
        compositeDisposable.add(Observable.fromCallable {
            getFUserUseCase.deleteAllCacheFUser()

            getFDivisionUseCase.deleteAllCacheFDivision()
            getFWarehouseUseCase.deleteAllCacheFWarehouse()
            getFSalesmanUseCase.deleteAllCacheFSalesman()
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    userViewStateLive
                }
        )

    }


    fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        mainEventChannel.send(MainEvent.ShowInvalidInputMessage(text))
    }
    fun navBackWithResult(value: Int) = viewModelScope.launch {
        mainEventChannel.send(MainEvent.NavigateBackWithResult(value))
    }

    sealed class MainEvent {
        data class ShowInvalidInputMessage(val msg: String) : MainEvent()
        data class NavigateBackWithResult(val result: Int) : MainEvent()
    }

}