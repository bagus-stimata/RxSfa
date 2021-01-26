package com.erp.distribution.sfa.presentation.ui.mvvm_todo.tasks

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterial
import com.erp.distribution.sfa.data.source.entity.Task
import com.erp.distribution.sfa.data.source.local.dao.TaskDao
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.sfa.presentation.ui.mvvm_todo.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.mvvm_todo.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TasksViewModel @ViewModelInject constructor(
//    private val taskDao: TaskDao,
    private val getFMaterialUseCase: GetFMaterialUseCase,
    private val preferencesManager: PreferencesManager,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    private val TAG = TasksViewModel::class.java.simpleName

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val tasksEventChannel = Channel<TasksEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()

    private val tasksFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
//        taskDao.getTasks(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
        getFMaterialUseCase.getCacheAllFMaterialFlow(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val tasks = tasksFlow.asLiveData()
//    val tasks = getFMaterialUseCase.getCacheAllFMaterial()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onTaskSelected(task: FMaterial) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.NavigateToEditTaskScreen(task))
    }

    fun onTaskCheckedChanged(task: FMaterial, isChecked: Boolean) = viewModelScope.launch {
//        getFMaterialUseCase.putCacheFMaterial(task.copy(selected = isChecked))
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.putCacheFMaterial(task.copy(selected = isChecked))
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

    fun onTaskSwiped(task: FMaterial) = viewModelScope.launch {
//        taskDao.delete(task)
//        tasksEventChannel.send(TasksEvent.ShowUndoDeleteTaskMessage(task))
//        getFMaterialUseCase.deleteCacheFMaterial(task)
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.deleteCacheFMaterial(task)
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

        tasksEventChannel.send(TasksEvent.ShowUndoDeleteTaskMessage(task))
    }

    fun onUndoDeleteClick(task: FMaterial) = viewModelScope.launch {
//        taskDao.insert(task)
//        getFMaterialUseCase.addCacheFMaterial(task)
        DisposableManager.add(Observable.fromCallable {
            getFMaterialUseCase.addCacheFMaterial(task)
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

    fun onAddNewTaskClick() = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.NavigateToAddTaskScreen)
    }

    fun onAddEditResult(result: Int) {
        when (result) {
            ADD_TASK_RESULT_OK -> showTaskSavedConfirmationMessage("Task added")
            EDIT_TASK_RESULT_OK -> showTaskSavedConfirmationMessage("Task updated")
        }
    }

    private fun showTaskSavedConfirmationMessage(text: String) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.ShowTaskSavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.NavigateToDeleteAllCompletedScreen)
    }

    sealed class TasksEvent {
        object NavigateToAddTaskScreen : TasksEvent()
        data class NavigateToEditTaskScreen(val task: FMaterial) : TasksEvent()
        data class ShowUndoDeleteTaskMessage(val task: FMaterial) : TasksEvent()
        data class ShowTaskSavedConfirmationMessage(val msg: String) : TasksEvent()
        object NavigateToDeleteAllCompletedScreen : TasksEvent()
    }
}