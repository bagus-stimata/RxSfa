package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.usecase.GetFtSalesdItemsUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list.FSaleshViewModel
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditFtSaleshViewModel @ViewModelInject constructor(
    private val getFtSaleshUseCase: GetFtSaleshUseCase,
    private val getFtSalesdItemsUseCase: GetFtSalesdItemsUseCase
//    ,
//    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val TAG = AddEditFtSaleshViewModel::class.java.simpleName

    var userViewState = UserViewState() //Ingat akan sama dengan pemanggilnya -> FtSaleshFragment sehingga tidak ada Add or Update yang ada hanya Edit
    var isEditMode = false //true-> Edit, false-> Yang hanya membedakan Dao Insert atau Update

    var ftSaleshRefno: Long = 0
    var ftSalesh = FtSalesh() //Ingat akan sama dengan pemanggilnya -> FtSaleshFragment
    fun getCacheFtSaleshByIdLive(ftSaleshRefno: Long): LiveData<FtSalesh> {
        var resultLiveData: LiveData<FtSalesh> = getFtSaleshUseCase.getCacheAllFtSaleshWithItemsByIdLive(ftSaleshRefno)
        return resultLiveData
    }

//    fun getCacheFtSalesdItemsByParentLive(ftSaleshRefno: Long): LiveData<List<FtSalesdItems>> {
//        var resultLiveData: LiveData<List<FtSalesdItems>>  = getFtSalesdItemsUseCase.getCacheListFtSalesdItemsByFtSaleshBean(ftSaleshRefno).map {
//            it.map {
//                it.toDomain()
//            }
//        }
//        return resultLiveData
//    }
    fun getCacheFtSalesdItemsByParentLive(ftSaleshRefno: Long): LiveData<List<FtSalesdItems>> {
        var resultLiveData: LiveData<List<FtSalesdItems>>  = getFtSalesdItemsUseCase.getCacheListFtSalesdItemsByFtSaleshLive(ftSaleshRefno)
        return resultLiveData
    }

    private val addEditFtSaleshEventChannel = Channel<AddEditCustomerOrderEvent>()
    val addEditFtSaleshEvent = addEditFtSaleshEventChannel.receiveAsFlow()


    fun onItemSelected(ftSalesdItems: FtSalesdItems) = viewModelScope.launch {
//        userViewState?.let {
//            addEditFtSaleshEvent.send(FSaleshViewModel.FtSaleshEvent.NavigateToEditSalesOrderScreen(userViewState!!, ftSalesh))
//        }
    }
    fun onItemSwiped(ftSalesdItems: FtSalesdItems) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSalesdItemsUseCase.deleteCacheFtSalesdItems(ftSalesdItems.toEntity())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},{},{}
                )
        )

        addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.ShowUndoDeleteFtSaleshMessage(ftSalesdItems))
    }

    fun onUndoDeleteClick(ftSalesdItems: FtSalesdItems) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSalesdItemsUseCase.addCacheFtSalesdItems(ftSalesdItems.toEntity())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},{},{}
                )
        )
    }


    fun onSaveClick() {
//        if (fieldBinding_Orderno.isBlank()) {
//            showInvalidInputMessage("Name cannot be empty")
//            return
//        }

//        onPopUpBackStackWithTheResult()
        if (ftSalesh != null) {
//            val updatedFtSalesh = ftSalesh.copy(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
//            updateFtSalesh(updatedFtSalesh)
        } else {
//            val newFtSalesh = FtSalesh(invoiceno = ftSaleshName, isValidOrder = ftSaleshImportance )
//            createFtSalesh(newFtSalesh)
        }

    }

    private fun insertFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.addCacheFtSaleshDomain(ftSalesh)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                },
                {
                    Log.d(TAG, "#result FtSalesh error  ${it.message}")
                },{}
            )
        )
   }
    private fun updateFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {

        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.putCacheFtSalesh(ftSalesh.toEntity())
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                        },{
                            Log.d(TAG, "#result FtSalesh error  ${it.message}")
                        },{}
                )
        )
    }

    fun onPopUpBackStackWithTheResult() = viewModelScope.launch {

        ftSalesh.fcustomerBean?.let {
            ftSalesh.fcustomerBean.custno
        }?.let {
            userViewState.fDivision
        }?.let {
            userViewState.fSalesman
        }?.let {
            userViewState.fWarehouse
        }?.run {
            ftSalesh.fdivisionBean = userViewState.fDivision!!
            ftSalesh.fsalesmanBean = userViewState.fSalesman!!
            ftSalesh.fwarehouseBean = userViewState.fWarehouse!!

            if (ftSalesh.fcustomerBean.id >0) {
                if (isEditMode){
//                    Log.d(TAG, "#result Edit Bos>>>>>>>>\n ${ftSalesh.fcustomerBean}")
                    updateFtSalesh(ftSalesh)

                }else {
//                    Log.d(TAG, "#result insert Bos>>>>>>>>\n ${ftSalesh.fcustomerBean}")
                    insertFtSalesh(ftSalesh)
                }
            }
            addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.NavigateBackWithResult(ftSalesh))

        }

    }

    fun onSelectOrEditCustomer() = viewModelScope.launch {
        val tempUserViewState = UserViewState()
        val tempFtSalesh = FtSalesh()
        addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.NavigateToSelectCustomerScreen(tempUserViewState, tempFtSalesh))
    }
    fun onSelectCustomerResult(fCustomer: FCustomer) = viewModelScope.launch {
        /**
         * PERHATIAN DIA LANGSUNG UPDATE KE DATABASE
         */
        ftSalesh.fcustomerBean = fCustomer
        if (ftSalesh.refno>0){
            updateFtSalesh(ftSalesh)
        }else {
            insertFtSalesh(ftSalesh)
        }
//        Log.d(TAG, "#result Hello: ${fCustomer.custname}   and ${ftSalesh?.fcustomerBean?.custname}")

        addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.RenderDataBindingUI())

    }

    fun onSelectOrEditMaterial() = viewModelScope.launch {
//        val tempUserViewState = UserViewState()
//        val tempFtSalesh = FtSalesh()
//        val tempFtSalesdItems = FtSalesdItems()
        addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.NavigateToSelectMaterialScreen(userViewState, ftSalesh))
    }

    fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditCustomerOrderEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditCustomerOrderEvent()
        data class ShowUndoDeleteFtSaleshMessage(val ftSalesdItems: FtSalesdItems) : AddEditCustomerOrderEvent()
        data class NavigateBackWithResult(val ftSalesh: FtSalesh) : AddEditCustomerOrderEvent()

        data class NavigateToSelectCustomerScreen(var userViewState: UserViewState, val ftSalesh: FtSalesh) : AddEditCustomerOrderEvent()
        data class NavigateToSelectMaterialScreen(var userViewState: UserViewState, val ftSalesh: FtSalesh) : AddEditCustomerOrderEvent()
        data class NavigateToSelectFtSalesdItemQtyScreen(var userViewState: UserViewState, val ftSalesh: FtSalesh, val ftSalesdItems: FtSalesdItems) : AddEditCustomerOrderEvent()

        class RenderDataBindingUI() : AddEditCustomerOrderEvent()

    }

//    fun conversionFtSaleshWithFCustomerGroup(ftSalesh: FtSalesh) : LiveData<FtSalesh> {
//        val resultMediatorLiveData: MediatorLiveData<FtSalesh> = MediatorLiveData<FtSalesh>()
//        resultMediatorLiveData.addSource(getFCustomerGroupUseCase.getCacheFCustomerGroupByIdDomainLive(ftSalesh.fcustomerBean.fcustomerGroupBean!!.id), Observer {
//            it?.let { fcustomerGroup ->
//                ftSalesh.fcustomerBean?.let {
//                    ftSalesh.fcustomerBean.fcustomerGroupBean = fcustomerGroup
//                    resultMediatorLiveData.postValue(ftSalesh)
//                }
//            }
//
//        return resultMediatorLiveData
//    }

}