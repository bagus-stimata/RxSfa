package com.erp.distribution.sfa.presentation.ui.syncronize_fromserver

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumUom
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.utils.DisposableManager
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import java.util.*


class SyncViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFAreaUseCase: GetFAreaUseCase,
    private val getFSubAreaUseCase: GetFSubAreaUseCase,
    private val getFCustomerUseCase: GetFCustomerUseCase,
    private val getFMaterialUseCase: GetFMaterialUseCase,
    private val getFCustomerGroupUseCase: GetFCustomerGroupUseCase,
    private val getFDivisionUseCase: GetFDivisionUseCase,
    private val getFMaterialGroup3UseCase: GetFMaterialGroup3UseCase,
    private val getFSalesmanUseCase: GetFSalesmanUseCase,
    private val getFWarehouseUseCase: GetFWarehouseUseCase
) : BaseViewModel() {

    private val TAG = SyncViewModel::class.simpleName

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewState.value = _userViewState.value?.copy(error = Error(message))
    }
    override var userViewState: UserViewState = UserViewState()
    override val userViewStateLive: LiveData<Resource<UserViewState>> = MutableLiveData<Resource<UserViewState>>()


    var isLoading = true
    var checkList1 = "trying.. Sync Material"
    var checkList2 = "trying.. Sync Customer"
    var checkList3 = ""
    var checkList4 = ""

    var userEntityActive: FUserEntity = FUserEntity()
    var divisionActive: FDivisionEntity = FDivisionEntity()

    var listFMaterialEntityMutableLive: MutableLiveData<List<FMaterialEntity>> = MutableLiveData()
    var listFCustomerEntityMutableLive: MutableLiveData<List<FCustomerEntity>> = MutableLiveData()

    init {
    }

    fun fetchFUserFromRepo() {
        compositeDisposable.add(
            getFUserUseCase.getRemoteAllFUser(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
//                        }
//                        Log.d(TAG, "#result FUSER trying add all ${it}")
//                        getFCustomerUseCase.addCacheListFCustomer(it)

                    },
                    {
                        Log.d(TAG, "#result FUSER error add all")
//                            error -> Log.e(TAG, error.printStackTrace())
                    }
                )
        )
    }


    fun subscribeFMaterialFromRepo(){
        DisposableManager.add(
            getFMaterialUseCase.getRemoteAllFMaterialByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), divisionActive.id, divisionActive.fcompanyBean)
                .toObservable()
                .map { data ->
                    data.map {

                        it.modified = Date()
                        it.created = Date()
                        it.modifiedBy = userEntityActive.username

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
                        Log.d(TAG, "#result MATERIAL error add all ${it.message}")
//                            error -> Log.e(TAG, error.printStackTrace())
                    } ,
                    {
//                            Log.d(TAG, "#result CUSTOMER Complete")
                    }

                )
        )
    }


    /**
     * oke bos
     */
    fun getFDivisionById_FromRepo(): Observable<FDivisionEntity>  {
        return   getFDivisionUseCase.getRemoteFDivisionById(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), userEntityActive.fdivisionBean!!).toObservable()
    }


    fun subscribeListFdivisionByParent_FromRepo(fDivisionEntity: FDivisionEntity){
//        Log.d(TAG, "#result CompanyID from USER  ${fDivisionEntity.fcompanyBean}")
        DisposableManager.add(
                getFDivisionUseCase.getRemoteAllFDivisionByCompany(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), fDivisionEntity.fcompanyBean)
                        .toObservable()
                        .map { data ->
                            data.map {
                                it.modified = Date()
                                it.created = Date()
                                it.modifiedBy = userEntityActive.username
                                it.isStatusActive = false
                                if (it.id ==  fDivisionEntity.id){
                                    it.isStatusActive = true
                                }
                                it
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
//                                    it.iterator().forEach {
//                                        Log.d(TAG, "#result Fetch FDivison Sukses  ${it.kode1}")
//                                    }
                                    insertCacheFDivision(it)
                                },
                                {
                                    Log.d(TAG, "#result Fetch FDivison error  ${it}")
                                } ,
                                {
                                }

                        )
        )
    }
    fun insertCacheFDivision(list:  List<FDivisionEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFDivisionUseCase.deleteAllCacheFDivision()
            getFDivisionUseCase.addCacheListFDivision(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
//                            Log.d(TAG, "#result Insert FDivison Sukses  ${it}")
                        },
                        {
                            Log.d(TAG, "#result Insert FDivison error  ${it.message}")
                        },
                        {
                        }
                )
        )
    }





    fun getFDivisionFromRepo(fCompanyEntity: FCompanyEntity): Observable<List<FDivisionEntity>>  {
        return   getFDivisionUseCase.getRemoteAllFDivisionByCompany(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), fCompanyEntity.id).toObservable()
    }
    fun getFAreaFromRepo(): Observable<List<FAreaEntity>>  {
        return getFAreaUseCase.getRemoteAllFAreaByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), divisionActive.id, divisionActive.fcompanyBean).toObservable()
    }
//    fun getFSubAreaFromRepo(fAreaEntity: FAreaEntity): Observable<List<FSubAreaEntity>>  {
//        return getFSubAreaUseCase.getRemoteAllFSubAreaByParent(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm), fAreaEntity.id).toObservable()
//    }
    fun subscribeListFSubAreaByParent_FromRepo(fAreaEntity: FAreaEntity){
        DisposableManager.add(
                getFSubAreaUseCase.getRemoteAllFSubAreaByParent(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), fAreaEntity.id)
                        .toObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    insertCacheFSubArea(it)
                                },
                                {
                                    Log.d(TAG, "#result Fetch FDivison error  ${it}")
                                } ,
                                {
                                }

                        )
        )
    }

    fun getFCustomerGroupFromRepo(): Observable<List<FCustomerGroupEntity>>  {
        return getFCustomerGroupUseCase.getRemoteAllFCustomerGroupByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), divisionActive.id, divisionActive.fcompanyBean).toObservable()
//        return getFCustomerGroupUseCase.getRemoteAllFCustomerGroup(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm)).toObservable()
    }


    fun getFMaterialGroup3FromRepo(): Observable<List<FMaterialGroup3Entity>>  {
        return   getFMaterialGroup3UseCase.getRemoteAllFMaterialGroup3(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm)).toObservable()
    }
    fun getFMaterialFromRepo(): Observable<List<FMaterialEntity>>  {
//        Log.d(TAG, "#result GetRepoFMaterial Div: ${divisionActive.id} and Comp: ${divisionActive.fcompanyBean}")
        return   getFMaterialUseCase.getRemoteAllFMaterialByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), divisionActive.id, divisionActive.fcompanyBean).toObservable()
    }

    fun getFCustomerFromRepo(): Observable<List<FCustomerEntity>>  {
        return   getFCustomerUseCase
            .getRemoteAllFCustomerByDivisionAndShareToCompany(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), divisionActive.id, divisionActive.fcompanyBean).toObservable()
//        return   getFCustomerUseCase
//                .getRemoteAllFCustomerByDivision(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm), divisionActive.id).toObservable()
    }

    fun insertCacheFArea(list: List<FAreaEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFAreaUseCase.deleteAllCacheFArea()
            getFAreaUseCase.addCacheListFArea(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        },
                        {
                        }
                )
        )
    }
    fun insertCacheFSubArea(list: List<FSubAreaEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFSubAreaUseCase.deleteAllCacheFSubArea()
            getFSubAreaUseCase.addCacheListFSubArea(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
//                            Log.d(TAG, "#result Saved To Insert FSubArea success ${it .toString()}")
                        },
                        {
                            Log.d(TAG, "#result FSubArea error  ${it.message}")

                        },
                        {

                        }
                )
        )
    }
    fun insertCacheFCustomerGroup(list: List<FCustomerGroupEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFCustomerGroupUseCase.deleteAllCacheFCustomerGroup()
            getFCustomerGroupUseCase.addCacheListFCustomerGroup(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")
                        },
                        {
                        }
                )
        )
    }


    fun insertCacheFMaterialGroup3(list: List<FMaterialGroup3Entity>){

        DisposableManager.add(Observable.fromCallable {
            getFMaterialGroup3UseCase.deleteAllCacheFMaterialGroup3()
            getFMaterialGroup3UseCase.addCacheListFMaterialGroup3(list)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
//                            Log.d(TAG, "#result Saved To Insert FMaterialGroup3 success ${it .toString()}")
                        },
                        {
                            Log.d(TAG, "#result FMaterialGroup3 error  ${it.message}")

                        },
                        {

                        }
                )
        )
    }

    fun insertCacheFMaterial(listFMaterialEntity:  List<FMaterialEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.deleteAllCacheFMaterial()
            getFMaterialUseCase.addCacheListFMaterial(listFMaterialEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
//                        Log.d(TAG, "#result Insert FMaterial Tod Database Suscess ${it}")
                    },
                    {
                        Log.d(TAG, "#result Insert FMaterial error To Database ${it.message}")
                    },
                    {

                    }
                )
        )
    }
    fun insertCacheFCustomer(listFCustomerEntity: List<FCustomerEntity>){
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.deleteAllCacheFCustomer()
            getFCustomerUseCase.addCacheListFCustomer(listFCustomerEntity)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
        )
    }


    fun getCacheFAreaLive(): LiveData<List<FAreaEntity>> {
        return getFAreaUseCase.getCacheAllFAreaByDivision(userEntityActive.fdivisionBean!!)
    }

    fun getCacheFMaterialGroup3Live(): LiveData<List<FMaterialGroup3Entity>> {
        return getFMaterialGroup3UseCase.getCacheAllFMaterialGroup3()
    }
    fun getCacheFMaterialLive(): LiveData<List<FMaterialEntity>> {
        return getFMaterialUseCase.getCacheAllFMaterial()
    }
    fun getCacheFCustomerLive(): LiveData<List<FCustomerEntity>> {
        return getFCustomerUseCase.getCacheAllFCustomer()
    }




    fun fetchFAreaFromRepo() {
        DisposableManager.add(
                getFAreaUseCase.getRemoteAllFAreaByDivision(SecurityUtil.getAuthHeader(userEntityActive.username, userEntityActive.passwordConfirm), 92082)
                        .toFlowable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
//                                    Log.d(TAG, "#result CUSTOMER trying add all ${it}")
//                                   getFCustomerUseCase.addCacheListFCustomer(it)
//                                    insertCacheFArea(it)

                                },
                                {
//                                    Log.d(TAG, "#result CUSTOMER error add all")
//                            error -> Log.e(TAG, error.printStackTrace())
                                } ,
                                {
//                                    Log.d(TAG, "#result FArea Complete")
                                }

                        )
        )
    }






    fun deleteAllCacheFCustomer() {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.deleteAllCacheFCustomer()
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "#result Delete All Cache FCustomer")
            }
        )

    }


}