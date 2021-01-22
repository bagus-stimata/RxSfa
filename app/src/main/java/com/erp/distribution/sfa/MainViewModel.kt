package com.erp.distribution.sfa

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.model.Photo
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.presentation.extention.map
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*


class MainViewModel  @ViewModelInject constructor(
        private val getFUserUseCase: GetFUserUseCase
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

    fun getRemoteFUserSecond(fUser: FUser): Single<FUser> {
        return getFUserUseCase.getRemoteAllFUserByUsername(fUser.username)
    }

    fun fetchRemoteFUser(fUser: FUser) {

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

                        //CREATE JIKA SAMA SAJA
                        if (successValue.password == fUser.password) {
                            disposable.add(Observable.fromCallable {
                                getFUserUseCase.addCacheFUser(returnFUser)
                            }
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    Log.d(TAG, "#result Berhasil Input Bos")
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
                    Log.d(TAG, "#result Berhasil Input Cache Bos")
                }
        )
    }
    fun subscribeAllFUser(){

//        val listFUser = arrayListOf<FUser>()
//        listUserActiveLive!!.value = listFUser
        listUserActiveLive = getFUserUseCase.getCacheAllFUser().map { it }

//        listUserActiveLive = getFUserUseCase.getCacheAllFUser().map { it.map {
////            contributorEntityMapper.mapToDomain(it)
//            //rubah rubah disini
////            it.id = 9999
////            it.username = "Ganteng Banget"
////            Log.d(TAG, "#result getMappedFUser")
//                it
//        } }
    }




}