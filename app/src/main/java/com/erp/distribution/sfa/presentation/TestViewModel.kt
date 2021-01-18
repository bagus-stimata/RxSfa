package com.erp.distribution.sfa.presentation

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.usecase.GetMainUseCase
import com.erp.distribution.sfa.security_model.FUser
import java.util.*

class TestViewModel @ViewModelInject constructor(private val getMainUseCase: GetMainUseCase) : ViewModel()  {

//    init {
//        repository = FUserRepository(application)
//        listFUserLive = repository.getAllFUserLive()
//    }
//
//    private val repository: FUserRepository
//    private val fCustomerRepository: FCustomerRepository? = null
//    private val fCustomerGroupRepository: FCustomerGroupRepository? = null
//    private val fAreaRepository: FAreaRepository? = null
//    private val fSubAreaRepository: FSubAreaRepository? = null
//    private val fMaterialRepository: FMaterialRepository? = null
//    private val fMaterialGroup3Repository: FMaterialGroup3Repository? = null
//    private val listFUserLive: LiveData<List<FUser>>
//    private val listFUser: List<FUser> = ArrayList<FUser>()
//    protected var itemHeader: MutableLiveData<FUser>? = null
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

    fun getRetrieveRemoteData() {

        Log.d("result", "Cek 1")
        getMainUseCase.execute(
            onSuccess = {
//                isLoad.value = true
                domainData.value = it
                Log.d("result", "Yes ada bos $it")
            },
            onError = {
                it.printStackTrace()
            }
        )
        Log.d("result", "Cek 2")

    }

    fun getCacheData(): LiveData<List<FUser>> {
        return getMainUseCase.getCacheAllData()
    }
    fun addCacheData() {
//        getMainUseCase.addCacheData(FUser(1, "bagus.stimata@gmail.com", "bagus", "hacker", "hacker", "hacker", "Bagus Winarno", "0812333", "catatan baik", null, null, false, 1, 1, 1 , false, false, Date(), Date(), "userbagus" ))
        getMainUseCase.addCacheData(FUser(1, "bagus.stimata@gmail.com","bagus",
            "hacker", "hacker", "hacker", "Bagus Winarno",
            "0821222", "Ini catatan oke", 1, 1, Date(), Date(),
            "userBagus"))
    }

    fun deleteAllCacheData() {
        getMainUseCase.deleteAllCacheData()
    }

    fun test(): String {
        Log.d("result", "Hello this is test")
        return "Jos Bos"
    }



}