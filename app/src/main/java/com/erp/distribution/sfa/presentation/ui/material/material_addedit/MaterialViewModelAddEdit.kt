package com.erp.distribution.sfa.presentation.ui.material.material_addedit

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.FMaterial
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

class MaterialViewModelAddEdit @ViewModelInject constructor(
    private val getFMaterialUseCase: GetFMaterialUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val TAG = MaterialViewModelAddEdit::class.java.simpleName

    val fMaterial = state.get<FMaterial>("fMaterial")

    var fMaterialName = state.get<String>("materialName") ?: fMaterial?.pname ?: ""
        set(value) {
            field = value
            state.set("materialName", value)
        }

    var fMaterialImportance = state.get<Boolean>("statusActive") ?: fMaterial?.isStatusActive ?: false
        set(value) {
            field = value
            state.set("statusActive", value)
        }

    private val addEditMaterialEventChannel = Channel<AddEditMaterialEvent>()
    val addEditMaterialEvent = addEditMaterialEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (fMaterialName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        if (fMaterial != null) {
            val updatedFMaterial = fMaterial.copy(pname = fMaterialName, isStatusActive = fMaterialImportance )
            updateFMaterial(updatedFMaterial)
        } else {
            val newFMaterial = FMaterial(pname = fMaterialName, isStatusActive = fMaterialImportance )
            createFMaterial(newFMaterial)
        }
    }

    private fun createFMaterial(fMaterial: FMaterial) = viewModelScope.launch {
//        taskDao.insert(task)
//        getFMaterialUseCase.addCacheFMaterial(fMaterial)
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.addCacheFMaterial(fMaterial)
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

        addEditMaterialEventChannel.send(AddEditMaterialEvent.NavigateBackWithResult(ADD_TASK_RESULT_OK))
    }

    private fun updateFMaterial(fMaterial: FMaterial) = viewModelScope.launch {
//        taskDao.update(task)
//        getFMaterialUseCase.putCacheFMaterial(task)
//        addEditTaskEventChannel.send(AddEditTaskEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.putCacheFMaterial(fMaterial)
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
        addEditMaterialEventChannel.send(AddEditMaterialEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))

    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditMaterialEventChannel.send(AddEditMaterialEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditMaterialEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditMaterialEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditMaterialEvent()
    }
}