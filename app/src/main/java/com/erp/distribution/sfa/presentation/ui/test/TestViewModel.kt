package com.erp.distribution.sfa.presentation.ui.test

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.repository.FUserRepository
import com.erp.distribution.sfa.domain.usecase.GetFAreaUseCase
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.presentation.extention.map
import com.erp.distribution.sfa.data.source.entity_security.FUser
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class TestViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFAreaUseCase: GetFAreaUseCase,
    private val repository: FUserRepository
    ) : ViewModel()  {
//class TestViewModel @ViewModelInject constructor(private val repository: FAreaRepository) : ViewModel()  {

    init {
//        repository = FUserRepository(application)
//        listFUserLive = repository.getAllFUserLive()
//        repository.getRemoteAllFArea()

//        Log.d("result", "Oke kirim Sart")
//        repository.createRemoteFArea(FArea(877798, 0, "TEST", "", "Test Aja", 105, 11298240, true, Date(), Date(), "bagus"))
//        Log.d("result", "Oke Kirim Selesai")
        subscribeNoteResult()
        refreshCacheFUser()
    }

    fun refreshCacheFUser() {
        Log.d(TAG, "#result Refresh FUser")
//        println("#result Refresh FUser")
//        getFUserUseCase.execute(
//            onSuccess = {
////                listFUser = it
//                        Log.d(TAG, "#result $it")
//            },
//            onError = {
//                it.printStackTrace()
//            }
//        )

//        Log.d(TAG, "#result dari Cache ${getFUserUseCase.getCacheAllFUser().value}")
    }


    private val TAG = TestViewModel::class.java.simpleName
    val datas = MutableLiveData<FUser>()





    val domainData = MutableLiveData<List<FUser>>()
    val dataFArea = MutableLiveData<List<FAreaEntity>>()
    val fareaBean = MutableLiveData<FAreaEntity>()

    private lateinit var notesResult: LiveData<List<FUser>>
    fun listenNotesResult(): LiveData<List<FUser>> {
        return notesResult
    }


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
        val newFArea = FAreaEntity(0, 0, "TEST", "", "Test Aja",
                105, 11298240, true, Date(), Date(), "bagus")
//        val newFArea = FArea(0, 0, "TEST", "", "Test Aja",
//                105, 11298240, true, "bagus")
        createNote(newFArea)

    }


    fun test(): String {
//        Log.d("result", "Hello this is test")

//        Log.d(TAG, "#result : ${getFUserUseCase.getCacheAllFUserBiasa()}")
        Log.d(TAG, "#result : ${repository.getCacheFUserByUsername("bagus").value}")

        return "Jos Bos"
    }


    private fun subscribeNoteResult() {
//        notesResult = getFUserUseCase.getCacheAllFUser()
        notesResult = getMappedFUser()

    }
//    private val apiService: ApiService? = null
    private val disposable = CompositeDisposable()

    private fun createNote(fAreaEntity: FAreaEntity) {
        disposable.add(
                getFAreaUseCase.createRemoteFArea(SecurityUtil.getAuthHeader("bagus", "hacker"), fAreaEntity)
                        .map {
                            it
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<FAreaEntity>() {
                            override fun onSuccess(fAreaEntity1: FAreaEntity) {
                                Log.d("result", "Masuk bos ${fAreaEntity1}")
                            }
                            override fun onError(e: Throwable) {
                                Log.d("result", "Error bos ${e.printStackTrace().toString()}")
                            }
                        }))

    }


    private fun getMappedFUser(): LiveData<List<FUser>>{
        return getFUserUseCase.getCacheAllFUser().map { it.map {
//            contributorEntityMapper.mapToDomain(it)
            //rubah rubah disini
            it.id = 9999
//            it.username = "Ganteng Banget"

            it //ini hasil ahirnya
        } }
    }

}