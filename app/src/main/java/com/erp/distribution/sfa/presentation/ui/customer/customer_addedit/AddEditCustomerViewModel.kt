package com.erp.distribution.sfa.presentation.ui.customer.customer_addedit

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.usecase.GetFCustomerUseCase
import com.erp.distribution.sfa.presentation.ui.customer.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.customer.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditCustomerViewModel @ViewModelInject constructor(
    private val getFCustomerUseCase: GetFCustomerUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val TAG = AddEditCustomerViewModel::class.java.simpleName

    val fCustomer = state.get<FCustomer>("fCustomer")

    var fCustomerName = state.get<String>("customerName") ?: fCustomer?.custname ?: ""
        set(value) {
            field = value
            state.set("customerName", value)
        }

    var fCustomerImportance = state.get<Boolean>("statusActive") ?: fCustomer?.isStatusActive ?: false
        set(value) {
            field = value
            state.set("statusActive", value)
        }

    private val addEditCustomerEventChannel = Channel<AddEditCustomerEvent>()
    val addEditCustomerEvent = addEditCustomerEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (fCustomerName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        if (fCustomer != null) {
            val updatedFCustomer = fCustomer.copy(custname = fCustomerName, isStatusActive = fCustomerImportance )
            updateFCustomer(updatedFCustomer)
        } else {
            val newFCustomer = FCustomer(custname = fCustomerName, isStatusActive = fCustomerImportance )
            createFCustomer(newFCustomer)
        }
    }

    private fun createFCustomer(fCustomer: FCustomer) = viewModelScope.launch {
//        taskDao.insert(task)
//        getFCustomerUseCase.addCacheFCustomer(fCustomer)
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.addCacheFCustomer(fCustomer.toEntity())
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                },
                {
                    Log.d(TAG, "#result CUSTOMER error  ${it.message}")

                },
                {

                }
            )
        )

        addEditCustomerEventChannel.send(AddEditCustomerEvent.NavigateBackWithResult(ADD_TASK_RESULT_OK))
    }

    private fun updateFCustomer(fCustomer: FCustomer) = viewModelScope.launch {
//        taskDao.update(task)
//        getFCustomerUseCase.putCacheFCustomer(task)
//        addEditTaskEventChannel.send(AddEditTaskEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.putCacheFCustomer(fCustomer.toEntity())
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                },
                {
                    Log.d(TAG, "#result CUSTOMER error  ${it.message}")

                },
                {

                }
            )
        )
        addEditCustomerEventChannel.send(AddEditCustomerEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditCustomerEventChannel.send(AddEditCustomerEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditCustomerEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditCustomerEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditCustomerEvent()
    }
}