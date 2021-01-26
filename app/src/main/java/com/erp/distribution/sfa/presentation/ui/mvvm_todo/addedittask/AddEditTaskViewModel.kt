package com.erp.distribution.sfa.presentation.ui.mvvm_todo.addedittask

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erp.distribution.sfa.data.source.entity.FMaterial
import com.erp.distribution.sfa.data.source.entity.Task
import com.erp.distribution.sfa.data.source.local.dao.TaskDao
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.sfa.presentation.ui.mvvm_todo.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.mvvm_todo.EDIT_TASK_RESULT_OK
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditTaskViewModel @ViewModelInject constructor(
//    private val taskDao: TaskDao,
    private val getFMaterialUseCase: GetFMaterialUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val task = state.get<FMaterial>("task")

    var taskName = state.get<String>("taskName") ?: task?.pname ?: ""
        set(value) {
            field = value
            state.set("taskName", value)
        }

    var taskImportance = state.get<Boolean>("taskImportance") ?: task?.isStatusActive ?: false
        set(value) {
            field = value
            state.set("taskImportance", value)
        }

    private val addEditTaskEventChannel = Channel<AddEditTaskEvent>()
    val addEditTaskEvent = addEditTaskEventChannel.receiveAsFlow()

    fun onSaveClick() {
        if (taskName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        if (task != null) {
            val updatedTask = task.copy(pname = taskName, isStatusActive = taskImportance )
            updateTask(updatedTask)
        } else {
            val newTask = FMaterial(pname = taskName, isStatusActive = taskImportance )
            createTask(newTask)
        }
    }

    private fun createTask(task: FMaterial) = viewModelScope.launch {
//        taskDao.insert(task)
        getFMaterialUseCase.addCacheFMaterial(task)
        addEditTaskEventChannel.send(AddEditTaskEvent.NavigateBackWithResult(ADD_TASK_RESULT_OK))
    }

    private fun updateTask(task: FMaterial) = viewModelScope.launch {
//        taskDao.update(task)
        getFMaterialUseCase.putCacheFMaterial(task)
        addEditTaskEventChannel.send(AddEditTaskEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))
    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditTaskEventChannel.send(AddEditTaskEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditTaskEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditTaskEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditTaskEvent()
    }
}