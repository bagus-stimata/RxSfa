package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_qty

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.domain.model.FtSalesdItems
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

    var userViewState = UserViewState()

    var ftSaleshRefno: Long = 0
    var ftSalesh = FtSalesh()
    var ftSalesdItemsId: Long = 0
    var ftSalesdItems = FtSalesdItems()


//    val userViewState = state.get<UserViewState>("userViewStateActive")
//    val ftSalesh = state.get<FtSalesh>("ftSalesh")

//    var ftSaleshName = state.get<String>("ftSaleshName") ?: ftSalesh?.invoiceno ?: ""
//        set(value) {
//            field = value
//            state.set("ftSaleshName", value)
//        }
//
//    var ftSaleshImportance = state.get<Boolean>("statusActive") ?: ftSalesh?.isValidOrder ?: false
//        set(value) {
//            field = value
//            state.set("statusActive", value)
//        }

    private val addEditFtSaleshEventChannel = Channel<AddEditFtSaleshQtyEvent>()
    val addEditFtSaleshEvent = addEditFtSaleshEventChannel.receiveAsFlow()

    fun onSaveClick() {
//        if (ftSaleshName.isBlank()) {
//            showInvalidInputMessage("Name cannot be empty")
//            return
//        }
//
//        onPopUpBackStackWithTheResult()
//
//        if (ftSalesh != null) {
//            val updatedFtSalesh = ftSalesh.copy(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
////            updateFtSalesh(updatedFtSalesh)
//        } else {
//            val newFtSalesh = FtSalesh(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
////            createFtSalesh(newFtSalesh)
//        }

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
            AddEditFtSaleshQtyEvent.NavigateBackWithResult(
                ADD_TASK_RESULT_OK
            )
        )
    }

    private fun updateFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {

        addEditFtSaleshEventChannel.send(
            AddEditFtSaleshQtyEvent.NavigateBackWithResult(
                EDIT_TASK_RESULT_OK
            )
        )

    }

    fun onPopUpBackStackWithTheResult() = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(
            AddEditFtSaleshQtyEvent.NavigateBackWithResult(
                EDIT_TASK_RESULT_OK
            )
        )
    }

    fun onUpdateQtyOke() = viewModelScope.launch {
//        val tempUserViewState = UserViewState()
//        val tempFtSalesh = FtSalesh()
//        val tempFtSalesdItems = FtSalesdItems()
        addEditFtSaleshEventChannel.send(AddEditFtSaleshQtyEvent.NavigateToFtSaleshCustomerOrder(userViewState!!, ftSalesh, ftSalesdItems))
    }

    fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(AddEditFtSaleshQtyEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditFtSaleshQtyEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditFtSaleshQtyEvent()

        data class NavigateToFtSaleshCustomerOrder(var userViewState: UserViewState, val ftSalesh: FtSalesh, val ftSalesdItems: FtSalesdItems) : AddEditFtSaleshQtyEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditFtSaleshQtyEvent()
    }

}