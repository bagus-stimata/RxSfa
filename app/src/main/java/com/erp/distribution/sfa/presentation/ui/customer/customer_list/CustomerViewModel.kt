package com.erp.distribution.sfa.presentation.ui.customer.customer_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.customer.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.customer.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CustomerViewModel @ViewModelInject constructor(
    private val getFCustomerUseCase: GetFCustomerUseCase,
    private val preferencesManager: PreferencesManager,
    @Assisted private val state: SavedStateHandle

) : BaseViewModel() {
    private val TAG = CustomerViewModel::class.java.simpleName

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewState.value = _userViewState.value?.copy(error = Error(message))
    }
//    val userViewState = state.get<UserViewState>("userViewStateActive")
    var userViewState = UserViewState()
    val ftSalesh = state.get<FtSalesh>("ftSalesh")

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferencesFlow
    private val fCustomerFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        getFCustomerUseCase.getCacheAllFCustomerDomainFlow(query, filterPreferences.sortOrder, 50, -1, filterPreferences.hideCompleted)
    }
    val fCustomerLive = fCustomerFlow.asLiveData()
//    val fCustomerLive = getFCustomerUseCase.getCacheAllFCustomerWithFDivisionDomainLive()
//    val fCustomerLive = getFCustomerUseCase.getCacheAllFCustomerWithGroupDomainLive()
//    val fCustomerLive = getFCustomerUseCase.getCacheAllFCustomerWithFDivisionAndGroupDomainLive()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onCustomerSelected(fCustomer: FCustomer) = viewModelScope.launch {
//        fCustomerEventChannel.send(CustomerEvent.NavigateToEditCustomerScreen(fCustomer))
        ftSalesh?.let {
            fCustomerEventChannel.send( CustomerEvent.NavigateToFtSaleshCustomerOrder(userViewState!!, fCustomer))
        }
    }

    fun onFCustomerCheckedChanged(fCustomer: FCustomer, isChecked: Boolean) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.putCacheFCustomer(fCustomer.toEntity().copy(selected = isChecked))
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

    fun onCustomerSwiped(fCustomer: FCustomer) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.deleteCacheFCustomer(fCustomer.toEntity())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FCustomer error  ${it.message}")
                        },{}
                )
        )

        fCustomerEventChannel.send(CustomerEvent.ShowUndoDeleteCustomerMessage(fCustomer))
    }

    fun onUndoDeleteClick(fCustomer: FCustomer) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFCustomerUseCase.addCacheFCustomer(fCustomer.toEntity())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },
                        {
                            Log.d(TAG, "#result FCustomer error  ${it.message}")
                        },{}
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


    /**
     * Model Events
     */
    private val fCustomerEventChannel = Channel<CustomerEvent>()
    val fCustomerEvent = fCustomerEventChannel.receiveAsFlow()

    sealed class CustomerEvent {
        object NavigateToAddCustomerScreen : CustomerEvent()
        data class NavigateToEditCustomerScreen(val fCustomer: FCustomer) : CustomerEvent()

        data class NavigateToFtSaleshCustomerOrder(var userViewState: UserViewState, val fCustomer: FCustomer) : CustomerEvent()

        data class ShowUndoDeleteCustomerMessage(val fCustomer: FCustomer) : CustomerEvent()
        data class ShowCustomerSavedConfirmationMessage(val msg: String) : CustomerEvent()
        object NavigateToDeleteAllCompletedScreen : CustomerEvent()

        data class NavigateBackWithResult(val result: Int) : CustomerEvent()

    }
}