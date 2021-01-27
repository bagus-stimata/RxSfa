package com.erp.distribution.sfa.presentation.ui.material.material_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterial
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
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

class FMaterialViewModel @ViewModelInject constructor(
    private val getFMaterialUseCase: GetFMaterialUseCase,
    private val preferencesManager: PreferencesManager,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    private val TAG = FMaterialViewModel::class.java.simpleName

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val fMaterialEventChannel = Channel<FMaterialEvent>()
    val fMaterialEvent = fMaterialEventChannel.receiveAsFlow()

    private val fMaterialFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        getFMaterialUseCase.getCacheAllFMaterialFlow(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val fMaterialLive = fMaterialFlow.asLiveData()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onItemSelected(fMaterial: FMaterial) = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEvent.NavigateToEditFMaterialScreen(fMaterial))
    }

    fun onItemCheckedChanged(fMaterial: FMaterial, isChecked: Boolean) = viewModelScope.launch {
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

    fun onItemSwiped(fMaterial: FMaterial) = viewModelScope.launch {
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

        fMaterialEventChannel.send(FMaterialEvent.ShowUndoDeleteFMaterialMessage(fMaterial))
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

    fun onAddNewFMaterialClick() = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEvent.NavigateToAddFMaterialScreen)
    }

    fun onAddEditResult(result: Int) {
        when (result) {
            ADD_TASK_RESULT_OK -> showFMaterialSavedConfirmationMessage("Data added")
            EDIT_TASK_RESULT_OK -> showFMaterialSavedConfirmationMessage("Data updated")
        }
    }

    private fun showFMaterialSavedConfirmationMessage(text: String) = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEvent.ShowFMaterialSavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEvent.NavigateToDeleteAllCompletedScreen)
    }

    sealed class FMaterialEvent {
        object NavigateToAddFMaterialScreen : FMaterialEvent()
        data class NavigateToEditFMaterialScreen(val fMaterial: FMaterial) : FMaterialEvent()
        data class ShowUndoDeleteFMaterialMessage(val fMaterial: FMaterial) : FMaterialEvent()
        data class ShowFMaterialSavedConfirmationMessage(val msg: String) : FMaterialEvent()
        object NavigateToDeleteAllCompletedScreen : FMaterialEvent()

        data class NavigateBackWithResult(val result: Int) : FMaterialViewModel.FMaterialEvent()

    }
}