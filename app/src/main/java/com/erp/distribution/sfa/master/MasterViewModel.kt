package com.erp.distribution.sfa.master

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.erp.distribution.sfa.domain.repository.FUserRepository
import com.erp.distribution.sfa.domain.usecase.GetFAreaUseCase
import com.erp.distribution.sfa.domain.usecase.GetFUserUseCase
import com.erp.distribution.sfa.model.FCustomer
import com.erp.distribution.sfa.security_model.FUser
import io.reactivex.Single

class MasterViewModel @ViewModelInject constructor(
    private val getFUserUseCase: GetFUserUseCase,
    private val getFAreaUseCase: GetFAreaUseCase
) : ViewModel() {

    var userActive: FUser = FUser()


//    fun fetchCustomerFromRemote(): Single<List<FCustomer>> {
//
//    }
//
//    fun doSync_MaterialFromServer(): Unit {
//
//    }

}