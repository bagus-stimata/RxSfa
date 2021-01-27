package com.erp.distribution.sfa.presentation.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.presentation.extention.map
import com.erp.distribution.sfa.data.source.entity_security.FUser
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*


class MainViewModel  @ViewModelInject constructor(
        private val getFUserUseCase: GetFUserUseCase,
        private val getFSalesmanUseCase: GetFSalesmanUseCase,
        private val getFDivisionUseCase: GetFDivisionUseCase,
        private val getFCompanyUseCase: GetFCompanyUseCase,
        private val getFWarehouseUseCase: GetFWarehouseUseCase,
        private val getFMaterialUseCase: GetFMaterialUseCase
)  : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName
    var userActive: FUser = FUser()
    var divisionEntityActive: FDivisionEntity = FDivisionEntity()
    var salesmanEntityActive: FSalesmanEntity = FSalesmanEntity()
    var warehouseEntityActive: FWarehouseEntity = FWarehouseEntity()

    var listUserActiveLive: LiveData<List<FUser>?>? = MutableLiveData()

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
                            disposable.add(Observable.fromCallable {
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
//        disposable.add(
//                getFUserUseCase.createRemoteFUser(SecurityUtil.getAuthHeader(fUser.username, fUser.passwordConfirm), fUser)
//                        .map {
//                            it
//                        }
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(object : DisposableSingleObserver<FUser>() {
//                            override fun onSuccess(successValue: FUser) {
////                                Log.d("result", "Masuk bos ${successValue}")
//                                returnFUser = successValue
//
//                            }
//
//                            override fun onError(e: Throwable) {
////                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
//                            }
//                        }))

//        repository.insert(fUser)

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
        listUserActiveLive = getFUserUseCase.getCacheAllFUser().map { it }
    }


    fun getRemoteFDivision(fUser: FUser): Single<FDivisionEntity> {
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
}