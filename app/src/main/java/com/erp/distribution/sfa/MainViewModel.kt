package com.erp.distribution.sfa

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.model.Photo
import com.erp.distribution.sfa.domain.usecase.GetFCustomerUseCase
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.presentation.extention.map
import com.erp.distribution.sfa.security_model.FUser
import com.erp.distribution.sfa.utils.DisposableManager
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
        private val getFCustomerUseCase: GetFCustomerUseCase
)  : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName
    var userActive: FUser = FUser()

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

    fun fetchFCustomerFromRepo() {
        DisposableManager.add(
                getFCustomerUseCase.getRemoteAllFCustomer(SecurityUtil.getAuthHeader(userActive.username, userActive.passwordConfirm))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
//                            brewaryList -> Log.i(TAG, "from api----->\n"+brewaryList.toString())
//                            RxJavaRetrofitRoomSampleApplication.database?.let {
//                            it.getBrewaryDao().deleteAll()
//                            it.getBrewaryDao().insertAll(brewaryList) //                        }
                                    Log.d(TAG, "#result CUSTOMER trying add all ${it}")

                                },
                                {
                                    Log.d(TAG, "#result CUSTOMER error add all")
//                                  error -> Log.e(TAG, error.printStackTrace())
                                }
                        )
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
//                    userActiveLive.value = fUser
//                    Log.d(TAG, "#result Berhasil Input Cache Bos")
                }
        )
    }
    fun subscribeAllFUser(){
        listUserActiveLive = getFUserUseCase.getCacheAllFUser().map { it }
    }




}