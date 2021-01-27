package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FtSalesh
import com.erp.distribution.sfa.domain.usecase.GetFtSaleshUseCase
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

class FSaleshViewModel @ViewModelInject constructor(
    private val getFtSaleshUseCase: GetFtSaleshUseCase,
    private val preferencesManager: PreferencesManager,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    private val TAG = FSaleshViewModel::class.java.simpleName

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val ftSaleshEventChannel = Channel<FtSaleshEvent>()
    val ftSaleshEvent = ftSaleshEventChannel.receiveAsFlow()

    private val ftSaleshFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        getFtSaleshUseCase.getCacheAllFtSaleshFlow(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val ftSaleshLive = ftSaleshFlow.asLiveData()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onItemSelected(ftSalesh: FtSalesh) = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToEditFtSaleshScreen(ftSalesh))
    }

    fun onItemCheckedChanged(ftSalesh: FtSalesh, isChecked: Boolean) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.putCacheFtSalesh(ftSalesh.copy(selected = isChecked))
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FtSalesh error  ${it.message}")
                        },
                        {

                        }
                )
        )

    }

    fun onItemSwiped(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.deleteCacheFtSalesh(ftSalesh)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FtSalesh error  ${it.message}")
                        },
                        {

                        }
                )
        )

        ftSaleshEventChannel.send(FtSaleshEvent.ShowUndoDeleteFtSaleshMessage(ftSalesh))
    }

    fun onUndoDeleteClick(ftSalesh: FtSalesh) = viewModelScope.launch {
//        taskDao.insert(task)
//        getFtSaleshUseCase.addCacheFtSalesh(task)
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.addCacheFtSalesh(ftSalesh)
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

    fun onAddNewFtSaleshClick() = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToAddFtSaleshScreen)
    }

    fun onAddEditResult(result: Int) {
        when (result) {
            ADD_TASK_RESULT_OK -> showFtSaleshSavedConfirmationMessage("Data added")
            EDIT_TASK_RESULT_OK -> showFtSaleshSavedConfirmationMessage("Data updated")
        }
    }

    private fun showFtSaleshSavedConfirmationMessage(text: String) = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.ShowFtSaleshSavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToDeleteAllCompletedScreen)
    }

    sealed class FtSaleshEvent {
        object NavigateToAddFtSaleshScreen : FtSaleshEvent()
        data class NavigateToEditFtSaleshScreen(val ftSalesh: FtSalesh) : FtSaleshEvent()
        data class ShowUndoDeleteFtSaleshMessage(val ftSalesh: FtSalesh) : FtSaleshEvent()
        data class ShowFtSaleshSavedConfirmationMessage(val msg: String) : FtSaleshEvent()
        object NavigateToDeleteAllCompletedScreen : FtSaleshEvent()

        data class NavigateBackWithResult(val result: Int) : FSaleshViewModel.FtSaleshEvent()

    }
}