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
import com.erp.distribution.sfa.utils.DisposableManager
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
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

    var ftSaleshRefnoLive: MutableLiveData<Long> = MutableLiveData()
    var ftSalesh = FtSalesh() //Ingat akan sama dengan pemanggilnya -> FtSaleshFragment
    fun getCacheFtSaleshByIdLive(ftSaleshRefno: Long): LiveData<FtSalesh> {
        var resultLiveData: LiveData<FtSalesh> = getFtSaleshUseCase.getCacheAllFtSaleshWithItemsByIdLive(ftSaleshRefno)
        return resultLiveData
    }

    var mutableMapFtSalesdItems = mutableMapOf<Long, FtSalesdItems>()

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
        userViewState?.let {
            if (ftSalesh.unread ==true) {
                addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.NavigateToSelectFtSalesdItemQtyScreen(userViewState!!, ftSalesh, ftSalesdItems))
            }else {
                addEditFtSaleshEventChannel.send(AddEditCustomerOrderEvent.ShowInvalidInputMessage("Sudah diterbitkan Nomor Order oleh Admin"))
            }
        }
    }
    fun onItemSwiped(ftSalesdItems: FtSalesdItems) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSalesdItemsUseCase.deleteCacheFtSalesdItems(ftSalesdItems).also {
                //Do Something
            }
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
            getFtSalesdItemsUseCase.addCacheFtSalesdItems(ftSalesdItems)
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
//        DisposableManager.add(Observable.fromCallable {
//            getFtSaleshUseCase.addCacheFtSaleshDomain(ftSalesh)
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe (
//                {},
//                {
//                    Log.d(TAG, "#result FtSalesh error  ${it.message}")
//                },{}
//            )
//        )
        DisposableManager.add(
                getFtSaleshUseCase.insertSingleCacheFtSalesh(ftSalesh.toEntity())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribeWith( object : DisposableSingleObserver<Long>() {
                            override fun onSuccess(newReturnId: Long?) {
                                newReturnId.let {
//                                    ftSaleshRefnoLive = newReturnId!!
                                    ftSaleshRefnoLive.postValue(newReturnId!!)
                                    ftSalesh.refno = newReturnId!!
                                }
                            }
                            override fun onError(e: Throwable?) {
                            }
                        })
        )
    }
    public fun updateFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {

        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.putCacheFtSalesh(ftSalesh)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {},{
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

    fun autoSyncToServer() = viewModelScope.launch {
        getFtSaleshUseCase.getCacheAllFtSaleshWithItemsSingle()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    for (ftSaleshBean in it.filter { it.listFtSalesdItems.size >0  && it.refno==ftSalesh.refno}){ //MAIN DI FILTER SUPAYA CUMA DAPAT SATU SAJA

                        if (ftSaleshBean.orderno.equals(""))
                            ftSaleshBean.orderno = "NewMobile"

                        if (ftSaleshBean.sourceId == 0.toLong())
                            ftSaleshBean.sourceId = System.currentTimeMillis()

                        /**
                         * Ingat createRemoteFtSaleshFromAndroid Android lho ya
                         */
                        val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
                        getFtSaleshUseCase
                                .addOrUpdateRemoteFtSaleshFromAndroid(authHeader, ftSaleshBean)
                                .toObservable()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .doOnNext { ftSaleshFromRemote ->
                                    /**
                                     * refno sudah berubah dengan refno dari server
                                     */
                                    ftSaleshBean.stared = true
                                    if (! ftSaleshFromRemote.orderno.trim().toLowerCase().contains("new") && ! ftSaleshFromRemote.orderno.trim().equals(""))  {

//                                        val ftSalesh = ftSaleshBean.copy(stared=true, unread = false, orderno = ftSaleshFromRemote.orderno,
//                                                orderDate = ftSaleshFromRemote.orderDate, invoiceDate = ftSaleshFromRemote.invoiceDate, dueDate = ftSaleshFromRemote.dueDate,
//                                                sjPengirimanDate = ftSaleshFromRemote.sjPengirimanDate, sjPenagihanDate = ftSaleshFromRemote.sjPenagihanDate,
//                                                disc1 = ftSaleshFromRemote.disc1, disc2 = ftSaleshFromRemote.disc2, discPlus_FG = ftSaleshFromRemote.discPlus_FG,
//
//                                                invoiceno = ftSaleshFromRemote.invoiceno, amountAfterDiscPlusRpAfterPpn_FG = ftSaleshFromRemote.amountAfterDiscPlusRpAfterPpn_FG)
//                                        //LANJUTKAN DIATAS NANTI YA
//                                        updateCacheFtSalesh(ftSalesh)
//                                        getFtSalesdItemsUseCase.getRemoteAllFtSalesdItemsByFtSalesh(authHeader, ftSaleshFromRemote.refno)
//                                                .toObservable()
//                                                .observeOn(AndroidSchedulers.mainThread())
//                                                .subscribeOn(Schedulers.io())
//                                                .doOnNext{
//                                                    listFtSalesdItems ->
//                                                    updateCacheFtSalesd_FromRepo(ftSalesh, listFtSalesdItems)
//                                                    Log.d(TAG, "#result hello bos detil sumber")
//
//                                                }
//                                                .subscribe (
//                                                        {
////                                                            Log.d(TAG, "#result sukses FtSalesdI --> ${listFtSalesdItems}")
//                                                        },
//                                                        {
//                                                            Log.e(TAG, "#result Error Update FtSalesd")
//                                                        }, {}
//                                                )

                                    }else {

                                        /**
                                         * Masih fresh
                                         */
                                        updateCacheFtSalesh(ftSaleshBean.copy(stared=true))

//                                        Log.d(TAG, "#result Bawah")

                                        getFtSalesdItemsUseCase.createRemoteListFtSalesdItems(SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm),
                                                ftSaleshBean.listFtSalesdItems.map {
                                                    it.ftSaleshBean = ftSaleshFromRemote!!
                                                    it
                                                })
                                                .toObservable()
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribeOn(Schedulers.io())
                                                .subscribe({
                                                    Log.d(TAG, "#result Items Success ${it}")
                                                }, {
                                                    Log.e(TAG, "#result  Items Error ${it}")
                                                }, {})

                                    }

                                }
                                .subscribe({
                                    /**
                                     * Change Status of FtSalesh
                                     */

                                }, {
//                                                Log.e(TAG, "#result Error OnSubscribe:\n ${it} ")
                                }, {})
//                                }
                    }//endfor
                }
                .subscribe({}, {
//                    Log.e(TAG, "#result Error subscribe:\n ${it} ")
                }, {})

    }

    fun updateCacheFtSalesh(ftSalesh: FtSalesh) {
//        Log.d(TAG, "#result hello bos header")

        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.putCacheFtSalesh(ftSalesh)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},
                        {
//                            Log.e(TAG, "#result Error Update ${it}")
                        }, {}
                )
        )
    }

    fun updateCacheFtSalesd_FromRepo(ftSalesh: FtSalesh, list: List<FtSalesdItems>)  {
//        Log.d(TAG, "#result hello bos detil")
        DisposableManager.add(Observable.fromCallable {
            getFtSalesdItemsUseCase.deleteAllCacheFtSalesdItemsByFtSalesh(ftSalesh).also {
                for (data in list){
//                    Log.d(TAG, "#result ${data.qty}")
                    data.ftSaleshBean = ftSalesh
                    getFtSalesdItemsUseCase.addCacheFtSalesdItems(data)
//                    Log.d(TAG, "#result ${data.qty}")
                }
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},
                        {
//                            Log.e(TAG, "#result Error Update ${it}")
                        }, {}
                )
        )
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