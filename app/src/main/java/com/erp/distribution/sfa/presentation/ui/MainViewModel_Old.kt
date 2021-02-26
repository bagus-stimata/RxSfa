package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.presentation.extention.map
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.util.*
import io.reactivex.rxjava3.core.Observable


class MainViewModel_Old  @ViewModelInject constructor(
        private val getFUserUseCase: GetFUserUseCase,
        private val getFSalesmanUseCase: GetFSalesmanUseCase,
        private val getFDivisionUseCase: GetFDivisionUseCase,
        private val getFCompanyUseCase: GetFCompanyUseCase,
        private val getFWarehouseUseCase: GetFWarehouseUseCase,
        private val getFMaterialUseCase: GetFMaterialUseCase
)  : ViewModel() {

    private val TAG = MainViewModel_Old::class.java.simpleName
    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    private val mainEventChannel = Channel<MainViewModel_Old.MainEvent>()
    val mainEvent = mainEventChannel.receiveAsFlow()


//    var userViewState: UserViewState = UserViewState()
    val userViewStateLive: LiveData<UserViewState>
        get() = _userViewStateLive
    var _userViewStateLive : MutableLiveData<UserViewState> = MutableLiveData()

    var userEntityActive: FUserEntity = FUserEntity()
    var divisionEntityActive: FDivisionEntity = FDivisionEntity()
    var salesmanEntityActive: FSalesmanEntity = FSalesmanEntity()
    var warehouseEntityActive: FWarehouseEntity = FWarehouseEntity()


    var listUserEntityActiveLive: LiveData<Resource<List<FUserEntity>?>?> = MutableLiveData()
    var userEntityActiveLive : LiveData<FUserEntity> = MutableLiveData<FUserEntity>()
    var divisionActiveLive : LiveData<FUserEntity> = MutableLiveData<FUserEntity>()
    var salesmanActiveLive : LiveData<FUserEntity> = MutableLiveData<FUserEntity>()
    var warehouseActiveLive : LiveData<FUserEntity> = MutableLiveData<FUserEntity>()

//    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
//        val message = ExceptionHandler.parse(exception)
//        _userViewStateLive.value = _userViewStateLive.value?.copy(error = Error(message))
//    }

    init {
//        subscribeAllFUser()

//        _userViewStateLive.value =
//            UserViewState(
//                isComplete = false,
//                error = null,
//                null,
//                null,
//                null,
//                null
//            )
    }
//    fun initView(character: CharacterPresentation) {
//        _detailViewState.value = _detailViewState.value?.copy(info = character)
//    }


    var listUserEntityActiveMutableLive: MutableLiveData<List<FUserEntity>> = MutableLiveData()

    var username: String = ""
    var password: String = ""



    var listFUserEntity: List<FUserEntity> = listOf()



    fun fetchRemoteFUser() {

        getFUserUseCase.execute(
            onSuccess = {
                listFUserEntity = it
//                Log.d("#result", "Masuk bos ${it}")

        },
            onError = {
                it.printStackTrace()
            }
        )
    }


    fun getRemoteFUserByUser(fUserEntity: FUserEntity): Single<FUserEntity> {
        return getFUserUseCase.getRemoteAllFUserByUsername(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fUserEntity.username)
    }

    fun fetchFUserFromRepo(): Single<List<FUserEntity>> {
       return getFUserUseCase.getRemoteAllFUser(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm))

    }

    fun fetchRemoteFUser(fUserEntity: FUserEntity) {

        var returnFUserEntity : FUserEntity = fUserEntity

        compositeDisposable.add(
            getFUserUseCase.getRemoteAllFUserByUsername(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fUserEntity.username)
                .map {
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<FUserEntity>() {
                    override fun onSuccess(successValue: FUserEntity) {
                        returnFUserEntity = successValue

                        returnFUserEntity.lastModified = Date()
                        returnFUserEntity.created = Date()
                        returnFUserEntity.modifiedBy = "bagus"

                        //CREATE JIKA SAMA SAJA
                        if (successValue.password == fUserEntity.password) {
                            compositeDisposable.add(io.reactivex.rxjava3.core.Observable.fromCallable {
                                getFUserUseCase.addCacheFUser(returnFUserEntity)
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


    fun insert(fUserEntity: FUserEntity): FUserEntity {
        var returnFUserEntity : FUserEntity = fUserEntity
        compositeDisposable.add(Observable.fromCallable {
                        getFUserUseCase.addCacheFUser(fUserEntity)
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "#result Berhasil Input Bos")
                }
        )


        return returnFUserEntity
    }

    fun delete(fUserEntity: FUserEntity): FUserEntity {
        var returnFUserEntity : FUserEntity
        compositeDisposable.add(
                getFUserUseCase.deleteRemoteFUser(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fUserEntity.id)
                        .map {
                            it
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<FUserEntity>() {
                            override fun onSuccess(successValue: FUserEntity) {
                                Log.d("result", "Masuk bos ${successValue}")
                                returnFUserEntity = successValue
                            }

                            override fun onError(e: Throwable) {
                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
                            }
                        }))
//        repository.delete(fUser)
        return fUserEntity
    }


    fun deleteCacheAllFUser() {
//        getFUserUseCase.deleteAllCacheFUser()

        compositeDisposable.add(Observable.fromCallable {
            getFUserUseCase.deleteAllCacheFUser()
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "#result Berhasil Delete All Cache Bos")
                }
            )

    }


    fun insertCacheFUser(fUserEntity: FUserEntity){
//        getFUserUseCase.addCacheFUser(fUser)
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


    fun subscribeAllFUser(){
        listUserEntityActiveLive = getFUserUseCase.getCacheAllFUser().map {
            Resource.Loading<Boolean>(true)
            try {
                Resource.Success(it)
           }catch (e: Exception){
               Resource.Failure(e)
           }
        }
    }


    fun getRemoteFDivision(fUserEntity: FUserEntity): Single<FDivisionEntity> {
        /**
         * 1. Cari Divisinya -> Dapat Kode Company
         * 2. Cari List Divivsi yan companynya sama
         */

        return getFDivisionUseCase.getRemoteFDivisionById(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.password), fUserEntity.fdivisionBean!!)
    }

    fun getRemoteFSalesman(fUserEntity: FUserEntity): Single<FSalesmanEntity> {
        return getFSalesmanUseCase.getRemoteFSalesmanById(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.password), fUserEntity.fsalesmanBean!!)
    }
    fun getRemoteFWarehouse(fUserEntity: FUserEntity): Single<FWarehouseEntity> {
        return getFWarehouseUseCase.getRemoteFWarehouseById(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.password), fUserEntity.fwarehouseBean!!)
    }


    val getCacheFMaterialEntity: LiveData<List<FMaterial>>
        get() = getFMaterialUseCase.getCacheAllFMaterial()



    sealed class MainEvent {
//        object NavigateToAddCustomerScreen : MainEvent()
//        data class NavigateToEditCustomerScreen(val fCustomer: FCustomer) : MainEvent()
//        data class ShowUndoDeleteCustomerMessage(val fCustomer: FCustomer) : MainEvent()
//        data class ShowCustomerSavedConfirmationMessage(val msg: String) : MainEvent()
//        object NavigateToDeleteAllCompletedScreen : MainEvent()

        data class NavigateToCustomerScreen(val fUserEntity: FUserEntity) : MainEvent()

        data class NavigateBackWithResult(val result: Int) : MainViewModel_Old.MainEvent()
    }


}