package com.erp.distribution.sfa.presentation.ui.master

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.data.source.entity_security.FUser
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumUom
import com.erp.distribution.sfa.utils.DisposableManager
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class MasterViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFAreaUseCase: GetFAreaUseCase,
    private val getFCustomerUseCase: GetFCustomerUseCase,
    private val getFMaterialUseCase: GetFMaterialUseCase,
    private val getFSalesmanUseCase: GetFSalesmanUseCase,
    private val getFWarehouseUseCase: GetFWarehouseUseCase
) : ViewModel() {

    private val TAG = MasterViewModel::class.simpleName
    private val compositeDisposable = CompositeDisposable()

    var isLoading = true
    var checkList1 = "trying.. Sync Material"
    var checkList2 = "trying.. Sync Customer"
    var checkList3 = ""
    var checkList4 = ""

    var userActive: FUser = FUser()

    var listFMaterialEntityMutableLive: MutableLiveData<List<FMaterialEntity>> = MutableLiveData()
    var listFCustomerEntityMutableLive: MutableLiveData<List<FCustomerEntity>> = MutableLiveData()

    init {
//        deleteAllCacheFCustomer()

    }

    fun fetchFUserFromRepo() {
        compositeDisposable.add(
            getFUserUseCase.getRemoteAllFUser(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
//                        }
                        Log.d(TAG, "#result FUSER trying add all ${it}")
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
            getFMaterialUseCase.getRemoteAllFMaterialByDivision(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm), 92082)
                .toObservable()
                .map { data ->
                    data.map {

//                        it.pprice = 0.0
//                        it.pprice2 = 0.0
//                        it.pprice2AfterPpn = 0.0
//                        it.ppriceAfterPpn = 0.0
//
//                        it.sprice = 0.0
//                        it.sprice2 = 0.0
//                        it.sprice2AfterPpn = 0.0
//                        it.spriceAfterPpn = 0.0

                        it.modified = Date()
                        it.created = Date()
                        it.modifiedBy = userActive.username

                        if (it.productionDate==null) it.productionDate = Date()
                        if (it.priceUom==null) it.priceUom = EnumUom.UOM1

                        it
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                            Log.d(TAG, "#result MATERIAL trying add all ${it}")
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

    fun getFMaterialFromRepo(): Observable<List<FMaterialEntity>>  {
        return   getFMaterialUseCase.getRemoteAllFMaterialByDivision(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm), 92082).toObservable()

    }

    fun getFCustomerFromRepo(): Observable<List<FCustomerEntity>>  {
        return   getFCustomerUseCase.getRemoteAllFCustomerByDivision(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm), 92082).toObservable()

    }
    fun subscribeCustomerFromRepoX(){
        DisposableManager.add(
            getFCustomerUseCase.getRemoteAllFCustomerByDivision(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm), 92082)
                .toObservable()
                .map { data -> data.map {
                    it.oldKode1 = "Balik Lagi"
                    it.address1 = "Malang Semua"
                    it
                } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                            listFCustomerEntityMutableLive.value = it

                    },
                    {
                        Log.d(TAG, "#result CUSTOMER error add all")
//                            error -> Log.e(TAG, error.printStackTrace())
                    } ,
                    {
                    }

            )

        )

    }


    fun insertCacheFMaterial(listFMaterialEntity:  List<FMaterialEntity>){

        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.addCacheListFMaterial(listFMaterialEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                    },
                    {
                        Log.d(TAG, "#result MATERIAL error  ${it.message}")

                    },
                    {

                    }
                )
        )
    }
    fun insertCacheFCustomer(listFCustomerEntity: List<FCustomerEntity>){
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.addCacheListFCustomer(listFCustomerEntity)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
        )
    }

    fun getCacheFMaterialLive(): LiveData<List<FMaterialEntity>> {
        return getFMaterialUseCase.getCacheAllFMaterial()
    }
    fun getCacheFCustomerLive(): LiveData<List<FCustomerEntity>> {
        return getFCustomerUseCase.getCacheAllFCustomer()
    }

    fun fetchFAreaFromRepo() {
        DisposableManager.add(
                getFAreaUseCase.getRemoteAllFAreaByDivision(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm), 92082)
                        .toFlowable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    Log.d(TAG, "#result CUSTOMER trying add all ${it}")
//                                   getFCustomerUseCase.addCacheListFCustomer(it)
//                                    insertCacheFArea(it)

                                },
                                {
                                    Log.d(TAG, "#result CUSTOMER error add all")
//                            error -> Log.e(TAG, error.printStackTrace())
                                } ,
                                {
                                    Log.d(TAG, "#result CUSTOMER Complete")
                                }

                        )
        )
    }

    fun insertCacheFArea(listFAreaEntity: List<FAreaEntity>){
        DisposableManager.add(Observable.fromCallable {
            getFAreaUseCase.addCacheListFArea(listFAreaEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
//                    userActiveLive.value = fUser
                    Log.d(TAG, "#result Inserted All FArea")
                }
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