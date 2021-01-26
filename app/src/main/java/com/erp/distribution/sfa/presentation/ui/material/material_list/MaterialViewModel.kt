package com.erp.distribution.sfa.presentation.ui.material.material_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterial
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.sfa.presentation.ui.material.material_addedit.AddEditMaterialViewModel
import com.erp.distribution.sfa.presentation.ui.test.mvvm_todo.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.test.mvvm_todo.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MaterialViewModel @ViewModelInject constructor(
    private val getFMaterialUseCase: GetFMaterialUseCase,
    private val preferencesManager: PreferencesManager,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    private val TAG = MaterialViewModel::class.java.simpleName

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val fMaterialEventChannel = Channel<MaterialEvent>()
    val fMaterialEvent = fMaterialEventChannel.receiveAsFlow()

    private val fMaterialFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
//        taskDao.getTasks(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
        getFMaterialUseCase.getCacheAllFMaterialFlow(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val fMaterialLive = fMaterialFlow.asLiveData()
//    val tasks = getFMaterialUseCase.getCacheAllFMaterial()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onMaterialSelected(fMaterial: FMaterial) = viewModelScope.launch {
        fMaterialEventChannel.send(MaterialEvent.NavigateToEditMaterialScreen(fMaterial))
    }

    fun onFMaterialCheckedChanged(fMaterial: FMaterial, isChecked: Boolean) = viewModelScope.launch {
//        getFMaterialUseCase.putCacheFMaterial(task.copy(selected = isChecked))
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.putCacheFMaterial(fMaterial.copy(selected = isChecked))
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

    }

    fun onMaterialSwiped(fMaterial: FMaterial) = viewModelScope.launch {
//        taskDao.delete(task)
//        tasksEventChannel.send(TasksEvent.ShowUndoDeleteTaskMessage(task))
//        getFMaterialUseCase.deleteCacheFMaterial(task)
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.deleteCacheFMaterial(fMaterial)
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

        fMaterialEventChannel.send(MaterialEvent.ShowUndoDeleteMaterialMessage(fMaterial))
    }

    fun onUndoDeleteClick(fMaterial: FMaterial) = viewModelScope.launch {
//        taskDao.insert(task)
//        getFMaterialUseCase.addCacheFMaterial(task)
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
    }

    fun onAddNewMaterialClick() = viewModelScope.launch {
        fMaterialEventChannel.send(MaterialEvent.NavigateToAddMaterialScreen)
    }

    fun onAddEditResult(result: Int) {
        when (result) {
            ADD_TASK_RESULT_OK -> showMaterialSavedConfirmationMessage("Data added")
            EDIT_TASK_RESULT_OK -> showMaterialSavedConfirmationMessage("Data updated")
        }
    }

    private fun showMaterialSavedConfirmationMessage(text: String) = viewModelScope.launch {
        fMaterialEventChannel.send(MaterialEvent.ShowMaterialSavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        fMaterialEventChannel.send(MaterialEvent.NavigateToDeleteAllCompletedScreen)
    }

    sealed class MaterialEvent {
        object NavigateToAddMaterialScreen : MaterialEvent()
        data class NavigateToEditMaterialScreen(val fMaterial: FMaterial) : MaterialEvent()
        data class ShowUndoDeleteMaterialMessage(val fMaterial: FMaterial) : MaterialEvent()
        data class ShowMaterialSavedConfirmationMessage(val msg: String) : MaterialEvent()
        object NavigateToDeleteAllCompletedScreen : MaterialEvent()

        data class NavigateBackWithResult(val result: Int) : MaterialViewModel.MaterialEvent()

    }
}