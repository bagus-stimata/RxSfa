package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.sfa.presentation.ui.test.mvvm_todo.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.test.mvvm_todo.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditFtSaleshViewModel @ViewModelInject constructor(
    private val getFtSaleshUseCase: GetFtSaleshUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val TAG = AddEditFtSaleshViewModel::class.java.simpleName

    val ftSalesh = state.get<FtSaleshEntity>("ftSalesh") // Mengikuti nama pada argument yang ada di nav_graph.xml

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

    private val addEditFtSaleshEventChannel = Channel<AddEditMaterialEvent>()
    val addEditFtSaleshEvent = addEditFtSaleshEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (ftSaleshName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        if (ftSalesh != null) {
            val updatedFtSalesh = ftSalesh.copy(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
            updateFtSalesh(updatedFtSalesh)
        } else {
            val newFtSalesh = FtSaleshEntity(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
            createFtSalesh(newFtSalesh)
        }
    }

    private fun createFtSalesh(ftSaleshEntity: FtSaleshEntity) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.addCacheFtSalesh(ftSaleshEntity)
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

        addEditFtSaleshEventChannel.send(AddEditMaterialEvent.NavigateBackWithResult(ADD_TASK_RESULT_OK))
    }

    private fun updateFtSalesh(ftSaleshEntity: FtSaleshEntity) = viewModelScope.launch {
//        taskDao.update(task)
//        getFtSaleshUseCase.putCacheFtSalesh(task)
//        addEditTaskEventChannel.send(AddEditTaskEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.putCacheFtSalesh(ftSaleshEntity)
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
        addEditFtSaleshEventChannel.send(AddEditMaterialEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(AddEditMaterialEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditMaterialEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditMaterialEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditMaterialEvent()
    }
}