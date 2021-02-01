package com.erp.distribution.sfa.presentation.ui.customer.customer_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.domain.usecase.GetFCustomerUseCase
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

class CustomerViewModel @ViewModelInject constructor(
    private val getFCustomerUseCase: GetFCustomerUseCase,
    private val preferencesManager: PreferencesManager,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    private val TAG = CustomerViewModel::class.java.simpleName

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val fCustomerEventChannel = Channel<CustomerEvent>()
    val fCustomerEvent = fCustomerEventChannel.receiveAsFlow()

    private val fCustomerFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        getFCustomerUseCase.getCacheAllFCustomerFlow(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }

    val fCustomerLive = fCustomerFlow.asLiveData()
//    val tasks = getFCustomerUseCase.getCacheAllFCustomer()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onCustomerSelected(fCustomerEntity: FCustomerEntity) = viewModelScope.launch {
        fCustomerEventChannel.send(CustomerEvent.NavigateToEditCustomerScreen(fCustomerEntity))
    }

    fun onFCustomerCheckedChanged(fCustomerEntity: FCustomerEntity, isChecked: Boolean) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.putCacheFCustomer(fCustomerEntity.copy(selected = isChecked))
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FCustomer error  ${it.message}")
                        },
                        {

                        }
                )
        )

    }

    fun onCustomerSwiped(fCustomerEntity: FCustomerEntity) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.deleteCacheFCustomer(fCustomerEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FCustomer error  ${it.message}")
                        },
                        {

                        }
                )
        )

        fCustomerEventChannel.send(CustomerEvent.ShowUndoDeleteCustomerMessage(fCustomerEntity))
    }

    fun onUndoDeleteClick(fCustomerEntity: FCustomerEntity) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.addCacheFCustomer(fCustomerEntity)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FCustomer error  ${it.message}")
                        },
                        {

                        }
                )
        )
    }

    fun onAddNewCustomerClick() = viewModelScope.launch {
        fCustomerEventChannel.send(CustomerEvent.NavigateToAddCustomerScreen)
    }

    fun onAddEditResult(result: Int) {
        when (result) {
            ADD_TASK_RESULT_OK -> showCustomerSavedConfirmationMessage("Data added")
            EDIT_TASK_RESULT_OK -> showCustomerSavedConfirmationMessage("Data updated")
        }
    }

    private fun showCustomerSavedConfirmationMessage(text: String) = viewModelScope.launch {
        fCustomerEventChannel.send(CustomerEvent.ShowCustomerSavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        fCustomerEventChannel.send(CustomerEvent.NavigateToDeleteAllCompletedScreen)
    }
    fun onConfirmDeleteClick() = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.deleteAllCacheFCustomer()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                },
                {
                    Log.d(TAG, "#result FCustomer error  ${it.message}")
                },
                {

                }
            )
        )
    }


    sealed class CustomerEvent {
        object NavigateToAddCustomerScreen : CustomerEvent()
        data class NavigateToEditCustomerScreen(val fCustomerEntity: FCustomerEntity) : CustomerEvent()
        data class ShowUndoDeleteCustomerMessage(val fCustomerEntity: FCustomerEntity) : CustomerEvent()
        data class ShowCustomerSavedConfirmationMessage(val msg: String) : CustomerEvent()
        object NavigateToDeleteAllCompletedScreen : CustomerEvent()

        data class NavigateBackWithResult(val result: Int) : CustomerViewModel.CustomerEvent()

    }
}