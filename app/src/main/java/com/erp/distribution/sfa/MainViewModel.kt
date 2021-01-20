package com.erp.distribution.sfa

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*


class MainViewModel  @ViewModelInject constructor(
        private val getFUserUseCase: GetFUserUseCase
)  : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName

    init {
//        deleteAllFUserById(FUser())
//        deleteAllFUser()
        loadCacheFUser()
    }
    private val disposable = CompositeDisposable()

    lateinit var userActive: FUser
    var username: String = ""
    var password: String = ""

    val mutableLiveData = MutableLiveData<String>()

    var itemHeader: MutableLiveData<FUser> = MutableLiveData()
    val listFUserLive: MutableLiveData<List<FUser>> = MutableLiveData()
    var listFUser: List<FUser> = listOf()


    fun loadCacheFUser(){
//        getFUserUseCase.getCacheAllFUser().observe()
        Log.d(TAG, "#result ukuran 1: ${getFUserUseCase.getCacheAllFUserBiasa()}")
        Log.d(TAG, "#result ukuran 2: ${getFUserUseCase.getCacheAllFUser().value}")

    }
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
    fun fetchRemoteFUser(fUser: FUser) {
//        deleteAllFUser()

        var returnFUser : FUser = fUser
        disposable.add(
            getFUserUseCase.getRemoteAllFUserByUsername(fUser.username)
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

//                        getFUserUseCase.addCacheFUser(returnFUser)

                        Log.d(TAG, "#result : ${returnFUser} ")
                        disposable.add(Observable.fromCallable {
                            getFUserUseCase.addCacheFUser(returnFUser)
                        }
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                Log.d(TAG, "#result Berhasil Input Bos")
                            }
                        )


                    }

                    override fun onError(e: Throwable) {
                                Log.d(TAG, "#result Error bos ${e.printStackTrace().toString()}")
                    }
                }))

    }


    fun insert(fUser: FUser): FUser {
        var returnFUser : FUser = fUser
        disposable.add(
                getFUserUseCase.createRemoteFUser(fUser)
                        .map {
                            it
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<FUser>() {
                            override fun onSuccess(successValue: FUser) {
//                                Log.d("result", "Masuk bos ${successValue}")
                                returnFUser = successValue

                            }

                            override fun onError(e: Throwable) {
//                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
                            }
                        }))

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

    fun update(fUser: FUser): FUser {
        var returnFUser : FUser
        disposable.add(
                getFUserUseCase.putRemoteFUser(fUser.id, fUser)
                        .map {
                            it
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<FUser>() {
                            override fun onSuccess(successValue: FUser) {
//                                Log.d("result", "Masuk bos ${successValue}")
                                returnFUser = successValue
                            }

                            override fun onError(e: Throwable) {
//                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
                            }
                        }))
//        repository.update(fUser)
        return fUser
    }

    fun delete(fUser: FUser): FUser {
        var returnFUser : FUser
        disposable.add(
                getFUserUseCase.deleteRemoteFUser(fUser.id)
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

    fun deleteAllFUserById(fUser: FUser) {
        getFUserUseCase.deleteCacheFUser(fUser)
    }
    fun deleteAllFUser() {
        getFUserUseCase.deleteAllCacheFUser()
//        disposable.add(
//                getFUserUseCase.deleteAllCacheFUser()
//                        .map {
//                            it
//                        }
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(object : DisposableSingleObserver<FUser>() {
//                            override fun onSuccess(successValue: FUser) {
//                                Log.d("result", "Masuk bos ${successValue}")
//                            }
//
//                            override fun onError(e: Throwable) {
//                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
//                            }
//                        }))

//        repository.deleteAllFUser()
    }

    val allFUserLive: LiveData<List<FUser>> get() = listFUserLive
//    val allFUser: List<FUser> =
//        get() = getFUserUseCase.getCacheAllFUser().value
//
//    fun getItemHeader(): LiveData<FUser>? {
//        return itemHeader
//    }

}