package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.model.toEntity
import com.erp.distribution.sfa.domain.usecase.GetFCustomerGroupUseCase
import com.erp.distribution.sfa.domain.usecase.GetFCustomerUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtSalesdItemsUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.salesorder.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
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
        var resultLiveData: LiveData<List<FtSalesdItems>>  = getFtSalesdItemsUseCase.getCacheListFtSalesdItemsByFtSaleshFlow(ftSaleshRefno)
        return resultLiveData
    }



//    val userViewState = state.get<UserViewState>("userViewStateActive")
//    var ftSalesh = state.get<FtSalesh>("ftSalesh")
    /**
     * SEMENTARA KITA TIDAK MENGGUNAKAN METODE STATE BINDING tapi Menggunakan Data Binding
     */
//    var fieldBinding_Orderno = state.get<String>("orderno") ?: ftSalesh?.orderno ?: "-"
//        set(value) {
//            field = value
//            state.set("orderno", value)
//        }
//    var fieldBinding_OrderDate = state.get<String>("orderDate") ?: ftSalesh?.orderDate?: Date()
//        set(value) {
//            field = value
//            state.set("orderDate", value)
//        }
//    var fieldBinding_Invoiceno = state.get<String>("invoiceno") ?: ftSalesh?.invoiceno ?: "-"
//        set(value) {
//            field = value
//            state.set("invoiceno", value)
//        }
//    var fieldBinding_InvoiceDate = state.get<String>("invoiceDate") ?: ftSalesh?.invoiceDate ?: Date()
//        set(value) {
//            field = value
//            state.set("invoiceDate", value)
//        }
//
//    var fieldBinding_Custname = state.get<String>("custname") ?: ftSalesh?.fcustomerBean ?.custname?: "-"
//        set(value) {
//            field = value
//            state.set("custname", value)
//        }
//
//    var fieldBinding_Custno = state.get<String>("custno") ?: ftSalesh?.fcustomerBean?.custno ?: "ID: -"
//        set(value) {
//            field = value
//            state.set("custno", value)
//        }
//
//    var fieldBinding_Address1 = state.get<String>("address1")
//            ?: ftSalesh?.fcustomerBean?.address1
//            ?: "-"
//        set(value) {
//            field = value
//            state.set("address1", value)
//        }
//    var fieldBinding_Address2 = state.get<String>("address2")
//            ?: ftSalesh?.fcustomerBean?.address2
//            ?: "-"
//        set(value) {
//            field = value
//            state.set("address2", value)
//        }
//    var fieldBinding_City1 = state.get<String>("city1")
//            ?: ftSalesh?.fcustomerBean?.city1
//            ?: "-"
//        set(value) {
//            field = value
//            state.set("city1", value)
//        }
//    var fieldBinding_TotalSales = state.get<Double>("totalSales")
//            ?: ftSalesh?.amountAfterDiscPlusRpAfterPpn_FG
//            ?: 0.0
//        set(value) {
//            field = value
//            state.set("totalSales", value)
//        }



    private val addEditFtSaleshEventChannel = Channel<AddEditSalesOrderEvent>()
    val addEditFtSaleshEvent = addEditFtSaleshEventChannel.receiveAsFlow()

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
            addEditFtSaleshEventChannel.send(AddEditSalesOrderEvent.NavigateBackWithResult(ftSalesh))

        }

    }

    fun onSelectOrEditCustomer() = viewModelScope.launch {
        val tempUserViewState = UserViewState()
        val tempFtSalesh = FtSalesh()
        addEditFtSaleshEventChannel.send(AddEditSalesOrderEvent.NavigateToSelectCustomerScreen(tempUserViewState, tempFtSalesh))
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

        addEditFtSaleshEventChannel.send(AddEditSalesOrderEvent.RenderDataBindingUI())

    }

    fun onSelectOrEditMaterial() = viewModelScope.launch {
//        val tempUserViewState = UserViewState()
//        val tempFtSalesh = FtSalesh()
//        val tempFtSalesdItems = FtSalesdItems()
        addEditFtSaleshEventChannel.send(AddEditSalesOrderEvent.NavigateToSelectMaterialScreen(userViewState, ftSalesh))
    }

    fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(AddEditSalesOrderEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditSalesOrderEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditSalesOrderEvent()
        data class NavigateBackWithResult(val ftSalesh: FtSalesh) : AddEditSalesOrderEvent()

        data class NavigateToSelectCustomerScreen(var userViewState: UserViewState, val ftSalesh: FtSalesh) : AddEditSalesOrderEvent()
        data class NavigateToSelectMaterialScreen(var userViewState: UserViewState, val ftSalesh: FtSalesh) : AddEditSalesOrderEvent()
        data class NavigateToSelectFtSalesdItemQtyScreen(var userViewState: UserViewState, val ftSalesh: FtSalesh, val ftSalesdItems: FtSalesdItems) : AddEditSalesOrderEvent()

        class RenderDataBindingUI() : AddEditSalesOrderEvent()

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