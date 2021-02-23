package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.usecase.GetFCustomerUseCase
import com.erp.distribution.sfa.domain.usecase.GetFDivisionUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtSalesdItemsUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.salesorder.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class FSaleshViewModel @ViewModelInject constructor(
        private val getFtSaleshUseCase: GetFtSaleshUseCase,
        private val getFtSalesdItemsUseCase: GetFtSalesdItemsUseCase,
        private val getFCustomerUseCase: GetFCustomerUseCase,
        private val preferencesManager: PreferencesManager,
        @Assisted private val state: SavedStateHandle

) : BaseViewModel() {
    private val TAG = FSaleshViewModel::class.java.simpleName

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewState.value = _userViewState.value?.copy(error = Error(message))
    }
//    val userViewState = state.get<UserViewState>("userViewStateActive")
    var userViewState = UserViewState()
//    var ftSalesh = FtSalesh() //Menggunakan langsung dari adapter

    val searchQuery = state.getLiveData("searchQuery", "")
    val preferencesFlow = preferencesManager.preferencesFlow

    private val ftSaleshFlow = combine(
            searchQuery.asFlow(),
            preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        getFtSaleshUseCase.getCacheAllFtSaleshFlow(
                query,
                filterPreferences.sortOrder, 75, -1,
                filterPreferences.hideCompleted
        )
    }


    val ftSaleshLive = ftSaleshFlow.asLiveData()
//    val ftSaleshLive = getFtSaleshUseCase.getCacheAllFtSaleshLive()

    fun getFtSaleshWithTransform(): LiveData<List<FtSalesh>> {
        var resultLiveData: LiveData<List<FtSalesh>> = ftSaleshLive

        resultLiveData = Transformations.switchMap(resultLiveData, {
            conversionAddItems2(it)
        })
        return resultLiveData
    }

    val ftSaleshWithItemsLive = getFtSaleshUseCase.getCacheAllFtSaleshWithItemsLive()



    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onItemCheckedChanged(ftSalesh: FtSalesh, isChecked: Boolean) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.putCacheFtSaleshDomain(ftSalesh.copy(selected = isChecked))
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                        },
                        {
                        }, {}
                )
        )

    }


    fun onItemSelected(ftSalesh: FtSalesh) = viewModelScope.launch {
        userViewState?.let {
            ftSaleshEventChannel.send(FtSaleshEvent.NavigateToEditSalesOrderScreen(userViewState!!, ftSalesh))
        }
    }
    fun onItemSwiped(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.deleteCacheFtSalesh(ftSalesh.toEntity())
            getFtSalesdItemsUseCase.deleteAllCacheFtSalesdItemsByFtSalesh(ftSalesh.toEntity())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                        },
                        {
                        }, {}
                )
        )

        ftSaleshEventChannel.send(FtSaleshEvent.ShowUndoDeleteFtSaleshMessage(ftSalesh))
    }

    fun onUndoDeleteClick(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.addCacheFtSaleshDomain(ftSalesh)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                        },
                        {
                            Log.d(TAG, "#result FtSalesh error  ${it.message}")
                        }, {}
                )
        )
    }

    fun onAddNewFtSaleshClick() = viewModelScope.launch {
//        ftSaleshEventChannel.send(FtSaleshEvent.ShowFtSaleshSavedConfirmationMessage("Isinya: ${userViewState!!}" ))
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToAddSalesOrderScreen(userViewState!!))
//        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToAddSalesOrderScreen(userViewState!!, FtSalesh()))
    }

    fun onAddEditResult(ftSaleshResult: FtSalesh) {
        ftSaleshResult?.let {
//            ftSaleshResult ->  ftSalesh
        }

//        when (result) {
//            ADD_TASK_RESULT_OK -> showFtSaleshSavedConfirmationMessage("Data added")
//            EDIT_TASK_RESULT_OK -> showFtSaleshSavedConfirmationMessage("Data updated")
//        }

    }

    private fun showFtSaleshSavedConfirmationMessage(text: String) = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.ShowFtSaleshSavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToDeleteAllCompletedScreen)
    }

    fun popUpBackStackWithTheResult() = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))
    }


    /**
     * Model Events
     */
    private val ftSaleshEventChannel = Channel<FtSaleshEvent>()
    val ftSaleshEvent = ftSaleshEventChannel.receiveAsFlow()

    sealed class FtSaleshEvent {
//        object NavigateToAddSalesOrderScreen() : FtSaleshEvent()
        data class NavigateToAddSalesOrderScreen(val userViewState: UserViewState) : FtSaleshEvent()

        /**
         * Ingat FtSalesh disini sudah include List FtSalesItems sebab saat dari tarik dari database sudah dalam keadaan berelasi antar tabel
         */
        data class NavigateToEditSalesOrderScreen(val userViewState: UserViewState, val ftSalesh: FtSalesh) : FtSaleshEvent()
//        data class NavigateToEditSalesOrderScreen(val userViewState: UserViewState, val ftSaleshBean: Long) : FtSaleshEvent() //--> Sebaiknya nanti gunakan Via Database Langsung

        data class ShowUndoDeleteFtSaleshMessage(val ftSalesh: FtSalesh) : FtSaleshEvent()
        data class ShowFtSaleshSavedConfirmationMessage(val msg: String) : FtSaleshEvent()
        object NavigateToDeleteAllCompletedScreen : FtSaleshEvent()

        data class NavigateBackWithResult(val result: Int) : FtSaleshEvent()
    }


    /**
     * THIS IS THE OTHER WAY
     * RELATION SHIP WITHOUT TABLE RELATIONSHIP
     *
     */
    fun getFtSaleshWithFCustomerLive(): LiveData<List<FtSalesh>> {
        var resultLiveData: LiveData<List<FtSalesh>> = ftSaleshLive

        resultLiveData = Transformations.switchMap(resultLiveData, {
            conversionFtSaleshWithFCustomer(it)
        })

        return resultLiveData
    }

    fun conversionFtSaleshWithFCustomer(list: List<FtSalesh>) : LiveData<List<FtSalesh>> {
        val resultMediatorLiveData: MediatorLiveData<List<FtSalesh>> = MediatorLiveData<List<FtSalesh>>()
        for (data in list) {
            resultMediatorLiveData.addSource(getFCustomerUseCase.getCacheFCustomerById(data.fcustomerBean.id), Observer {
                it?.let {
                    data.fcustomerBean = it.toDomain()
                    resultMediatorLiveData.postValue(list)
                }
            }
            )
        }
        return resultMediatorLiveData
    }

    fun conversionAddItems(list: List<FtSalesh>) : LiveData<List<FtSalesh>> {
        val resultMediatorLiveData: MediatorLiveData<List<FtSalesh>> = MediatorLiveData<List<FtSalesh>>()
        for (data in list) {
            resultMediatorLiveData.addSource(getFtSalesdItemsUseCase.getCacheListFtSalesdItemsByFtSaleshLive(data.refno), Observer {
                it?.let {
                    data.listFtSalesdItems.addAll(it)
                    resultMediatorLiveData.postValue(list)
                    Log.d(TAG, "#result ::>> ${it.size}")
                }
            })
        }
        return resultMediatorLiveData
    }

    fun conversionAddItems2(list: List<FtSalesh>) : LiveData<List<FtSalesh>> {
        val resultMediatorLiveData: MediatorLiveData<List<FtSalesh>> = MediatorLiveData<List<FtSalesh>>()
        resultMediatorLiveData.value = list
        for (data in list) {
            resultMediatorLiveData.addSource(getFtSalesdItemsUseCase.getCacheListFtSalesdItemsByFtSaleshLive(data.refno), Observer {
                it?.let {
                    data.listFtSalesdItems.addAll(it)
                    Log.d(TAG, "#result ::>> ${it.size}")
                    resultMediatorLiveData.postValue(list)
                }
            })
        }
        return resultMediatorLiveData
    }



}


class CombinedSubjectReminders(
        ldSubject: LiveData<List<FtSalesh>>,
        ldReminder: LiveData<List<FtSalesdItems>>
) : MediatorLiveData<Pair<List<FtSalesh>, List<FtSalesdItems>>>() {

    private var listSubject: List<FtSalesh> = emptyList()
    private var listReminder: List<FtSalesdItems> = emptyList()

    init {
        value = Pair(listSubject, listReminder)

        addSource(ldSubject) {
            if( it != null ) listSubject = it
            value = Pair(listSubject, listReminder)
        }

        addSource(ldReminder) {
            if( it != null ) listReminder = it
            value = Pair(listSubject, listReminder)
        }
    }
}