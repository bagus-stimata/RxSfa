package com.erp.distribution.sfa.presentation.ui.material.material_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.sfa.domain.model.FMaterialGroup3
import com.erp.distribution.sfa.domain.usecase.GetFMaterialGroup3UseCase
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
        private val getFMaterialGroup3UseCase: GetFMaterialGroup3UseCase,
        private val preferencesManager: PreferencesManager,
        @Assisted private val state: SavedStateHandle
) : ViewModel() {
    private val TAG = FMaterialViewModel::class.java.simpleName

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val fMaterialEventChannel = Channel<FMaterialEntityEvent>()
    val fMaterialEvent = fMaterialEventChannel.receiveAsFlow()

    val fMaterialGroup3Live = getFMaterialGroup3UseCase.getCacheAllFMaterialGroup3()
    fun getCacheAllFMaterialGroup3Live(id: Int): LiveData<FMaterialGroup3Entity> =
        getFMaterialGroup3UseCase.getCacheFMaterialGroup3ById(id)

    private val fMaterialFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        getFMaterialUseCase.getCacheAllFMaterialFlow(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
//        getFMaterialEntityUseCase.getCacheAllFMaterialEntityDomainFlow(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val fMaterialLive = fMaterialFlow.asLiveData()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onItemSelected(fMaterial: FMaterialEntity) = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEntityEvent.NavigateToEditFMaterialEntityScreen(fMaterial))
    }

    fun onItemCheckedChanged(fMaterial: FMaterialEntity, isChecked: Boolean) = viewModelScope.launch {
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

    fun onItemSwiped(fMaterial: FMaterialEntity) = viewModelScope.launch {
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

        fMaterialEventChannel.send(FMaterialEntityEvent.ShowUndoDeleteFMaterialEntityMessage(fMaterial))
    }

    fun onUndoDeleteClick(fMaterial: FMaterialEntity) = viewModelScope.launch {
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

    fun onAddNewFMaterialEntityClick() = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEntityEvent.NavigateToAddFMaterialEntityScreen)
    }

    fun onAddEditResult(result: Int) {
        when (result) {
            ADD_TASK_RESULT_OK -> showFMaterialEntitySavedConfirmationMessage("Data added")
            EDIT_TASK_RESULT_OK -> showFMaterialEntitySavedConfirmationMessage("Data updated")
        }
    }

    private fun showFMaterialEntitySavedConfirmationMessage(text: String) = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEntityEvent.ShowFMaterialEntitySavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        fMaterialEventChannel.send(FMaterialEntityEvent.NavigateToDeleteAllCompletedScreen)
    }
    fun onConfirmDeleteClick() = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.deleteAllCacheFMaterial()
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

    sealed class FMaterialEntityEvent {
        object NavigateToAddFMaterialEntityScreen : FMaterialEntityEvent()
        data class NavigateToEditFMaterialEntityScreen(val fMaterial: FMaterialEntity) : FMaterialEntityEvent()
        data class ShowUndoDeleteFMaterialEntityMessage(val fMaterial: FMaterialEntity) : FMaterialEntityEvent()
        data class ShowFMaterialEntitySavedConfirmationMessage(val msg: String) : FMaterialEntityEvent()
        object NavigateToDeleteAllCompletedScreen : FMaterialEntityEvent()

        data class NavigateBackWithResult(val result: Int) : FMaterialViewModel.FMaterialEntityEvent()

    }
}