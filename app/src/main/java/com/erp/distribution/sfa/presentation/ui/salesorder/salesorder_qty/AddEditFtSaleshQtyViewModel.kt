package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_qty

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.salesorder.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.salesorder.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditFtSaleshQtyViewModel @ViewModelInject constructor(
    private val getFtSaleshUseCase: GetFtSaleshUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val TAG = AddEditFtSaleshQtyViewModel::class.java.simpleName

    val userViewState = state.get<UserViewState>("userViewStateActive")
    val ftSalesh = state.get<FtSalesh>("ftSalesh")

    var ftSaleshName = state.get<String>("ftSaleshName") ?: ftSalesh?.invoiceno ?: ""
        set(value) {
            field = value
            state.set("ftSaleshName", value)
        }

    var ftSaleshImportance = state.get<Boolean>("statusActive") ?: ftSalesh?.isValidOrder ?: false
        set(value) {
            field = value
            state.set("statusActive", value)
        }

    private val addEditFtSaleshEventChannel = Channel<AddEditSalesOrderEvent>()
    val addEditFtSaleshEvent = addEditFtSaleshEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (ftSaleshName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        popUpBackStackWithTheResult()

        if (ftSalesh != null) {
            val updatedFtSalesh = ftSalesh.copy(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
//            updateFtSalesh(updatedFtSalesh)
        } else {
            val newFtSalesh = FtSalesh(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
//            createFtSalesh(newFtSalesh)
        }

    }

    private fun createFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.addCacheFtSaleshDomain(ftSalesh)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                },
                {
                    Log.d(TAG, "#result MATERIAL error  ${it.message}")
                },
                {
                }
            )
        )

        addEditFtSaleshEventChannel.send(
            AddEditSalesOrderEvent.NavigateBackWithResult(
                ADD_TASK_RESULT_OK
            )
        )
    }

    private fun updateFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {

//        DisposableManager.add(Observable.fromCallable {
//            getFtSaleshUseCase.putCacheFtSaleshDomain(ftSalesh)
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe (
//                {
//                },
//                {
//                    Log.d(TAG, "#result MATERIAL error  ${it.message}")
//
//                },
//                {
//
//                }
//            )
//        )

        addEditFtSaleshEventChannel.send(
            AddEditSalesOrderEvent.NavigateBackWithResult(
                EDIT_TASK_RESULT_OK
            )
        )
    }

    fun popUpBackStackWithTheResult() = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(
            AddEditSalesOrderEvent.NavigateBackWithResult(
                EDIT_TASK_RESULT_OK
            )
        )
    }

    fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(AddEditSalesOrderEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditSalesOrderEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditSalesOrderEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditSalesOrderEvent()
    }
}