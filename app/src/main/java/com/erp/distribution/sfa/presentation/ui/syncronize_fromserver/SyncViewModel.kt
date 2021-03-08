package com.erp.distribution.sfa.presentation.ui.syncronize_fromserver

import android.util.Log
import android.widget.Toast
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumUom
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.MainViewModel
import com.erp.distribution.sfa.utils.DisposableManager
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.jetbrains.anko.Android
import java.util.*


class SyncViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFAreaUseCase: GetFAreaUseCase,
    private val getFSubAreaUseCase: GetFSubAreaUseCase,
    private val getFCustomerUseCase: GetFCustomerUseCase,
    private val getFMaterialUseCase: GetFMaterialUseCase,
    private val getFCustomerGroupUseCase: GetFCustomerGroupUseCase,
    private val getFDivisionUseCase: GetFDivisionUseCase,
    private val getFMaterialGroup1UseCase: GetFMaterialGroup1UseCase,
    private val getFMaterialGroup2UseCase: GetFMaterialGroup2UseCase,
    private val getFMaterialGroup3UseCase: GetFMaterialGroup3UseCase,
    private val getFSalesmanUseCase: GetFSalesmanUseCase,
    private val getFWarehouseUseCase: GetFWarehouseUseCase,
    @Assisted private val state: SavedStateHandle

) : BaseViewModel() {

    private val TAG = SyncViewModel::class.simpleName

    val userViewState = state.get<UserViewState>("userViewStateActive")
    val userViewStateLive: LiveData<Resource<UserViewState>> = MutableLiveData<Resource<UserViewState>>()

    //if you want to bind  to each field
//    var fUserName = state.get<String>("userName") ?: fUser?.username ?: ""
//        set(value) {
//            field = value
//            state.set("userName", value)
//            Log.d(TAG, "#resul parameter pass ${value}")
//
//        }


    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewState.value = _userViewState.value?.copy(error = Error(message))
    }
//    override var userViewState: UserViewState = UserViewState()


    var isLoading = false
    var progresPersen = 0
    val progresPersenLive = MutableLiveData<Int>()

    var progresMessageSuccess = ""
    val progresMessageSuccessLive = MutableLiveData<String>()

    var progresMessageError = ""
    val progresMessageErrorLive = MutableLiveData<String>()

    var checkList1 = ""
    var checkList2 = ""
    var checkList3 = ""
    var checkList4 = ""

    var listFMaterialEntityMutableLive: MutableLiveData<List<FMaterial>> = MutableLiveData()
    var listFCustomerEntityMutableLive: MutableLiveData<List<FCustomerEntity>> = MutableLiveData()

    init {
    }

    fun subscribeFMaterialFromRepo(fUserEntity: FUserEntity, fDivisionEntity: FDivisionEntity){
        DisposableManager.add(
            getFMaterialUseCase.getRemoteAllFMaterialByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fDivisionEntity.id, fDivisionEntity.fcompanyBean)
                .toObservable()
                .map { data ->
                    data.map {

                        it.modified = Date()
                        it.created = Date()
                        it.modifiedBy = fUserEntity.username

                        if (it.productionDate==null) it.productionDate = Date()
                        if (it.priceUom==null) it.priceUom = EnumUom.UOM1

                        it
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
//                            Log.d(TAG, "#result MATERIAL trying add all ${it}")
                        listFMaterialEntityMutableLive.value = it

                    },
                    {
//                        Log.d(TAG, "#result MATERIAL error add all ${it.message}")
//                            error -> Log.e(TAG, error.printStackTrace())
                    } ,
                    {
//                            Log.d(TAG, "#result CUSTOMER Complete")
                    }

                )
        )
    }



    fun getFAreaFromRepoAndSaveToCache(fUserEntity: FUserEntity, fDivisionEntity: FDivisionEntity) {
        val disposable = getFAreaUseCase
                .getRemoteAllFAreaByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fDivisionEntity.id, fDivisionEntity.fcompanyBean)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { list ->
                    insertCacheFArea(list)
                }
                .doAfterNext{
                    it.iterator().forEach {  fArea ->
                        subscribeListFSubAreaByParent_FromRepo(fUserEntity, fArea)
                    }
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            progresPersen += 10
                            progresPersenLive.postValue(progresPersen)
                            progresMessageSuccess += "\n FArea Success"
                            progresMessageSuccessLive.postValue(progresMessageSuccess)
                        },
                        {
                            progresMessageError += "\n FArea Success"
                            progresMessageErrorLive.postValue(progresMessageError)
                        },{}
                )

        compositeDisposable.add(disposable)
    }


    fun subscribeListFSubAreaByParent_FromRepo(fUserEntity: FUserEntity, fAreaEntity: FAreaEntity){
        val disposable = getFSubAreaUseCase.getRemoteAllFSubAreaByParent(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fAreaEntity.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { insertCacheFSubArea(it) }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            //Progres tidak bisa ditaruh disini karena akan dijalankan satu persatu
                        },
                        {
//                            Log.d(TAG, "#result error FSubArea ${it.printStackTrace()}")
                        } ,
                        {
                        }

                )
        compositeDisposable.add(disposable)
    }

    fun getFCustomerGroupFromRepoAndSaveToCache(fUserEntity: FUserEntity, fDivisionEntity: FDivisionEntity)  {
        getFCustomerGroupUseCase.getRemoteAllFCustomerGroupByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fDivisionEntity.id, fDivisionEntity.fcompanyBean)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext{
                    insertCacheFCustomerGroup(it)
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            progresPersen += 5
                            progresPersenLive.postValue(progresPersen)
                            progresMessageSuccess += "\n FCustomerGroup Success"
                            progresMessageSuccessLive.postValue(progresMessageSuccess)
                        },{
                            progresMessageError += "\n FCustomerGroup Error"
                            progresMessageErrorLive.postValue(progresMessageError)
                        },{}
                )
    }



    fun getFMaterialGroup123FromRepoAndSaveToCache(fUserEntity: FUserEntity, fDivisionEntity: FDivisionEntity) {
        val disposable = getFMaterialGroup1UseCase
//           .getRemoteAllFMaterialGroup1ByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fDivisionEntity.id, fDivisionEntity.fcompanyBean)
                .getRemoteAllFMaterialGroup1ByCompany(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fDivisionEntity.fcompanyBean)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    insertCacheFMaterialGroup1(it)
                }
                .doAfterNext{
                    it.iterator().forEach {  fMaterialGroup1Entity ->
                        subscribeListFMaterialGrup2ByParent_FromRepo(fUserEntity, fMaterialGroup1Entity)
//                        Log.d(">>>>> ", "#result ${fMaterialGroup1Entity.kode1} | ${fMaterialGroup1Entity.description} ")
                    }
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            progresPersen += 15
                            progresPersenLive.postValue(progresPersen)
                            progresMessageSuccess += "\n FMaterialGroup123 Success"
                            progresMessageSuccessLive.postValue(progresMessageSuccess)
                        },
                        {
                            progresMessageError += "\n FMaterialGroup123 Error"
                            progresMessageErrorLive.postValue(progresMessageError)
                        },{}
                )

        compositeDisposable.add(disposable)
    }
    fun subscribeListFMaterialGrup2ByParent_FromRepo(fUserEntity: FUserEntity, fMaterialGroup1Entity: FMaterialGroup1Entity){
        val disposable = getFMaterialGroup2UseCase.getRemoteAllFMaterialGroup2ByParent(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fMaterialGroup1Entity.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    insertCacheFMaterialGroup2(it)
                }
                .doAfterNext {
                    it.iterator().forEach {  fMaterialGroup2Entity ->
                        subscribeListFMaterialGrup3ByParent_FromRepo(fUserEntity, fMaterialGroup2Entity)
                    }
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
//                            Log.d(TAG, "#RESULT >>> ${it}  \n")
//                            Log.d(TAG, "#RESULT >>> ${fMaterialGroup1Entity.id} - ${fMaterialGroup1Entity.description} \n")

                        },
                        {
//                            Log.d(TAG, "#RESULT ERROR>>> ${fMaterialGroup1Entity.id} - ${fMaterialGroup1Entity.description} \n")
                        } ,
                        {
                        }

                )
        compositeDisposable.add(disposable)
    }
    fun subscribeListFMaterialGrup3ByParent_FromRepo(fUserEntity: FUserEntity, fMaterialGroup2Entity: FMaterialGroup2Entity){
        val disposable = getFMaterialGroup3UseCase.getRemoteAllFMaterialGroup3ByParent(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fMaterialGroup2Entity.id)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    insertCacheFMaterialGroup3(it)
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
//                            Log.d(TAG, "#RESULT >>> ${it}  \n")

                        },
                        {
//                            Log.d(TAG, "#RESULT ERROR >>> ${fMaterialGroup2Entity.id} ${fMaterialGroup2Entity.description} ${it.message} \n")
                            progresPersenLive.postValue(2)

                        } ,
                        {
                        }

                )
        compositeDisposable.add(disposable)
    }


    fun getFCustomerFromRepoAndSaveToCache(fUserEntity: FUserEntity, fDivisionEntity: FDivisionEntity)  {
        getFCustomerUseCase
            .getRemoteAllFCustomerByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fDivisionEntity.id, fDivisionEntity.fcompanyBean)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext{
                    insertCacheFCustomer(it.map {
                        /**
                         * Entah kenapa Kolom isStatusSctive tidak bisa ditarik dari Server
                         */
                        it.isStatusActive =true
                        it
                    })
                }
                .subscribe(
                        {
                            progresPersen += 35
                            progresPersenLive.postValue(progresPersen)
                            progresMessageSuccess += "\n FCustomer Success"
                            progresMessageSuccessLive.postValue(progresMessageSuccess)

                        },{
                            progresMessageError += "\n FCustomer Error"
                            progresMessageErrorLive.postValue(progresMessageError)
                        },{}
                )
    }
    fun getFMaterialFromRepoAndSaveToCache(fUserEntity: FUserEntity, fDivisionEntity: FDivisionEntity)  {
        getFMaterialUseCase
                .getRemoteAllFMaterialEntityByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(fUserEntity.username, fUserEntity.passwordConfirm), fDivisionEntity.id, fDivisionEntity.fcompanyBean)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext{

                    for (data in it){
//                        Log.d(TAG, "#result >> ${data.fvendorBean!!.id} | ${data.fmaterialGroup3Bean.id} | ${data.fdivisionBean.id} | ${data.fmaterialSalesBrandBean} | ${data.ftaxBean}" )
//                        Log.d(TAG, "#result >>  ${data.fmaterialGroup3Bean.id} | ${data.fmaterialSalesBrandBean} " )
                    }

                    insertCacheFMaterial(it.map {
                        /**
                         * Entah kenapa Kolom isStatusSctive tidak bisa ditarik dari Server
                         */

                        it.isStatusActive =true
                        it
                    })

//                    Log.d(TAG, "#result Insert FMaterial Success Database Suscess ${it}")

                }
                .subscribe(
                        {

//                            Log.d(TAG, "#result Insert FMaterial Success Database Suscess ${it}")

                            progresPersen += 35
                            progresPersenLive.postValue(progresPersen)
                            progresMessageSuccess += "\n FMaterial Success"
                            progresMessageSuccessLive.postValue(progresMessageSuccess)

                        },{
                            progresMessageError += "\n FMaterial Error"
                            progresMessageErrorLive.postValue(progresMessageError)

                        },{}
                )
    }

    fun insertCacheFArea(list: List<FAreaEntity>){

        DisposableManager.add(Observable.fromCallable {
            /**
             * Pada Root Harus Hapus Semua dulu semua cabangnya baru Insert
             */
            getFAreaUseCase.deleteAllCacheFArea().also {
                getFSubAreaUseCase.deleteAllCacheFSubArea().also {
                    getFAreaUseCase.addCacheListFArea(list)
                }

            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        },
                        {
                        }
                )
        )
    }
    fun insertCacheFSubArea(list: List<FSubAreaEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFSubAreaUseCase.addCacheListFSubArea(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
//                            Log.d(TAG, "#result Saved To Insert FSubArea success ${it .toString()}")
                        },
                        {
//                            Log.d(TAG, "#result FSubArea error  ${it.message}")

                        },
                        {

                        }
                )
        )
    }
    fun insertCacheFCustomerGroup(list: List<FCustomerGroupEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFCustomerGroupUseCase.deleteAllCacheFCustomerGroup().also {
                getFCustomerGroupUseCase.addCacheListFCustomerGroup(list)
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
//                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        },
                        {
                        }
                )
        )
    }


    fun insertCacheFMaterialGroup1(list: List<FMaterialGroup1Entity>){

        DisposableManager.add(Observable.fromCallable {
            getFMaterialGroup1UseCase.deleteAllCacheFMaterialGroup1().also {
                getFMaterialGroup2UseCase.deleteAllCacheFMaterialGroup2().also {

                    getFMaterialGroup3UseCase.deleteAllCacheFMaterialGroup3().also {
                        /**
                         * Hapus semua dulu baru insert
                         */
                        getFMaterialGroup1UseCase.addCacheListFMaterialGroup1(list)
                    }
//                    getFMaterialGroup1UseCase.addCacheListFMaterialGroup1(list)

                }
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {},
                        {},
                        {}
                )
        )
    }
    fun insertCacheFMaterialGroup2(list: List<FMaterialGroup2Entity>){

        DisposableManager.add(Observable.fromCallable {
            getFMaterialGroup2UseCase.addCacheListFMaterialGroup2(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {},
                        {},
                        {}
                )
        )
    }
    fun insertCacheFMaterialGroup3(list: List<FMaterialGroup3Entity>){

        DisposableManager.add(Observable.fromCallable {
            getFMaterialGroup3UseCase.addCacheListFMaterialGroup3(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {},
                        {},
                        {}
                )
        )
    }


    fun insertCacheFMaterial(listFMaterial:  List<FMaterialEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.deleteAllCacheFMaterial().also {
                getFMaterialUseCase.addCacheListFMaterialEntity(listFMaterial)
            }

        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
//                        Log.d(TAG, "#result Insert FMaterial Success Database Suscess ${it}")
                    },
                    {
//                        Log.e(TAG, "#result Insert FMaterial ERROR To Database ${it.message}")
                    },{}
                )
        )
    }


    fun insertCacheFCustomer(listFCustomerEntity: List<FCustomerEntity>){
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.deleteAllCacheFCustomer().also {
                getFCustomerUseCase.addCacheListFCustomer(listFCustomerEntity)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
        )
    }


    /**
     * Event Flow
     */

    private val syncEventChannel = Channel<SyncViewModel.SyncFragmentEvent>()
    val syncEventFlow = syncEventChannel.receiveAsFlow()

    sealed class SyncFragmentEvent {
        data class ShowInvalidInputMessage(val msg: String) : SyncFragmentEvent()
        data class StartSyncFromRepo(val userViewState: UserViewState): SyncFragmentEvent()
        data class NavigateBackWithResult(val result: Int) : SyncFragmentEvent()
    }

    fun startSync(userViewState: UserViewState) = viewModelScope.launch {
        syncEventChannel.send(SyncFragmentEvent.StartSyncFromRepo(userViewState))
    }



}