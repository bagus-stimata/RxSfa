package com.erp.distribution.sfa.presentation

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.usecase.GetFAreaUseCase
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.model.FArea
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class TestViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFAreaUseCase: GetFAreaUseCase
    ) : ViewModel()  {
//class TestViewModel @ViewModelInject constructor(private val repository: FAreaRepository) : ViewModel()  {

    init {
//        repository = FUserRepository(application)
//        listFUserLive = repository.getAllFUserLive()
//        repository.getRemoteAllFArea()

//        Log.d("result", "Oke kirim Sart")
//        repository.createRemoteFArea(FArea(877798, 0, "TEST", "", "Test Aja", 105, 11298240, true, Date(), Date(), "bagus"))
//        Log.d("result", "Oke Kirim Selesai")

        refreshCacheFUser()
    }

    fun refreshCacheFUser() {
        Log.d(TAG, "#result Refresh FUser")
        println("#result Refresh FUser")
        getFUserUseCase.execute(
            onSuccess = {
//                listFUser = it
                        Log.d(TAG, "#result $it")
            },
            onError = {
                it.printStackTrace()
            }
        )
    }


//    fun insert(fUser: FUser): FUser {
//        repository.insert(fUser)
//        return fUser
//    }
//
//    fun update(fUser: FUser): FUser {
//        repository.update(fUser)
//        return fUser
//    }
//
//    fun delete(fUser: FUser): FUser {
//        repository.delete(fUser)
//        return fUser
//    }
//
//    fun deleteAllFUser() {
//        repository.deleteAllFUser()
//    }
//
//    val allFUserLive: LiveData<List<FUser>>
//        get() = listFUserLive
//    val allFUser: List<Any>
//        get() = repository.getAllFUser()
//
//    fun getItemHeader(): LiveData<FUser>? {
//        return itemHeader
//    }


    private val TAG = TestViewModel::class.java.simpleName
    val datas = MutableLiveData<FUser>()


//    fun getDetail() {
//        return
////        getMainUseCase.savePhotoId(id)
//        getMainUseCase.execute(
//            onSuccess = {
////                isLoad.value = true
//                datas.value = it
//            },
//            onError = {
//                it.printStackTrace()
//            }
//    }




    val domainData = MutableLiveData<List<FUser>>()
    val dataFArea = MutableLiveData<List<FArea>>()
    val fareaBean = MutableLiveData<FArea>()



    fun getRetrieveRemoteData() {

        Log.d("result", "Cek 1")
        getFAreaUseCase.execute(
                onSuccess = {
                    dataFArea.value = it
                    fareaBean.value = it[0]
                    Log.d("result", "Yes ada bos $it")
                },
                onError = {
                    it.printStackTrace()
                }
        )

        Log.d("result", "Cek 2")

    }

    fun createFArea() {
        val newFArea = FArea(0, 0, "TEST", "", "Test Aja",
                105, 11298240, true, Date(), Date(), "bagus")
//        val newFArea = FArea(0, 0, "TEST", "", "Test Aja",
//                105, 11298240, true, "bagus")
        createNote(newFArea)

    }


    fun test(): String {
        Log.d("result", "Hello this is test")

//        Log.d("result", "Hasilny: ${repository.getRemoteAllFArea().blockingGet().size}")

        return "Jos Bos"
    }


//    private val apiService: ApiService? = null
    private val disposable = CompositeDisposable()

    private fun createNote(fArea: FArea) {
        disposable.add(
                getFAreaUseCase.createRemoteFArea(fArea)
                        .map {
                            it
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<FArea>() {
                            override fun onSuccess(fArea1: FArea) {
                                Log.d("result", "Masuk bos ${fArea1}")
                            }
                            override fun onError(e: Throwable) {
                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
                            }
                        }))

    }


}