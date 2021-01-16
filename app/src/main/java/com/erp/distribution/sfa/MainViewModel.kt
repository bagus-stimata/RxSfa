package com.erp.distribution.sfa

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.usecase.GetMainUseCase
import com.erp.distribution.sfa.domain.usecase.GetPhotoDetailUseCase
import java.util.*

class MainViewModel @ViewModelInject constructor() : ViewModel()  {

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


}