package com.erp.distribution.sfa.presentation.ui.material.material_addedit

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.sfa.presentation.ui.test.mvvm_todo.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.test.mvvm_todo.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditFMaterialViewModel @ViewModelInject constructor(
    private val getFMaterialUseCase: GetFMaterialUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val TAG = AddEditFMaterialViewModel::class.java.simpleName

    val fMaterial = state.get<FMaterialEntity>("fMaterial") // Mengikuti nama pada argument yang ada di nav_graph.xml

    var fMaterialName = state.get<String>("fMaterialName") ?: fMaterial?.pname ?: ""
        set(value) {
            field = value
            state.set("fMaterialName", value)
        }

    var fMaterialImportance = state.get<Boolean>("statusActive") ?: fMaterial?.isStatusActive ?: false
        set(value) {
            field = value
            state.set("statusActive", value)
        }

    private val addEditFMaterialEventChannel = Channel<AddEditMaterialEvent>()
    val addEditFMaterialEvent = addEditFMaterialEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (fMaterialName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        if (fMaterial != null) {
            val updatedFMaterial = fMaterial.copy(pname = fMaterialName, isStatusActive = fMaterialImportance )
            updateFMaterial(updatedFMaterial)
        } else {
            val newFMaterial = FMaterialEntity(pname = fMaterialName, isStatusActive = fMaterialImportance )
            createFMaterial(newFMaterial)
        }
    }

    private fun createFMaterial(fMaterialEntity: FMaterialEntity) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.addCacheFMaterial(fMaterialEntity)
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

        addEditFMaterialEventChannel.send(AddEditMaterialEvent.NavigateBackWithResult(ADD_TASK_RESULT_OK))
    }

    private fun updateFMaterial(fMaterialEntity: FMaterialEntity) = viewModelScope.launch {
//        taskDao.update(task)
//        getFMaterialUseCase.putCacheFMaterial(task)
//        addEditTaskEventChannel.send(AddEditTaskEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.putCacheFMaterial(fMaterialEntity)
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
        addEditFMaterialEventChannel.send(AddEditMaterialEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditFMaterialEventChannel.send(AddEditMaterialEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditMaterialEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditMaterialEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditMaterialEvent()
    }
}